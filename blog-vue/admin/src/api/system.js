import request from '../utils/request'

/**
 * 获取验证码
 */
export const getCaptcha = () => {
  return request({
    url: '/captcha/getCaptcha',
    method: 'GET'
  })
}

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
    url: '/admin/menu/getRoutes',
    method: 'GET'
  })
}
