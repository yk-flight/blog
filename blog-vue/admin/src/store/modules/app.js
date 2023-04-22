const app = {
  state: {
    // 菜单是否折叠
    isCollapse: false
  },
  mutations: {
    SET_COLLAPSE (state) {
      state.isCollapse = !state.isCollapse
    }
  },
  // 异步处理方式
  actions: {
  }
}

export default app
