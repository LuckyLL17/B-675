# 批量图片下载器

批量下载图片到本地的工具，支持多个 URL 同时下载，带 SQLite 数据库记录下载历史。

## 🛠 技术栈
- Frontend: HTML + Tailwind CSS + JavaScript
- Backend: PHP 8
- Database: SQLite
- Server: Nginx + Apache

## 🚀 快速启动 (Docker)

1. 确保 Docker Desktop 已运行
2. 在根目录执行：
```bash
docker compose up --build
```
3. 访问前端：http://localhost:3000
4. 后端 API：http://localhost:8000/imgdow.php

## 📸 功能介绍

### 主要功能
- ✅ 批量输入图片链接（一行一个）
- ✅ 实时显示下载进度（已下载/总数量）
- ✅ 自动创建日期目录（imgdow+年月日）
- ✅ SQLite 数据库记录下载历史
- ✅ 统计卡片（总下载/成功/失败/今日）
- ✅ 历史记录查看（分页）
- ✅ 响应式设计（PC/移动端适配）

### 使用方法
1. 在输入框中粘贴图片链接，每行一个
2. 点击「开始下载」按钮
3. 查看下载进度，完成后可查看历史记录

## 📁 目录结构
```
batch-downloader/
├── README.md
├── docker-compose.yml
├── frontend/
│   ├── Dockerfile
│   ├── index.html
│   └── nginx.conf
├── backend/
│   ├── Dockerfile
│   └── imgdow.php
├── data/                   # SQLite 数据库
│   └── downloads.db
└── downloads/              # 下载的图片
    └── imgdow20260120/
```

## 🔌 API 接口

| 接口 | 方法 | 说明 |
|------|------|------|
| `/imgdow.php` | POST | 下载单个图片 |
| `/imgdow.php?action=history` | GET | 获取下载历史 |
| `/imgdow.php?action=stats` | GET | 获取统计信息 |
