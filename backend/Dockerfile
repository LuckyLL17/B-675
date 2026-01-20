FROM php:8.2-apache

# 安装 SQLite 和其他依赖
RUN apt-get update && apt-get install -y \
    libsqlite3-dev \
    && docker-php-ext-install pdo pdo_sqlite \
    && rm -rf /var/lib/apt/lists/*

# 启用必要模块
RUN a2enmod rewrite headers

WORKDIR /var/www/html

# 复制 PHP 文件
COPY . .

# 创建数据目录并设置权限
RUN mkdir -p /var/www/html/data && chown -R www-data:www-data /var/www/html

EXPOSE 80
