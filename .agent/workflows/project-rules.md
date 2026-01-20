---
description: 项目交付规则和标准 - 所有项目必须遵循
---

# 项目交付规则和标准
项目默认语言统一使用中文，如需求有特殊要求，按需求优先。

## 1. 目录结构 (必须遵守)

```
Project_Root/
├── README.md           # [必填] 项目说明书
├── docker-compose.yml  # [必填] 核心启动文件
├── frontend/           # 前端代码 (含 Dockerfile)
│   ├── Dockerfile
│   ├── src/
│   │   ├── components/ # 组件化拆分
│   │   └── pages/
│   └── package.json
├── backend/            # 后端代码 (含 Dockerfile)
│   ├── Dockerfile
│   ├── app/
│   │   ├── models.py   # MVC 分层
│   │   └── main.py
│   └── requirements.txt
└── ... (其他工程文件)
```

## 2. Docker 交付标准

### 端口映射
- 前端默认映射到宿主机 `3000`
- 后端映射到 `8000`
- **必须在 README 中写明端口**

### 零依赖原则
- **严禁**要求 QA 在本地安装 Node, Python, Java
- 所有依赖必须打入镜像
- 用户只需要 Docker Desktop 即可运行

### 数据持久化
- 数据库使用 Docker Volume 或 SQLite
- 确保重启容器数据不丢失

## 3. README.md 模板

```markdown
# [项目名称]

## 🛠 技术栈
- Frontend: React + Tailwind
- Backend: Python FastAPI
- DB: PostgreSQL / SQLite

## 🚀 快速启动 (Docker)
1. 确保 Docker Desktop 已运行。
2. 在根目录执行：`docker compose up --build`
3. 访问前端：http://localhost:3000
4. 访问后端文档：http://localhost:8000/docs

## 🧪 测试账号 (如有)
- 用户名: admin
- 密码: password123

## 📸 功能介绍
[功能截图和说明]
```

## 4. 代码质量要求

### 前端
- 使用 `components/`、`pages/` 等目录进行组件化拆分
- 禁止所有代码写在一个文件里

### 后端
- 使用 MVC 分层结构：`models.py`、`routes/`、`services/` 等
- 禁止所有代码写在 `app.py` 一个文件里

## 5. UI/美观度要求 (重要!)

### 必须实现
- ✅ 使用 **Tailwind CSS**
- ✅ 卡片式设计，有阴影、圆角
- ✅ 配色舒适协调（禁止原生 HTML 样式）
- ✅ 按钮有 **Hover 效果**
- ✅ 加载数据时有 **Loading 转圈**
- ✅ 操作成功有 **Toast 提示**
- ✅ **移动端适配**（响应式设计）
- ✅ 整体要有**商业交付/SaaS MVP**的质感

### 设计原则
- 使用现代字体（如 Google Fonts: Inter, Roboto, Outfit）
- 使用平滑渐变
- 添加微动画增强用户体验
- 避免使用原生/普通颜色，使用协调的配色方案

## 6. 禁止事项 ❌

| 禁止项 | 说明 |
|--------|------|
| `node_modules` | 压缩包严禁包含 |
| `venv` / `.venv` | 压缩包严禁包含 |
| `.git` | 压缩包严禁包含 |
| 本地依赖安装 | README 不能要求用户安装 Node/Python/Java |
| 单文件代码 | 代码不能全写在一个文件里 |
| 丑陋 UI | 按钮歪斜、输入框没对齐、不适配移动端 |

## 7. 提交物清单

- [ ] 代码包 (.zip)：剔除 node_modules, venv, .git
- [ ] 运行录屏/截图：docker compose up + 浏览器演示
- [ ] 对话轨迹：AI 对话记录 (PDF/Markdown/JSON)

## 8. 自测检查项

在提交前必须确认：

- [ ] `docker compose up --build` 能正常启动
- [ ] 前端访问 http://localhost:3000 正常
- [ ] 后端访问 http://localhost:8000/docs 正常
- [ ] UI 美观、移动端适配
- [ ] 代码结构清晰、有分层
- [ ] README 完整、无本地依赖要求