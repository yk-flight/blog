import { generateRoutes } from '../../api/system'
import { constantRoutes } from '../../router'
import Layout from '../../layout/index'

const permission = {
  namespaced: true,
  state: {
    // 侧边栏
    sidebarRouters: []
  },
  mutations: {
    SET_ROUTES (state, sidebarRouters) {
      state.sidebarRouters = sidebarRouters
    }
  },
  // 异步处理方式
  actions: {
    /**
     * 获取用户菜单信息
     */
    generateRoutes ({ commit }) {
      return new Promise((resolve, reject) => {
        // 获取菜单信息
        generateRoutes().then(res => {
          // 将后端传来的动态路由转为Json
          const routeData = JSON.parse(JSON.stringify(res))
          const sidebarData = JSON.parse(JSON.stringify(res))
          // 根据组件的实际路径完成拼接动作
          const rewriteRoutes = filterAsyncRouter(routeData)
          const sidebarRoutes = filterAsyncRouter(sidebarData)
          // 将没有匹配到的路由全部替换为404
          rewriteRoutes.push({ path: '*', redirect: '/404', hidden: true })
          // 拼接公共路由后设置当前用户角色到Vuex中
          commit('SET_ROUTES', constantRoutes.concat(sidebarRoutes))
          resolve(rewriteRoutes)
        }).catch((error) => {
          // 如果出现登录异常的情况将其捕获并抛出
          reject(error)
        })
      })
    }
  }
}

/**
 * 对异步得到的路由做过滤的动作
 */
function filterAsyncRouter (asyncRouterMap) {
  return asyncRouterMap.filter(route => {
    // 在component存在的情况下才能够进行处理
    if (route.component) {
      // 如果当前路由的组件是Layout
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        // 非父菜单组件都到views目录下加载
        route.component = loadComponent(route.component)
      }
    }
    // 如果当前路由含有子路由
    if (route.children !== null && route.children && route.children.length > 0) {
      // 递归加载子路由
      route.children = filterAsyncRouter(route.children)
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

export default permission
