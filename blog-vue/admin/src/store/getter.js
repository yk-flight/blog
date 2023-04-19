// import { getItem } from '../utils/cookie'
// 快速获取数据
const getters = {
  // 角色
  roles: state => state.user.roles,
  // 用户菜单
  sidebarRouters: state => state.permission.sidebarRouters
}

export default getters
