import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../layout/Layout'

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
    component: () => import('../layout/Layout.vue'),
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('../views/home/Home.vue'),
        meta: {
          title: '首页',
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

/**
 * 对异步得到的路由做过滤的动作
 */
export function filterAsync (asyncRouterMap) {
  return asyncRouterMap.filter(route => {
    // 在component存在的情况下才能够进行处理
    if (route.component) {
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadComponent(route.component)
      }
    }
    if (route.children !== null && route.children && route.children.length > 0) {
      route.children = filterAsync(route.children)
    }
    return true
  })
}

/**
 * 根据组件路径加载对应组件
 *
 * @param {*} componentPath
 */
function loadComponent (componentPath) {
  return (resolve) => require([`@/views/${componentPath}`], resolve)
}

export default router
