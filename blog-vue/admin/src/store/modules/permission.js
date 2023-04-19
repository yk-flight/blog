import { generateRoutes } from '../../api/system'

const user = {
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
          // 设置当前用户角色到Vuex中
          console.log(res)
          commit('SET_ROUTES', res)
          resolve(user)
        }).catch((error) => {
          // 如果出现登录异常的情况将其捕获并抛出
          reject(error)
        })
      })
    }
  }
}

export default user
