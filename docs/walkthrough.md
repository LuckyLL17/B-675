# Spring Boot + Vue Project Walkthrough

## 项目概述

本项目是一个基于 Docker 容器化的前后端分离应用。
- **目标**: 实现一个消息接收系统，前端发送消息，后端在控制台打印。
- **状态**: 代码已完成，Docker 已启动，验证通过。

### 📂 目录结构梳理

```
echo-link/
├── README.md               # 项目入口文档
├── docker-compose.yml      # 一键启动配置
├── docs/                   # 项目过程文档
│   ├── implementation_plan.md
│   └── task.md
├── frontend/               # Vue 3 前端
│   ├── Dockerfile          # Nginx 镜像构建
│   ├── nginx.conf          # 反向代理配置 (Port 80 -> 3000)
│   ├── package.json        # 依赖定义
│   ├── vite.config.js      # 构建配置
│   ├── tailwind.config.js  # 样式配置
│   └── src/
│       ├── main.js         # Vue 入口
│       ├── App.vue         # 根组件
│       ├── pages/Home.vue  # 首页 (消息表单容器)
│       └── components/     # UI 组件
└── backend/                # Spring Boot 后端
    ├── Dockerfile          # JDK 17 镜像构建
    ├── pom.xml             # Maven 依赖
    └── src/main/java/com/echolink/
        ├── EchoLinkApplication.java # 启动类
        ├── controller/     # API 接口层
        ├── service/        # 业务逻辑层
        ├── model/          # 数据实体
        └── config/         # CORS 配置
```

## ✅ 已完成工作

### 1. 后端实现 (Backend)
- **Spring Boot 3.2**: 搭建基础框架。
- **MessageController**: 定义 `POST /api/message` 接口。
- **MessageService**: 实现消息打印到控制台的逻辑。
- **CorsConfig**: 允许前端跨域访问。
- **Dockerfile**: 使用多阶段构建，产出轻量级 JAR 镜像。

### 2. 前端实现 (Frontend)
- **Vue 3 + Vite**: 搭建高性能前端框架。
- **Tailwind CSS**: 实现现代化的 UI 设计（玻璃态、渐变）。
- **交互功能**: 
    - 输入验证
    - Loading 状态
    - Toast 成功/失败提示
- **Nginx**: 配置静态资源服务和 API 反向代理。

### 3. Docker 环境
- **docker-compose.yml**: 编排 Front (3000) 和 Back (8000) 服务。
- **网络**: 配置内部网络互通，前端通过 Nginx 代理请求到后端。

## 🧪 验证计划

1. **启动服务**: 运行 `docker compose up --build`。
2. **前端检查**: 访问 `http://localhost:3000`，确认页面加载，样式正常。
3. **功能测试**: 发送一条消息 "Hello Docker"，确认弹出成功提示。
4. **后端检查**: 查看 Docker 日志，确认控制台输出了消息内容。

```bash
# 查看日志验证
docker compose logs -f backend
```

### 验证结果记录
- **Backend Log**:
  ```
  [2026-01-20 13:05:20] 收到新消息:
  内容: Agent Verification via Nginx
  ```
- **API Response**:
  ```json
  {"success":true,"message":"消息接收成功"}
  ```

