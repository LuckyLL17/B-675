# Echo Link

![Project Status](https://img.shields.io/badge/status-active-success.svg)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=flat&logo=docker&logoColor=white)
![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=flat&logo=vuedotjs&logoColor=%234FC08D)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=flat&logo=spring&logoColor=white)

Echo Link 是一个简洁优雅的前后端分离消息传递应用。前端基于 Vue 3 构建精美界面，后端使用 Spring Boot 处理业务，通过 Docker Compose 实现一键部署。

## ✨ 功能特性

- **现代化 UI**: 采用 Tailwind CSS 设计的玻璃态风格，支持响应式布局。
- **实时反馈**: 完善的 Loading 状态和 Toast 提示。
- **容器化**: 完整的前后端 Docker 编排，开箱即用。
- **健壮性**: 包含 Nginx 反向代理和 CORS 配置。

## 🛠 技术栈

- **Frontend**: Vue 3 + Vite + Tailwind CSS
- **Backend**: Spring Boot 3.2 + Java 17
- **Container**: Docker + Nginx

## 📚 文档索引

- [📄 任务清单 (Task List)](docs/task.md) - 项目开发过程记录
- [🏗 实现计划 (Implementation Plan)](docs/implementation_plan.md) - 架构设计与技术决策
- [📖 演练 & 验证 (Walkthrough)](docs/walkthrough.md) - 验证步骤与结果证据
- [🔌 API 文档](docs/API_DOCUMENTATION.md) - 接口详细说明
- [💻 本地开发指南](docs/LOCAL_DEVELOPMENT.md) - 非 Docker 环境下的开发运行

---

## 🚀 快速启动 (Docker)

这是推荐的运行方式，无需本地安装 Java 或 Node.js 环境。

1. **前提**: 确保 Docker Desktop 已运行。
2. **启动**:
   ```bash
   docker compose up --build
   ```
3. **访问**:
   - 前端页面: http://localhost:3000
4. **验证**:
   - 在页面输入消息并发送。
   - 查看后端日志确认接收：
     ```bash
     docker compose logs -f backend
     ```

## 📂 项目结构

```
echo-link/
├── docker-compose.yml      # Docker 服务编排
├── README.md               # 项目入口文档
├── docs/                   # 详细文档目录
│   ├── API_DOCUMENTATION.md
│   ├── LOCAL_DEVELOPMENT.md
│   ├── implementation_plan.md
│   ├── task.md
│   └── walkthrough.md
├── frontend/               # Vue 前端源码
│   ├── Dockerfile
│   ├── nginx.conf          # Nginx 代理配置
│   └── src/
└── backend/                # Spring Boot 后端源码
    ├── Dockerfile
    ├── pom.xml
    └── src/
```

## 🔧 常见操作

### 停止服务
```bash
docker compose down
```

### 查看日志
```bash
# 查看所有日志
docker compose logs -f

# 查看特定服务
docker compose logs -f backend
docker compose logs -f frontend
```
