import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../components/Login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../components/Home.vue')
  },
  {
    path: '/resources',
    name: 'Resources',
    component: () => import('../components/Resources.vue')
  },
  {
    path: '/orders',
    name: 'Orders',
    component: () => import('../components/Orders.vue')
  },
  {
    path: '/engineers',
    name: 'EngineerMarket',
    component: () => import('../components/EngineerMarket.vue')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('../components/Admin.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 不需要登录的页面
  const publicPages = ['/login'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // 如果需要登录但未登录，重定向到登录页
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router