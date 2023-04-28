// 快速获取数据
const getters = {
  // 角色
  roles: state => state.user.roles,
  // 头像
  avatar: state => state.user.avatar,
  // 首页对话框
  homeDialogShow: state => state.app.homeDialogShow,
  // 用户菜单
  sidebarRouters: state => state.permission.sidebarRouters,
  // 是否折叠菜单
  isCollapse: state => state.app.isCollapse,
  // 右侧功能栏
  rightPanelShow: state => state.app.rightPanelShow,
  // 标签页
  tagsViewList: state => state.app.tagsViewList,
  // 侧边栏主题
  sideTheme: state => state.settings.sideTheme,
  // 是否开启标签页
  tagsView: state => state.settings.tagsView,
  // 是否固定顶部导航栏
  fixedHeader: state => state.settings.fixedHeader,
  // 是否显示侧边栏Logo
  sidebarLogo: state => state.settings.sidebarLogo,
  // 主题色
  theme: state => state.settings.theme
}

export default getters
