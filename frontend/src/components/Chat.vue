<template>
  <div class="chat">
    <div class="chat-header">
      <h2>聊天窗口</h2>
      <div class="chat-info">
        <span class="chat-name">{{ chatPartnerName }}</span>
        <span class="chat-status" :class="chatPartnerStatus">{{ chatPartnerStatus === 'online' ? '在线' : chatPartnerStatus === 'busy' ? '忙碌' : '离线' }}</span>
      </div>
    </div>
    <div class="chat-messages">
      <div 
        v-for="message in messages" 
        :key="message.id"
        class="message"
        :class="message.senderId === currentUserId ? 'message-outgoing' : 'message-incoming'"
      >
        <div class="message-content">
          <p>{{ message.content }}</p>
          <span class="message-time">{{ formatTime(message.timestamp) }}</span>
        </div>
      </div>
    </div>
    <div class="chat-input">
      <input 
        type="text" 
        v-model="messageInput" 
        placeholder="输入消息..."
        @keyup.enter="sendMessage"
      />
      <button class="send-btn" @click="sendMessage">发送</button>
      <input 
        type="file" 
        accept="image/*"
        @change="handleFileUpload"
        class="file-input"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Chat',
  props: {
    orderId: {
      type: Number,
      required: true
    },
    currentUserId: {
      type: Number,
      required: true
    },
    chatPartnerId: {
      type: Number,
      required: true
    },
    chatPartnerName: {
      type: String,
      default: '对方'
    },
    chatPartnerStatus: {
      type: String,
      default: 'offline'
    }
  },
  data() {
    return {
      messageInput: '',
      messages: [
        {
          id: 1,
          orderId: this.orderId,
          senderId: this.chatPartnerId,
          content: '你好，我是您的专属工程师，有什么可以帮助您的吗？',
          type: 'text',
          timestamp: new Date().toISOString()
        },
        {
          id: 2,
          orderId: this.orderId,
          senderId: this.currentUserId,
          content: '你好，我需要安装一款游戏，请问你能帮我吗？',
          type: 'text',
          timestamp: new Date().toISOString()
        },
        {
          id: 3,
          orderId: this.orderId,
          senderId: this.chatPartnerId,
          content: '当然可以，请问是什么游戏呢？',
          type: 'text',
          timestamp: new Date().toISOString()
        }
      ]
    }
  },
  mounted() {
    // 加载历史消息
    this.loadMessages();
  },
  methods: {
    loadMessages() {
      // 这里应该调用API获取历史消息
      // 暂时使用模拟数据
    },
    sendMessage() {
      if (this.messageInput.trim() === '') return;
      
      const newMessage = {
        id: Date.now(),
        orderId: this.orderId,
        senderId: this.currentUserId,
        content: this.messageInput,
        type: 'text',
        timestamp: new Date().toISOString()
      };
      
      this.messages.push(newMessage);
      this.messageInput = '';
      
      // 这里应该调用API发送消息
      console.log('发送消息:', newMessage);
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        // 这里应该处理文件上传
        console.log('上传文件:', file);
        // 模拟发送图片消息
        const newMessage = {
          id: Date.now(),
          orderId: this.orderId,
          senderId: this.currentUserId,
          content: '[图片]',
          type: 'image',
          timestamp: new Date().toISOString()
        };
        this.messages.push(newMessage);
      }
    },
    formatTime(timestamp) {
      const date = new Date(timestamp);
      return date.toLocaleTimeString();
    }
  }
}
</script>

<style scoped>
.chat {
  width: 100%;
  max-width: 600px;
  height: 500px;
  border: 1px solid #ddd;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.chat-header {
  padding: 15px;
  border-bottom: 1px solid #ddd;
  background-color: #f5f5f5;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chat-header h2 {
  font-size: 1.2em;
  margin: 0;
  color: #333;
}

.chat-info {
  display: flex;
  align-items: center;
  gap: 10px;
}

.chat-name {
  font-weight: bold;
  color: #333;
}

.chat-status {
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 12px;
  font-weight: bold;
}

.chat-status.online {
  background-color: #e8f5e8;
  color: #4CAF50;
}

.chat-status.busy {
  background-color: #fff3e0;
  color: #ff9800;
}

.chat-status.offline {
  background-color: #f3e5f5;
  color: #9c27b0;
}

.chat-messages {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
  background-color: #f9f9f9;
}

.message {
  margin-bottom: 15px;
  max-width: 70%;
  animation: fadeIn 0.3s ease;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(10px); }
  to { opacity: 1; transform: translateY(0); }
}

.message-incoming {
  align-self: flex-start;
}

.message-outgoing {
  align-self: flex-end;
}

.message-content {
  padding: 10px 15px;
  border-radius: 18px;
  position: relative;
}

.message-incoming .message-content {
  background-color: white;
  border-bottom-left-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.message-outgoing .message-content {
  background-color: #4CAF50;
  color: white;
  border-bottom-right-radius: 4px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.message-content p {
  margin: 0 0 5px 0;
  line-height: 1.4;
}

.message-time {
  font-size: 10px;
  opacity: 0.7;
  display: block;
  text-align: right;
}

.chat-input {
  padding: 15px;
  border-top: 1px solid #ddd;
  background-color: #f5f5f5;
  display: flex;
  gap: 10px;
  align-items: center;
}

.chat-input input[type="text"] {
  flex: 1;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 20px;
  font-size: 14px;
  outline: none;
  transition: border-color 0.3s;
}

.chat-input input[type="text"]:focus {
  border-color: #4CAF50;
}

.send-btn {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s;
  font-size: 14px;
}

.send-btn:hover {
  background-color: #45a049;
}

.file-input {
  display: none;
}

.file-input-label {
  padding: 10px;
  background-color: #e0e0e0;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  transition: background-color 0.3s;
}

.file-input-label:hover {
  background-color: #d0d0d0;
}
</style>