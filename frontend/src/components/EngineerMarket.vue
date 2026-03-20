<template>
  <div class="engineer-market">
    <h1>技术服务市场</h1>
    <p>寻找专业的技术工程师为您提供服务</p>
    
    <!-- 服务分类 -->
    <div class="service-categories">
      <h2>服务分类</h2>
      <div class="category-list">
        <button 
          v-for="category in categories" 
          :key="category.id"
          class="category-btn"
          :class="{ active: selectedCategory === category.id }"
          @click="selectedCategory = category.id"
        >
          {{ category.name }}
        </button>
      </div>
    </div>
    
    <!-- 工程师列表 -->
    <div class="engineer-list">
      <h2>推荐工程师</h2>
      <div class="engineers-grid">
        <div 
          v-for="engineer in filteredEngineers" 
          :key="engineer.id"
          class="engineer-card"
        >
          <div class="engineer-avatar">
            <img :src="engineer.avatar" :alt="engineer.realName" />
          </div>
          <h3>{{ engineer.realName }}</h3>
          <div class="engineer-skills">
            <span 
              v-for="skill in engineer.skills.split(',')" 
              :key="skill"
              class="skill-tag"
            >
              {{ skill }}
            </span>
          </div>
          <div class="engineer-stats">
            <div class="stat-item">
              <span class="stat-label">评分</span>
              <span class="stat-value">{{ engineer.rating }}</span>
            </div>
            <div class="stat-item">
              <span class="stat-label">接单量</span>
              <span class="stat-value">{{ engineer.orderCount }}</span>
            </div>
            <div class="stat-item">
              <span class="stat-label">服务价格</span>
              <span class="stat-value">{{ engineer.servicePrice }}元/次</span>
            </div>
          </div>
          <div class="engineer-status">
            <span 
              class="status-badge" 
              :class="engineer.status"
            >
              {{ engineer.status === 'online' ? '在线' : engineer.status === 'busy' ? '忙碌' : '离线' }}
            </span>
          </div>
          <button class="contact-btn">联系工程师</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EngineerMarket',
  data() {
    return {
      selectedCategory: null,
      categories: [
        { id: 1, name: '游戏安装' },
        { id: 2, name: 'BUG修复' },
        { id: 3, name: '脚本定制' },
        { id: 4, name: '性能优化' }
      ],
      engineers: [
        {
          id: 1,
          realName: '张工程师',
          avatar: 'https://via.placeholder.com/100',
          skills: '游戏安装,BUG修复,性能优化',
          rating: 4.8,
          orderCount: 156,
          servicePrice: 200,
          status: 'online'
        },
        {
          id: 2,
          realName: '李工程师',
          avatar: 'https://via.placeholder.com/100',
          skills: '脚本定制,游戏安装',
          rating: 4.5,
          orderCount: 89,
          servicePrice: 150,
          status: 'busy'
        },
        {
          id: 3,
          realName: '王工程师',
          avatar: 'https://via.placeholder.com/100',
          skills: 'BUG修复,性能优化',
          rating: 4.9,
          orderCount: 203,
          servicePrice: 250,
          status: 'online'
        },
        {
          id: 4,
          realName: '赵工程师',
          avatar: 'https://via.placeholder.com/100',
          skills: '游戏安装,脚本定制',
          rating: 4.6,
          orderCount: 124,
          servicePrice: 180,
          status: 'offline'
        }
      ]
    }
  },
  computed: {
    filteredEngineers() {
      if (!this.selectedCategory) {
        return this.engineers;
      }
      // 根据选择的分类过滤工程师
      const categoryName = this.categories.find(cat => cat.id === this.selectedCategory).name;
      return this.engineers.filter(engineer => 
        engineer.skills.includes(categoryName)
      );
    }
  }
}
</script>

<style scoped>
.engineer-market {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  font-size: 2.5em;
  margin-bottom: 20px;
  color: #333;
  text-align: center;
}

p {
  font-size: 1.2em;
  margin-bottom: 40px;
  color: #666;
  text-align: center;
}

.service-categories {
  margin-bottom: 40px;
}

.service-categories h2 {
  font-size: 1.5em;
  margin-bottom: 20px;
  color: #333;
}

.category-list {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.category-btn {
  padding: 10px 20px;
  background-color: #f0f0f0;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.3s;
  font-size: 14px;
}

.category-btn:hover {
  background-color: #e0e0e0;
}

.category-btn.active {
  background-color: #4CAF50;
  color: white;
}

.engineer-list h2 {
  font-size: 1.5em;
  margin-bottom: 20px;
  color: #333;
}

.engineers-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.engineer-card {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
  text-align: center;
}

.engineer-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.engineer-avatar {
  margin-bottom: 15px;
}

.engineer-avatar img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.engineer-card h3 {
  font-size: 1.2em;
  margin-bottom: 10px;
  color: #333;
}

.engineer-skills {
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
  justify-content: center;
  margin-bottom: 15px;
}

.skill-tag {
  padding: 3px 10px;
  background-color: #e8f5e8;
  color: #4CAF50;
  border-radius: 12px;
  font-size: 12px;
}

.engineer-stats {
  display: flex;
  justify-content: space-around;
  margin-bottom: 15px;
  padding: 10px;
  background-color: #f0f0f0;
  border-radius: 4px;
}

.stat-item {
  text-align: center;
}

.stat-label {
  display: block;
  font-size: 12px;
  color: #666;
  margin-bottom: 5px;
}

.stat-value {
  display: block;
  font-size: 14px;
  font-weight: bold;
  color: #333;
}

.engineer-status {
  margin-bottom: 15px;
}

.status-badge {
  padding: 3px 10px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: bold;
}

.status-badge.online {
  background-color: #e8f5e8;
  color: #4CAF50;
}

.status-badge.busy {
  background-color: #fff3e0;
  color: #ff9800;
}

.status-badge.offline {
  background-color: #f3e5f5;
  color: #9c27b0;
}

.contact-btn {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  width: 100%;
}

.contact-btn:hover {
  background-color: #45a049;
}
</style>