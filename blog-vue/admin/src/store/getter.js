// 快速获取数据
const getters = {
  // 角色
  roles: state => state.user.roles,
  // 用户菜单
  sidebarRouters: state => state.permission.sidebarRouters,
  // 是否折叠菜单
  isCollapse: state => state.app.isCollapse,
  // 右侧功能栏
  rightPanelShow: state => state.app.rightPanelShow,
  // 标签页
  tagsViewList: state => state.app.tagsViewList
}

export default getters
