# Spring Boot + Vue 前后端分离项目实现计划 (已完成)

> [!NOTE]
> 本计划已于 2026-01-20 执行完毕。项目已通过 Docker 验证。


创建一个简单的消息传递应用：前端接收用户输入的消息，发送到后端，后端将消息输出到控制台。

## 技术栈

- **Frontend**: Vue 3 + Vite + Tailwind CSS
- **Backend**: Spring Boot 3.2 + Maven
- **Container**: Docker + docker-compose

---

## Proposed Changes

### Backend (Spring Boot)

#### [NEW] [pom.xml](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/pom.xml)
Maven 配置文件，包含 Spring Boot Web 依赖。

#### [NEW] [EchoLinkApplication.java](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/src/main/java/com/echolink/EchoLinkApplication.java)
Spring Boot 主入口类。

#### [NEW] [Message.java](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/src/main/java/com/echolink/model/Message.java)
消息数据模型类。

#### [NEW] [MessageController.java](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/src/main/java/com/echolink/controller/MessageController.java)
REST API 控制器，接收 POST 请求。

#### [NEW] [MessageService.java](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/src/main/java/com/echolink/service/MessageService.java)
业务逻辑层，负责将消息输出到控制台。

#### [NEW] [CorsConfig.java](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/src/main/java/com/echolink/config/CorsConfig.java)
CORS 跨域配置。

#### [NEW] [application.properties](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/src/main/resources/application.properties)
Spring Boot 配置文件。

#### [NEW] [Dockerfile](file:///Users/jack.yan/Downloads/labeleases/echo-link/backend/Dockerfile)
后端容器构建文件。

---

### Frontend (Vue 3 + Vite)

#### [NEW] [package.json](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/package.json)
Node 依赖配置。

#### [NEW] [vite.config.js](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/vite.config.js)
Vite 配置。

#### [NEW] [index.html](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/index.html)
HTML 入口。

#### [NEW] [tailwind.config.js](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/tailwind.config.js)
Tailwind CSS 配置。

#### [NEW] [postcss.config.js](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/postcss.config.js)
PostCSS 配置。

#### [NEW] [src/main.js](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/src/main.js)
Vue 入口文件。

#### [NEW] [src/App.vue](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/src/App.vue)
根组件。

#### [NEW] [src/index.css](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/src/index.css)
全局样式。

#### [NEW] [src/pages/HomePage.vue](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/src/pages/HomePage.vue)
首页。

#### [NEW] [src/components/MessageForm.vue](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/src/components/MessageForm.vue)
消息发送表单组件，包含输入框、发送按钮、Loading 状态、Toast 提示。

#### [NEW] [nginx.conf](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/nginx.conf)
Nginx 配置。

#### [NEW] [Dockerfile](file:///Users/jack.yan/Downloads/labeleases/echo-link/frontend/Dockerfile)
前端容器构建文件。

---

### Docker 配置

#### [NEW] [docker-compose.yml](file:///Users/jack.yan/Downloads/labeleases/echo-link/docker-compose.yml)
服务编排配置。

---

### 文档

#### [NEW] [README.md](file:///Users/jack.yan/Downloads/labeleases/echo-link/README.md)
项目说明文档。

---

## Verification Plan

### 自动测试 (Docker)

```bash
cd /Users/jack.yan/Downloads/labeleases/echo-link
docker compose up --build
```

验证：
1. 容器正常启动无报错
2. 前端访问 http://localhost:3000 正常
3. 后端访问 http://localhost:8000 正常

### 功能测试 (浏览器)

1. 打开 http://localhost:3000
2. 在输入框中输入消息，例如 "Hello World"
3. 点击发送按钮
4. 观察：
   - 前端显示 Loading 状态
   - 发送成功后显示 Toast 提示
5. 查看后端容器日志，确认消息已输出

```bash
docker compose logs backend
```

### 移动端适配测试

使用浏览器开发者工具切换到移动端视图，确认 UI 适配正常。
