<template>
  <div class="boss-login-container">
    <div class="boss-header">
      <h1>纸飞资</h1>
    </div>
    <div class="boss-login-content">
      <div class="boss-login-left">
        <div class="boss-login-slogan">
          <h2>海量优质工程师，在线约单</h2>
          <p>找服务，纸飞资直接谈</p>
        </div>
        <div class="boss-login-illustration">
          <!-- 插画区域 -->
        </div>
      </div>
      <div class="boss-login-right">
        <div class="boss-login-form">
          <div class="boss-login-tabs">
            <button 
              class="boss-tab" 
              :class="{ active: activeTab === 'login' }"
              @click="activeTab = 'login'"
            >
              登录
            </button>
            <button 
              class="boss-tab" 
              :class="{ active: activeTab === 'register' }"
              @click="activeTab = 'register'"
            >
              注册
            </button>
          </div>
          
          <!-- 登录表单 -->
          <form v-if="activeTab === 'login'" @submit.prevent="handleLogin">
            <div class="boss-form-group">
              <input 
                type="text" 
                v-model="loginForm.username" 
                placeholder="手机号/用户名" 
                required
              >
            </div>
            <div class="boss-form-group">
              <input 
                type="password" 
                v-model="loginForm.password" 
                placeholder="密码" 
                required
              >
            </div>
            <div class="boss-form-group">
              <select v-model="loginForm.role">
                <option value="user">普通用户</option>
                <option value="engineer">工程师</option>
                <option value="admin">管理员</option>
              </select>
            </div>
            <button type="submit" class="boss-login-button">登录</button>
            <div class="boss-login-footer">
              <a href="#">忘记密码？</a>
            </div>
          </form>
          
          <!-- 注册表单 -->
          <form v-else @submit.prevent="handleRegister">
            <div class="boss-form-group">
              <div class="boss-phone-input">
                <span class="boss-country-code">+86</span>
                <input 
                  type="text" 
                  v-model="registerForm.phone" 
                  placeholder="手机号" 
                  required
                >
              </div>
            </div>
            <div class="boss-form-group">
              <input 
                type="text" 
                v-model="registerForm.code" 
                placeholder="验证码" 
                required
              >
              <button type="button" class="boss-send-code">发送验证码</button>
            </div>
            <div class="boss-form-group">
              <input 
                type="password" 
                v-model="registerForm.password" 
                placeholder="设置密码" 
                required
              >
            </div>
            <div class="boss-form-group">
              <select v-model="registerForm.role">
                <option value="user">普通用户</option>
                <option value="engineer">工程师</option>
              </select>
            </div>
            <button type="submit" class="boss-register-button">注册</button>
          </form>
          
          <div class="boss-agreement">
            <input type="checkbox" id="agreement" v-model="agreementChecked">
            <label for="agreement">
              已阅读并同意<a href="#">《用户协议》</a>和<a href="#">《隐私政策》</a>
            </label>
          </div>
          
          <div class="boss-login-divider">
            <span>其他登录方式</span>
          </div>
          
          <div class="boss-other-login">
            <button class="boss-wechat-login">微信登录</button>
            <button class="boss-qq-login">QQ登录</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      activeTab: 'login',
      loginForm: {
        username: '',
        password: '',
        role: 'user'
      },
      registerForm: {
        phone: '',
        code: '',
        password: '',
        role: 'user'
      },
      agreementChecked: false,
      errorMessage: ''
    }
  },
  methods: {
    handleLogin() {
      // 这里应该调用登录API
      console.log('登录信息:', this.loginForm);
      // 模拟登录成功
      localStorage.setItem('user', JSON.stringify(this.loginForm));
      // 根据角色跳转到不同页面
      if (this.loginForm.role === 'admin') {
        this.$router.push('/admin');
      } else if (this.loginForm.role === 'engineer') {
        this.$router.push('/engineers');
      } else {
        this.$router.push('/home');
      }
    },
    handleRegister() {
      // 这里应该调用注册API
      console.log('注册信息:', this.registerForm);
      // 模拟注册成功
      localStorage.setItem('user', JSON.stringify(this.registerForm));
      // 根据角色跳转到不同页面
      if (this.registerForm.role === 'engineer') {
        this.$router.push('/engineers');
      } else {
        this.$router.push('/home');
      }
    }
  }
}
</script>

<style scoped>
/* 全局样式重置 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.boss-login-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #00b4db, #0083b0);
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
}

.boss-header {
  padding: 20px 40px;
  background-color: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
}

.boss-header h1 {
  color: white;
  font-size: 24px;
  font-weight: bold;
}

.boss-login-content {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 70px);
  padding: 40px;
}

.boss-login-left {
  flex: 1;
  max-width: 500px;
  color: white;
  padding-right: 60px;
}

.boss-login-slogan h2 {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 16px;
  line-height: 1.2;
}

.boss-login-slogan p {
  font-size: 18px;
  opacity: 0.9;
  margin-bottom: 40px;
}

.boss-login-illustration {
  height: 300px;
  background-image: url('https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=illustration%20of%20people%20working%20on%20computers%20with%20chat%20bubbles%20and%20work%20tasks%2C%20modern%20flat%20design%2C%20blue%20and%20green%20color%20scheme&image_size=landscape_16_9');
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
}

.boss-login-right {
  flex: 1;
  max-width: 400px;
}

.boss-login-form {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.boss-login-tabs {
  display: flex;
  margin-bottom: 30px;
  border-bottom: 1px solid #f0f0f0;
}

.boss-tab {
  flex: 1;
  padding: 12px 0;
  background: none;
  border: none;
  font-size: 16px;
  font-weight: 500;
  color: #666;
  cursor: pointer;
  transition: all 0.3s;
  border-bottom: 2px solid transparent;
}

.boss-tab.active {
  color: #00b4db;
  border-bottom-color: #00b4db;
}

.boss-form-group {
  margin-bottom: 20px;
  position: relative;
}

.boss-form-group input,
.boss-form-group select {
  width: 100%;
  padding: 14px 16px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  font-size: 16px;
  transition: all 0.3s;
}

.boss-form-group input:focus,
.boss-form-group select:focus {
  outline: none;
  border-color: #00b4db;
  box-shadow: 0 0 0 2px rgba(0, 180, 219, 0.2);
}

.boss-phone-input {
  display: flex;
  align-items: center;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  transition: all 0.3s;
}

.boss-phone-input:focus-within {
  border-color: #00b4db;
  box-shadow: 0 0 0 2px rgba(0, 180, 219, 0.2);
}

.boss-country-code {
  padding: 0 16px;
  border-right: 1px solid #e0e0e0;
  color: #666;
}

.boss-phone-input input {
  border: none;
  flex: 1;
}

.boss-phone-input input:focus {
  box-shadow: none;
}

.boss-send-code {
  position: absolute;
  right: 8px;
  top: 50%;
  transform: translateY(-50%);
  background-color: #f0f0f0;
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  font-size: 14px;
  color: #666;
  cursor: pointer;
  transition: all 0.3s;
}

.boss-send-code:hover {
  background-color: #e0e0e0;
}

.boss-login-button,
.boss-register-button {
  width: 100%;
  padding: 14px;
  background-color: #00b4db;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s;
  margin-bottom: 16px;
}

.boss-login-button:hover,
.boss-register-button:hover {
  background-color: #00a0c0;
}

.boss-login-footer {
  text-align: right;
  margin-bottom: 20px;
}

.boss-login-footer a {
  color: #00b4db;
  text-decoration: none;
  font-size: 14px;
}

.boss-login-footer a:hover {
  text-decoration: underline;
}

.boss-agreement {
  font-size: 12px;
  color: #999;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.boss-agreement input {
  margin-right: 8px;
}

.boss-agreement a {
  color: #00b4db;
  text-decoration: none;
}

.boss-agreement a:hover {
  text-decoration: underline;
}

.boss-login-divider {
  position: relative;
  text-align: center;
  margin: 20px 0;
  color: #999;
  font-size: 14px;
}

.boss-login-divider::before,
.boss-login-divider::after {
  content: '';
  position: absolute;
  top: 50%;
  width: 40%;
  height: 1px;
  background-color: #e0e0e0;
}

.boss-login-divider::before {
  left: 0;
}

.boss-login-divider::after {
  right: 0;
}

.boss-other-login {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.boss-wechat-login,
.boss-qq-login {
  padding: 10px 20px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  background-color: white;
  color: #666;
  cursor: pointer;
  transition: all 0.3s;
  display: flex;
  align-items: center;
  gap: 8px;
}

.boss-wechat-login:hover,
.boss-qq-login:hover {
  border-color: #00b4db;
  color: #00b4db;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .boss-login-content {
    flex-direction: column;
    padding: 20px;
  }
  
  .boss-login-left {
    padding-right: 0;
    margin-bottom: 40px;
    text-align: center;
  }
  
  .boss-login-slogan h2 {
    font-size: 28px;
  }
  
  .boss-login-form {
    padding: 30px;
  }
}
</style>