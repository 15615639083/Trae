import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
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
    path: '/admin',
    name: 'Admin',
    component: () => import('../components/Admin.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router