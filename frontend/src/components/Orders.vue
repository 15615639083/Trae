<template>
  <div class="orders">
    <h1>在线接单</h1>
    <div class="order-list">
      <div class="order-item" v-for="order in orders" :key="order.id">
        <h2>{{ order.title }}</h2>
        <p>{{ order.description }}</p>
        <p class="order-meta">发布者: {{ order.publisher }} | 发布时间: {{ order.createTime }} | 价格: {{ order.price }} 元</p>
        <button class="accept-btn">承接任务</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Orders',
  data() {
    return {
      orders: [
        {
          id: 1,
          title: 'Vue 项目开发',
          description: '开发一个基于 Vue 3 的前端项目，包含用户登录、数据展示等功能',
          publisher: '赵六',
          createTime: '2026-03-15',
          price: 5000
        },
        {
          id: 2,
          title: 'Spring Boot 后端开发',
          description: '开发一个基于 Spring Boot 的后端 API，包含用户管理、数据存储等功能',
          publisher: '孙七',
          createTime: '2026-03-14',
          price: 8000
        },
        {
          id: 3,
          title: 'MySQL 数据库设计',
          description: '设计一个完整的 MySQL 数据库 schema，包含表结构、索引优化等',
          publisher: '周八',
          createTime: '2026-03-13',
          price: 3000
        }
      ]
    }
  }
}
</script>

<style scoped>
.orders {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  font-size: 2em;
  margin-bottom: 30px;
  color: #333;
}

.order-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.order-item {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: left;
}

.order-item h2 {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #333;
}

.order-item p {
  margin-bottom: 10px;
  color: #666;
}

.order-meta {
  font-size: 0.9em;
  color: #999;
  margin-bottom: 15px;
}

.accept-btn {
  padding: 8px 16px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.accept-btn:hover {
  background-color: #45a049;
}
</style>