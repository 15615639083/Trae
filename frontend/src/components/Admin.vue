<template>
  <div class="admin">
    <h1>后台管理</h1>
    <div class="admin-menu">
      <button class="menu-btn" @click="activeTab = 'resources'">资源管理</button>
      <button class="menu-btn" @click="activeTab = 'orders'">订单管理</button>
      <button class="menu-btn" @click="activeTab = 'users'">用户管理</button>
    </div>
    <div class="admin-content">
      <div v-if="activeTab === 'resources'" class="tab-content">
        <h2>资源管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>标题</th>
              <th>发布者</th>
              <th>发布时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="resource in resources" :key="resource.id">
              <td>{{ resource.id }}</td>
              <td>{{ resource.title }}</td>
              <td>{{ resource.author }}</td>
              <td>{{ resource.createTime }}</td>
              <td>
                <button class="edit-btn">编辑</button>
                <button class="delete-btn">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="activeTab === 'orders'" class="tab-content">
        <h2>订单管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>标题</th>
              <th>发布者</th>
              <th>价格</th>
              <th>发布时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="order in orders" :key="order.id">
              <td>{{ order.id }}</td>
              <td>{{ order.title }}</td>
              <td>{{ order.publisher }}</td>
              <td>{{ order.price }} 元</td>
              <td>{{ order.createTime }}</td>
              <td>
                <button class="edit-btn">编辑</button>
                <button class="delete-btn">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="activeTab === 'users'" class="tab-content">
        <h2>用户管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>用户名</th>
              <th>邮箱</th>
              <th>注册时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.username }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.registerTime }}</td>
              <td>
                <button class="edit-btn">编辑</button>
                <button class="delete-btn">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Admin',
  data() {
    return {
      activeTab: 'resources',
      resources: [
        {
          id: 1,
          title: 'Vue 3 入门教程',
          author: '张三',
          createTime: '2026-03-15'
        },
        {
          id: 2,
          title: 'Spring Boot 实战',
          author: '李四',
          createTime: '2026-03-14'
        },
        {
          id: 3,
          title: 'MySQL 优化技巧',
          author: '王五',
          createTime: '2026-03-13'
        }
      ],
      orders: [
        {
          id: 1,
          title: 'Vue 项目开发',
          publisher: '赵六',
          price: 5000,
          createTime: '2026-03-15'
        },
        {
          id: 2,
          title: 'Spring Boot 后端开发',
          publisher: '孙七',
          price: 8000,
          createTime: '2026-03-14'
        },
        {
          id: 3,
          title: 'MySQL 数据库设计',
          publisher: '周八',
          price: 3000,
          createTime: '2026-03-13'
        }
      ],
      users: [
        {
          id: 1,
          username: 'admin',
          email: 'admin@zhifeizi.com',
          registerTime: '2026-03-01'
        },
        {
          id: 2,
          username: 'user1',
          email: 'user1@zhifeizi.com',
          registerTime: '2026-03-02'
        },
        {
          id: 3,
          username: 'user2',
          email: 'user2@zhifeizi.com',
          registerTime: '2026-03-03'
        }
      ]
    }
  }
}
</script>

<style scoped>
.admin {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  font-size: 2em;
  margin-bottom: 30px;
  color: #333;
}

.admin-menu {
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
}

.menu-btn {
  padding: 10px 20px;
  background-color: #f0f0f0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.menu-btn:hover {
  background-color: #e0e0e0;
}

.menu-btn:active {
  background-color: #d0d0d0;
}

.tab-content {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.tab-content h2 {
  font-size: 1.5em;
  margin-bottom: 20px;
  color: #333;
}

.admin-table {
  width: 100%;
  border-collapse: collapse;
}

.admin-table th,
.admin-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.admin-table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.edit-btn,
.delete-btn {
  padding: 5px 10px;
  margin-right: 5px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.edit-btn {
  background-color: #4CAF50;
  color: white;
}

.edit-btn:hover {
  background-color: #45a049;
}

.delete-btn {
  background-color: #f44336;
  color: white;
}

.delete-btn:hover {
  background-color: #da190b;
}
</style>