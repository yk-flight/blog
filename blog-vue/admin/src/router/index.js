import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export const constantRoutes = [
  {
    path: '/login',
    name: 'Login',
    hidden: true,
    component: () => import('../views/login/Login.vue')
  },
  {
    path: '/404',
    component: () => import('../views/system/error/404/index.vue'),
    hidden: true,
    meta: {
      title: '404 Not Found'
    }
  },
  {
    path: '/401',
    component: () => import('../views/system/error/401/index.vue'),
    hidden: true
  },
  {
    path: '/',
    redirect: 'home',
    meta: {
      title: '仪表盘',
      icon: 'dashboard'
    },
    component: () => import('../layout/index.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/dashboard/Home.vue'),
        meta: {
          title: '工作台',
          icon: 'home'
        }
      },
      {
        path: 'analysis',
        name: 'Analysis',
        component: () => import('../views/dashboard/Analysis.vue'),
        meta: {
          title: '分析页',
          icon: 'analysis'
        }
      }
    ]
  },
  {
    path: '/user',
    component: () => import('../layout/index.vue'),
    hidden: true,
    redirect: 'noRedirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/system/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },
  {
    path: '/config',
    component: () => import('../layout/index.vue'),
    hidden: true,
    redirect: 'noRedirect',
    children: [
      {
        path: 'other',
        component: () => import('@/views/website/config/Other.vue'),
        name: 'Other',
        meta: { title: '其他配置' }
      }
    ]
  },
  {
    path: '/interface',
    component: () => import('../layout/index.vue'),
    hidden: true,
    redirect: 'Redirect',
    children: [
      {
        path: 'resource',
        component: () => import('@/views/system/interface/components/Resource.vue'),
        name: 'Resource',
        meta: { title: '接口信息' }
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: constantRoutes
})

// 获取原型对象push函数
const originalPush = VueRouter.prototype.push
// 获取原型对象replace函数
const originalReplace = VueRouter.prototype.replace

/**
 * 修改原型对象中的push函数
 *
 * @param {*} location 当前要跳转路径的对象
 * @returns
 */
VueRouter.prototype.push = function push (location) {
  // this指向当前VueRouter的实例对象
  return originalPush.call(this, location).catch(err => err)
}

/**
 * 修改原型对象中的replace函数
 *
 * @param {*} location 当前要跳转路径的对象
 * @returns
 */
VueRouter.prototype.replace = function replace (location) {
  return originalReplace.call(this, location).catch(err => err)
}

export default router
