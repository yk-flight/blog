import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export const constantRoutes = [
  {
    path: '/login',
    name: 'Login',
    hidden: true,
    component: () => import('../views/login/Login.vue'),
    meta: {
      title: '个人博客登录页'
    }
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
          icon: 'dashboard'
        }
      },
      {
        path: 'analys',
        name: 'Analys',
        component: () => import('../views/dashboard/Analys.vue'),
        meta: {
          title: '分析页',
          icon: 'dashboard'
        }
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
