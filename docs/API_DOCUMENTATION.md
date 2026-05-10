# API 接口文档

本文档详细说明了 Echo Link 后端服务提供的 RESTful API 接口。

## 基础信息

- **Base URL**: `http://localhost:8000` (Direct) / `http://localhost:3000/api` (Via Proxy)
- **Content-Type**: `application/json`

---

## 消息相关接口

### 1. 发送消息

接收前端发送的消息并打印到后端控制台。

- **Endpoint**: `/api/message`
- **Method**: `POST`
- **Access**: Public

#### 请求参数 (Body)

| 参数名 | 类型 | 必填 | 说明 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `content` | string | 是 | 消息内容 | "Hello Echo" |

#### 响应示例

**成功 (200 OK)**

```json
{
  "success": true,
  "message": "消息接收成功"
}
```

**失败 (400 Bad Request)**

```json
{
  "success": false,
  "message": "消息内容不能为空"
}
```

#### 调用示例 (cURL)

```bash
curl -X POST http://localhost:3000/api/message \
  -H "Content-Type: application/json" \
  -d '{"content": "Hello World"}'
```

---

## 系统接口

### 2. 健康检查

检查后端服务是否存活。

- **Endpoint**: `/api/health`
- **Method**: `GET`

#### 响应示例

```json
{
  "status": "UP",
  "timestamp": "2026-01-20T12:00:00Z"
}
```
