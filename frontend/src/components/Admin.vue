<template>
  <div class="admin">
    <h1>后台管理系统</h1>
    <div class="admin-menu">
      <button class="menu-btn" @click="activeTab = 'dashboard'">控制台</button>
      <button class="menu-btn" @click="activeTab = 'users'">用户管理</button>
      <button class="menu-btn" @click="activeTab = 'engineers'">工程师管理</button>
      <button class="menu-btn" @click="activeTab = 'orders'">订单管理</button>
      <button class="menu-btn" @click="activeTab = 'resources'">资源管理</button>
      <button class="menu-btn" @click="activeTab = 'finance'">财务管理</button>
    </div>
    <div class="admin-content">
      <!-- 控制台 -->
      <div v-if="activeTab === 'dashboard'" class="tab-content">
        <h2>控制台</h2>
        <div class="dashboard-stats">
          <div class="stat-card">
            <h3>今日新增用户</h3>
            <p class="stat-value">{{ stats.todayUsers }}</p>
          </div>
          <div class="stat-card">
            <h3>今日订单数</h3>
            <p class="stat-value">{{ stats.todayOrders }}</p>
          </div>
          <div class="stat-card">
            <h3>待审核工程师</h3>
            <p class="stat-value">{{ stats.pendingEngineers }}</p>
          </div>
          <div class="stat-card">
            <h3>待处理投诉</h3>
            <p class="stat-value">{{ stats.pendingComplaints }}</p>
          </div>
        </div>
      </div>

      <!-- 用户管理 -->
      <div v-if="activeTab === 'users'" class="tab-content">
        <h2>用户管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>用户名</th>
              <th>邮箱</th>
              <th>手机号</th>
              <th>角色</th>
              <th>状态</th>
              <th>注册时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.username }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.phone || '未设置' }}</td>
              <td>{{ user.role }}</td>
              <td>{{ user.status }}</td>
              <td>{{ user.registerTime }}</td>
              <td>
                <button class="edit-btn">编辑</button>
                <button class="delete-btn">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 工程师管理 -->
      <div v-if="activeTab === 'engineers'" class="tab-content">
        <h2>工程师管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>用户ID</th>
              <th>真实姓名</th>
              <th>身份证号</th>
              <th>技能标签</th>
              <th>审核状态</th>
              <th>评分</th>
              <th>服务价格</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="engineer in engineers" :key="engineer.id">
              <td>{{ engineer.id }}</td>
              <td>{{ engineer.userId }}</td>
              <td>{{ engineer.realName }}</td>
              <td>{{ engineer.idCard }}</td>
              <td>{{ engineer.skills }}</td>
              <td>{{ engineer.verificationStatus }}</td>
              <td>{{ engineer.rating }}</td>
              <td>{{ engineer.servicePrice }} 元</td>
              <td>
                <button class="approve-btn" v-if="engineer.verificationStatus === 'pending'">通过</button>
                <button class="reject-btn" v-if="engineer.verificationStatus === 'pending'">驳回</button>
                <button class="edit-btn">编辑</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 订单管理 -->
      <div v-if="activeTab === 'orders'" class="tab-content">
        <h2>订单管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>用户ID</th>
              <th>工程师ID</th>
              <th>标题</th>
              <th>价格</th>
              <th>状态</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="order in orders" :key="order.id">
              <td>{{ order.id }}</td>
              <td>{{ order.userId }}</td>
              <td>{{ order.engineerId || '未指派' }}</td>
              <td>{{ order.title }}</td>
              <td>{{ order.price }} 元</td>
              <td>{{ order.status }}</td>
              <td>{{ order.createTime }}</td>
              <td>
                <button class="assign-btn" v-if="order.status === 'pending'">指派</button>
                <button class="edit-btn">编辑</button>
                <button class="delete-btn">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 资源管理 -->
      <div v-if="activeTab === 'resources'" class="tab-content">
        <h2>资源管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>标题</th>
              <th>描述</th>
              <th>作者</th>
              <th>文件路径</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="resource in resources" :key="resource.id">
              <td>{{ resource.id }}</td>
              <td>{{ resource.title }}</td>
              <td>{{ resource.description }}</td>
              <td>{{ resource.author }}</td>
              <td>{{ resource.fileUrl }}</td>
              <td>{{ resource.createTime }}</td>
              <td>
                <button class="edit-btn">编辑</button>
                <button class="delete-btn">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 财务管理 -->
      <div v-if="activeTab === 'finance'" class="tab-content">
        <h2>财务管理</h2>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>用户ID</th>
              <th>订单ID</th>
              <th>金额</th>
              <th>类型</th>
              <th>状态</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="transaction in transactions" :key="transaction.id">
              <td>{{ transaction.id }}</td>
              <td>{{ transaction.userId }}</td>
              <td>{{ transaction.orderId || '无' }}</td>
              <td>{{ transaction.amount }} 元</td>
              <td>{{ transaction.type }}</td>
              <td>{{ transaction.status }}</td>
              <td>{{ transaction.createdAt }}</td>
              <td>
                <button class="approve-btn" v-if="transaction.status === 'pending'">审核</button>
                <button class="edit-btn">编辑</button>
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
      activeTab: 'dashboard',
      stats: {
        todayUsers: 15,
        todayOrders: 8,
        pendingEngineers: 3,
        pendingComplaints: 1
      },
      users: [
        {
          id: 1,
          username: 'admin',
          email: 'admin@zhifeizi.com',
          phone: '13800138000',
          role: 'admin',
          status: 'active',
          registerTime: '2026-03-01'
        },
        {
          id: 2,
          username: 'user1',
          email: 'user1@zhifeizi.com',
          phone: '13900139000',
          role: 'user',
          status: 'active',
          registerTime: '2026-03-02'
        },
        {
          id: 3,
          username: 'engineer1',
          email: 'engineer1@zhifeizi.com',
          phone: '13700137000',
          role: 'engineer',
          status: 'active',
          registerTime: '2026-03-03'
        }
      ],
      engineers: [
        {
          id: 1,
          userId: 3,
          realName: '张工程师',
          idCard: '110101199001011234',
          skills: '游戏安装,BUG修复,性能优化',
          verificationStatus: 'approved',
          rating: 4.8,
          servicePrice: 200
        },
        {
          id: 2,
          userId: 4,
          realName: '李工程师',
          idCard: '110101199001011235',
          skills: '脚本定制,游戏安装',
          verificationStatus: 'pending',
          rating: 0,
          servicePrice: 150
        }
      ],
      orders: [
        {
          id: 1,
          userId: 2,
          engineerId: 3,
          title: '游戏安装服务',
          price: 200,
          status: 'completed',
          createTime: '2026-03-15'
        },
        {
          id: 2,
          userId: 2,
          engineerId: null,
          title: 'BUG修复服务',
          price: 300,
          status: 'pending',
          createTime: '2026-03-16'
        }
      ],
      resources: [
        {
          id: 1,
          title: 'Vue 3 入门教程',
          description: 'Vue 3 基础学习资料',
          author: '张三',
          fileUrl: '/uploads/vue3-tutorial.pdf',
          createTime: '2026-03-15'
        },
        {
          id: 2,
          title: 'Spring Boot 实战',
          description: 'Spring Boot 项目开发指南',
          author: '李四',
          fileUrl: '/uploads/spring-boot-guide.pdf',
          createTime: '2026-03-14'
        }
      ],
      transactions: [
        {
          id: 1,
          userId: 2,
          orderId: 1,
          amount: 200,
          type: 'pay',
          status: 'completed',
          createdAt: '2026-03-15'
        },
        {
          id: 2,
          userId: 3,
          orderId: 1,
          amount: 180,
          type: 'withdraw',
          status: 'pending',
          createdAt: '2026-03-16'
        }
      ]
    }
  }
}
</script>

<style scoped>
.admin {
  max-width: 1400px;
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
  flex-wrap: wrap;
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

.dashboard-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.stat-card h3 {
  font-size: 1em;
  color: #666;
  margin-bottom: 10px;
}

.stat-value {
  font-size: 2em;
  font-weight: bold;
  color: #333;
  margin: 0;
}

.admin-table {
  width: 100%;
  border-collapse: collapse;
  overflow-x: auto;
  display: block;
}

.admin-table th,
.admin-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  white-space: nowrap;
}

.admin-table th {
  background-color: #f2f2f2;
  font-weight: bold;
  position: sticky;
  top: 0;
  z-index: 1;
}

.edit-btn,
.delete-btn,
.approve-btn,
.reject-btn,
.assign-btn {
  padding: 5px 10px;
  margin-right: 5px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  font-size: 12px;
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

.approve-btn {
  background-color: #2196F3;
  color: white;
}

.approve-btn:hover {
  background-color: #0b7dda;
}

.reject-btn {
  background-color: #ff9800;
  color: white;
}

.reject-btn:hover {
  background-color: #e68a00;
}

.assign-btn {
  background-color: #9c27b0;
  color: white;
}

.assign-btn:hover {
  background-color: #7b1fa2;
}
</style>