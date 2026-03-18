<template>
  <div class="resources">
    <h1>资源分享</h1>
    <div class="resource-list">
      <div class="resource-item" v-for="resource in resources" :key="resource.id">
        <h2>{{ resource.title }}</h2>
        <p>{{ resource.description }}</p>
        <p class="resource-meta">发布者: {{ resource.author }} | 发布时间: {{ resource.createTime }}</p>
        <a href="#" class="download-btn">下载</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Resources',
  data() {
    return {
      resources: [
        {
          id: 1,
          title: 'Vue 3 入门教程',
          description: '详细介绍 Vue 3 的核心概念和使用方法',
          author: '张三',
          createTime: '2026-03-15'
        },
        {
          id: 2,
          title: 'Spring Boot 实战',
          description: 'Spring Boot 项目开发实战指南',
          author: '李四',
          createTime: '2026-03-14'
        },
        {
          id: 3,
          title: 'MySQL 优化技巧',
          description: 'MySQL 数据库性能优化的实用技巧',
          author: '王五',
          createTime: '2026-03-13'
        }
      ]
    }
  }
}
</script>

<style scoped>
.resources {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  font-size: 2em;
  margin-bottom: 30px;
  color: #333;
}

.resource-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.resource-item {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: left;
}

.resource-item h2 {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #333;
}

.resource-item p {
  margin-bottom: 10px;
  color: #666;
}

.resource-meta {
  font-size: 0.9em;
  color: #999;
  margin-bottom: 15px;
}

.download-btn {
  display: inline-block;
  padding: 8px 16px;
  background-color: #4CAF50;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.download-btn:hover {
  background-color: #45a049;
}
</style>