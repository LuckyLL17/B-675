<template>
  <div class="glass rounded-2xl p-6 md:p-8 shadow-2xl">
    <form @submit.prevent="sendMessage" class="space-y-6">
      <!-- 输入区域 -->
      <div class="space-y-2">
        <label for="message" class="block text-sm font-medium text-slate-300">
          消息内容
        </label>
        <div class="relative">
          <textarea
            id="message"
            v-model="message"
            rows="4"
            :disabled="loading"
            class="w-full px-4 py-3 bg-slate-800/50 border border-slate-600/50 rounded-xl text-white placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent transition-all duration-200 resize-none disabled:opacity-50"
            placeholder="输入您想发送的消息..."
          ></textarea>
          <!-- 字符计数 -->
          <span class="absolute bottom-3 right-3 text-xs text-slate-500">
            {{ message.length }} 字符
          </span>
        </div>
      </div>
      
      <!-- 发送按钮 -->
      <button
        type="submit"
        :disabled="loading || !message.trim()"
        class="group relative w-full py-3.5 px-6 rounded-xl font-semibold text-white overflow-hidden transition-all duration-300 disabled:opacity-50 disabled:cursor-not-allowed"
        :class="loading ? 'bg-slate-600' : 'bg-gradient-to-r from-primary-500 to-primary-600 hover:from-primary-600 hover:to-primary-700 hover:shadow-lg hover:shadow-primary-500/30 hover:-translate-y-0.5'"
      >
        <!-- 加载动画 -->
        <span v-if="loading" class="flex items-center justify-center gap-2">
          <svg class="animate-spin w-5 h-5" fill="none" viewBox="0 0 24 24">
            <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
            <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
          </svg>
          发送中...
        </span>
        <span v-else class="flex items-center justify-center gap-2">
          <svg class="w-5 h-5 transition-transform group-hover:translate-x-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6"></path>
          </svg>
          发送消息
        </span>
      </button>
    </form>
    
    <!-- Toast 提示 -->
    <Transition
      enter-active-class="transition-all duration-300 ease-out"
      enter-from-class="opacity-0 translate-y-4"
      enter-to-class="opacity-100 translate-y-0"
      leave-active-class="transition-all duration-200 ease-in"
      leave-from-class="opacity-100 translate-y-0"
      leave-to-class="opacity-0 translate-y-4"
    >
      <div
        v-if="showToast"
        class="fixed bottom-6 left-1/2 -translate-x-1/2 flex items-center gap-3 px-5 py-3 rounded-xl shadow-lg"
        :class="toastType === 'success' ? 'bg-emerald-500' : 'bg-red-500'"
      >
        <!-- 成功图标 -->
        <svg v-if="toastType === 'success'" class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
        </svg>
        <!-- 错误图标 -->
        <svg v-else class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
        </svg>
        <span class="text-white font-medium">{{ toastMessage }}</span>
      </div>
    </Transition>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const message = ref('')
const loading = ref(false)
const showToast = ref(false)
const toastMessage = ref('')
const toastType = ref('success')

// 显示 Toast 提示
const showNotification = (msg, type = 'success') => {
  toastMessage.value = msg
  toastType.value = type
  showToast.value = true
  setTimeout(() => {
    showToast.value = false
  }, 3000)
}

// 发送消息
const sendMessage = async () => {
  if (!message.value.trim() || loading.value) return
  
  loading.value = true
  
  try {
    const response = await fetch('/api/message', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ content: message.value })
    })
    
    if (!response.ok) {
      throw new Error('请求失败')
    }
    
    const data = await response.json()
    
    if (data.success) {
      showNotification('消息发送成功！')
      message.value = ''
    } else {
      showNotification('发送失败，请重试', 'error')
    }
  } catch (error) {
    console.error('发送失败:', error)
    showNotification('网络错误，请检查连接', 'error')
  } finally {
    loading.value = false
  }
}
</script>
