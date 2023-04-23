const app = {
  state: {
    // 菜单是否折叠
    isCollapse: false,
    // 右侧面板是否显示
    rightPanelShow: false
  },
  mutations: {
    SET_COLLAPSE (state) {
      state.isCollapse = !state.isCollapse
    },
    SET_RIGHT_PANEL (state) {
      state.rightPanelShow = !state.rightPanelShow
    }
  },
  // 异步处理方式
  actions: {
  }
}

export default app
