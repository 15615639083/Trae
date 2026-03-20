<template>
  <div class="home">
    <h1>知飞子 - 游戏资源与技术服务平台</h1>
    <p>欢迎来到知飞子平台，这里是游戏资源分享和技术服务的专业平台。</p>
    <div class="features">
      <div class="feature">
        <h2>资源分享</h2>
        <p>分享各种优质游戏资源，包括游戏安装包、补丁、工具等。</p>
        <router-link to="/resources">查看资源</router-link>
      </div>
      <div class="feature">
        <h2>技术服务市场</h2>
        <p>寻找专业的技术工程师为您提供游戏安装、BUG修复等服务。</p>
        <router-link to="/engineers">浏览工程师</router-link>
      </div>
      <div class="feature">
        <h2>在线接单</h2>
        <p>发布和承接各种技术任务，自由灵活地开展工作。</p>
        <router-link to="/orders">查看订单</router-link>
      </div>
      <div class="feature">
        <h2>后台管理</h2>
        <p>管理员可以管理平台资源、订单和用户，确保平台正常运行。</p>
        <router-link to="/admin">进入管理</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  font-size: 2.5em;
  margin-bottom: 20px;
  color: #333;
}

p {
  font-size: 1.2em;
  margin-bottom: 40px;
  color: #666;
}

.features {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

.feature {
  width: 300px;
  padding: 20px;
  margin: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.feature h2 {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #333;
}

.feature p {
  font-size: 1em;
  margin-bottom: 20px;
  color: #666;
}

.feature a {
  display: inline-block;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.feature a:hover {
  background-color: #45a049;
}
</style>