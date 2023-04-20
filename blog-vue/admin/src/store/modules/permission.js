import { generateRoutes } from '../../api/system'
import { constantRoutes } from '../../router'
import Layout from '../../layout/Layout'

const permission = {
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
          const rewirteRoutes = filterAsyncRouter(routeData)
          const sidebarRoutes = filterAsyncRouter(sidebarData)
          console.log('拼接合并好的路由为：', constantRoutes.concat(sidebarRoutes))
          // 拼接公共路由后设置当前用户角色到Vuex中
          commit('SET_ROUTES', constantRoutes.concat(sidebarRoutes))
          resolve(rewirteRoutes)
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
    // 处理子菜单的路由
    // if (route.children) {
    //   route.children = filterChildren(route.children)
    // }
    // 在component存在的情况下才能够进行处理
    if (route.component) {
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadComponent(route.component)
      }
    }
    if (route.children !== null && route.children && route.children.length > 0) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })
}

// function filterChildren (childrenMap, lastRouter = false) {
//   // var children = []
//   childrenMap.forEach((el, index) => {
//     console.log(el)
//   })
// }

/**
 * 根据组件路径加载对应组件
 *
 * @param {*} componentPath
 */
function loadComponent (componentPath) {
  return (resolve) => require([`@/views/${componentPath}`], resolve)
}

export default permission
