// 快速获取数据
const getters = {
  // 角色
  roles: state => state.user.roles,
  // 用户菜单
  sidebarRouters: state => state.permission.sidebarRouters,
  // 是否折叠菜单
  isCollapse: state => state.app.isCollapse
}

export default getters
