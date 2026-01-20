<?php
/**
 * 批量图片下载器 - PHP API (含 SQLite 数据库)
 * 
 * 功能：
 * - 下载单个图片到本地 imgdow+年月日 目录
 * - 记录下载历史到 SQLite 数据库
 * - 查询下载历史
 */

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

// 处理预检请求
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit;
}

// 数据库初始化
$dbPath = __DIR__ . '/data/downloads.db';
$dbDir = dirname($dbPath);

if (!is_dir($dbDir)) {
    mkdir($dbDir, 0755, true);
}

try {
    $db = new PDO('sqlite:' . $dbPath);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // 创建表
    $db->exec('CREATE TABLE IF NOT EXISTS downloads (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        url TEXT NOT NULL,
        filename TEXT NOT NULL,
        directory TEXT NOT NULL,
        file_size INTEGER DEFAULT 0,
        status TEXT DEFAULT "success",
        error_message TEXT,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )');
} catch (PDOException $e) {
    echo json_encode(['success' => false, 'error' => '数据库连接失败: ' . $e->getMessage()]);
    exit;
}

// 路由处理
$method = $_SERVER['REQUEST_METHOD'];
$action = $_GET['action'] ?? 'download';

switch ($action) {
    case 'history':
        // 获取下载历史
        getHistory($db);
        break;
    case 'stats':
        // 获取统计信息
        getStats($db);
        break;
    case 'download':
    default:
        // 下载图片
        if ($method === 'POST') {
            downloadImage($db);
        } else {
            echo json_encode(['success' => false, 'error' => '仅支持 POST 请求']);
        }
        break;
}

/**
 * 下载图片
 */
function downloadImage($db)
{
    $input = json_decode(file_get_contents('php://input'), true);

    if (!isset($input['url']) || empty($input['url'])) {
        echo json_encode(['success' => false, 'error' => '请提供图片 URL']);
        return;
    }

    $url = trim($input['url']);

    // 验证 URL 格式
    if (!filter_var($url, FILTER_VALIDATE_URL)) {
        saveToDb($db, $url, '', '', 0, 'failed', 'URL 格式无效');
        echo json_encode(['success' => false, 'error' => 'URL 格式无效', 'url' => $url]);
        return;
    }

    // 创建下载目录：imgdow + 年月日
    $dateFolder = 'imgdow' . date('Ymd');
    $downloadDir = __DIR__ . DIRECTORY_SEPARATOR . $dateFolder;

    if (!is_dir($downloadDir)) {
        if (!mkdir($downloadDir, 0755, true)) {
            saveToDb($db, $url, '', $dateFolder, 0, 'failed', '无法创建下载目录');
            echo json_encode(['success' => false, 'error' => '无法创建下载目录', 'url' => $url]);
            return;
        }
    }

    // 从 URL 提取文件名
    $parsedUrl = parse_url($url);
    $pathInfo = pathinfo($parsedUrl['path'] ?? '');
    $filename = $pathInfo['basename'] ?? ('image_' . time() . '.jpg');

    // 清理文件名
    $filename = preg_replace('/[^a-zA-Z0-9._-]/', '_', $filename);
    if (empty($filename) || $filename === '.') {
        $filename = 'image_' . uniqid() . '.jpg';
    }

    // 确保文件名唯一
    $savePath = $downloadDir . DIRECTORY_SEPARATOR . $filename;
    $counter = 1;
    while (file_exists($savePath)) {
        $nameWithoutExt = $pathInfo['filename'] ?? 'image';
        $ext = $pathInfo['extension'] ?? 'jpg';
        $filename = $nameWithoutExt . '_' . $counter . '.' . $ext;
        $savePath = $downloadDir . DIRECTORY_SEPARATOR . $filename;
        $counter++;
    }

    // 下载图片
    try {
        $context = stream_context_create([
            'http' => [
                'method' => 'GET',
                'header' => [
                    'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36',
                    'Accept: image/*,*/*;q=0.8',
                    'Referer: ' . $url
                ],
                'timeout' => 30,
                'ignore_errors' => true
            ],
            'ssl' => [
                'verify_peer' => false,
                'verify_peer_name' => false
            ]
        ]);

        $imageData = @file_get_contents($url, false, $context);

        if ($imageData === false) {
            saveToDb($db, $url, '', $dateFolder, 0, 'failed', '下载失败');
            echo json_encode(['success' => false, 'error' => '下载失败', 'url' => $url]);
            return;
        }

        // 保存文件
        if (file_put_contents($savePath, $imageData) === false) {
            saveToDb($db, $url, $filename, $dateFolder, 0, 'failed', '保存失败');
            echo json_encode(['success' => false, 'error' => '保存失败', 'url' => $url]);
            return;
        }

        $fileSize = strlen($imageData);

        // 保存到数据库
        saveToDb($db, $url, $filename, $dateFolder, $fileSize, 'success', null);

        echo json_encode([
            'success' => true,
            'url' => $url,
            'savedAs' => $filename,
            'directory' => $dateFolder,
            'size' => $fileSize
        ]);

    } catch (Exception $e) {
        saveToDb($db, $url, '', $dateFolder, 0, 'failed', $e->getMessage());
        echo json_encode(['success' => false, 'error' => $e->getMessage(), 'url' => $url]);
    }
}

/**
 * 保存记录到数据库
 */
function saveToDb($db, $url, $filename, $directory, $size, $status, $error)
{
    try {
        $stmt = $db->prepare('INSERT INTO downloads (url, filename, directory, file_size, status, error_message) VALUES (?, ?, ?, ?, ?, ?)');
        $stmt->execute([$url, $filename, $directory, $size, $status, $error]);
    } catch (Exception $e) {
        // 静默失败，不影响主流程
    }
}

/**
 * 获取下载历史
 */
function getHistory($db)
{
    try {
        $page = isset($_GET['page']) ? max(1, (int) $_GET['page']) : 1;
        $limit = isset($_GET['limit']) ? min(100, max(1, (int) $_GET['limit'])) : 20;
        $offset = ($page - 1) * $limit;

        // 总数
        $countStmt = $db->query('SELECT COUNT(*) FROM downloads');
        $total = (int) $countStmt->fetchColumn();

        // 列表
        $stmt = $db->prepare('SELECT * FROM downloads ORDER BY created_at DESC LIMIT ? OFFSET ?');
        $stmt->execute([$limit, $offset]);
        $records = $stmt->fetchAll(PDO::FETCH_ASSOC);

        echo json_encode([
            'success' => true,
            'data' => $records,
            'pagination' => [
                'page' => $page,
                'limit' => $limit,
                'total' => $total,
                'totalPages' => ceil($total / $limit)
            ]
        ]);
    } catch (Exception $e) {
        echo json_encode(['success' => false, 'error' => $e->getMessage()]);
    }
}

/**
 * 获取统计信息
 */
function getStats($db)
{
    try {
        $stats = [];

        // 总下载数
        $stmt = $db->query('SELECT COUNT(*) as total, SUM(CASE WHEN status = "success" THEN 1 ELSE 0 END) as success, SUM(CASE WHEN status = "failed" THEN 1 ELSE 0 END) as failed, SUM(file_size) as total_size FROM downloads');
        $stats = $stmt->fetch(PDO::FETCH_ASSOC);

        // 今日下载
        $todayStmt = $db->query('SELECT COUNT(*) as today FROM downloads WHERE DATE(created_at) = DATE("now")');
        $stats['today'] = (int) $todayStmt->fetchColumn();

        echo json_encode([
            'success' => true,
            'stats' => [
                'total' => (int) $stats['total'],
                'success' => (int) $stats['success'],
                'failed' => (int) $stats['failed'],
                'today' => $stats['today'],
                'totalSize' => (int) $stats['total_size'],
                'totalSizeFormatted' => formatBytes((int) $stats['total_size'])
            ]
        ]);
    } catch (Exception $e) {
        echo json_encode(['success' => false, 'error' => $e->getMessage()]);
    }
}

/**
 * 格式化文件大小
 */
function formatBytes($bytes)
{
    if ($bytes === 0)
        return '0 B';
    $units = ['B', 'KB', 'MB', 'GB'];
    $i = floor(log($bytes, 1024));
    return round($bytes / pow(1024, $i), 2) . ' ' . $units[$i];
}
