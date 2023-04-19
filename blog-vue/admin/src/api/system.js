import request from '../utils/request'

/**
 * 用户登录
 */
export const login = (data) => {
  return request({
    url: '/login/admin',
    method: 'POST',
    data: data
  })
}

/**
 * 获取菜单
 */
export const generateRoutes = () => {
  return request({
    url: '/menu/getRoutes',
    method: 'GET'
  })
}
