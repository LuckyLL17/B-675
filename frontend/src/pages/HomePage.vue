<template>
  <div class="min-h-screen flex items-center justify-center p-4 md:p-8">
    <!-- 背景装饰 -->
    <div class="fixed inset-0 overflow-hidden pointer-events-none">
      <div class="absolute -top-40 -right-40 w-80 h-80 bg-primary-500/20 rounded-full blur-3xl"></div>
      <div class="absolute -bottom-40 -left-40 w-80 h-80 bg-purple-500/20 rounded-full blur-3xl"></div>
    </div>
    
    <!-- 主内容卡片 -->
    <div class="relative w-full max-w-lg">
      <!-- Logo 和标题 -->
      <div class="text-center mb-8">
        <div class="inline-flex items-center justify-center w-16 h-16 mb-4 rounded-2xl bg-gradient-to-br from-primary-400 to-primary-600 shadow-lg shadow-primary-500/30">
          <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z"></path>
          </svg>
        </div>
        <h1 class="text-3xl md:text-4xl font-bold text-white mb-2">Echo Link</h1>
        <p class="text-slate-400">发送消息到服务器控制台</p>
      </div>
      
      <!-- 消息表单 -->
      <MessageForm />
    </div>
  </div>
</template>

<script setup>
import MessageForm from '../components/MessageForm.vue'
</script>
