# 本地开发指南

如果你不想使用 Docker，或者需要进行代码调试，可以按照以下步骤在本地运行项目。

## 环境要求

- **Node.js**: v18+
- **Java**: JDK 17+
- **Maven**: 3.6+

---

## 1. 启动后端 (Spring Boot)

后端服务默认运行在 `8000` 端口。

1. 进入后端目录：
   ```bash
   cd backend
   ```

2. 安装依赖并运行：
   ```bash
   ./mvnw spring-boot:run
   ```
   或者如果你安装了全局 Maven：
   ```bash
   mvn spring-boot:run
   ```

3. 验证启动：
   - 看到日志 `Started EchoLinkApplication in ...` 即为启动成功。
   - 访问 `http://localhost:8000/api/health` 查看健康状态 (需先实现该接口，或直接测试 Message 接口)。

---

## 2. 启动前端 (Vue 3)

前端开发服务器默认运行在 `3000` 端口。请检查 `vite.config.js`。

1. 进入前端目录：
   ```bash
   cd frontend
   ```

2. 安装依赖：
   ```bash
   npm install
   ```

3. 启动开发服务器：
   ```bash
   npm run dev
   ```

4. 访问页面：
   -通常是 `http://localhost:3000`。
   - 注意：本地开发时，你需要确保前端配置了代理以连接到 `http://localhost:8000` 的后端，否则会出现跨域错误。

### 配置本地代理

在 `vite.config.js` 中添加代理配置 (如果尚未配置)：

```javascript
export default defineConfig({
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8000',
        changeOrigin: true,
      }
    }
  }
})
```

---

## 3. 常见问题

**Q: 端口被占用？**
- 后端：在 `backend/src/main/resources/application.properties` 中修改 `server.port`。
- 前端：在 `package.json` 的 scripts 中添加 `--port 3001` 或修改 `vite.config.js`。

**Q: 跨域错误 (CORS)？**
- 确保后端 `CorsConfig` 类允许了你的前端地址 (如 `http://localhost:3000`)。
