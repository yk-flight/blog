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
 * 获取首页信息
 */
export const getHomeInfo = () => {
  return request({
    url: '/admin/index/getHomeInfo',
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

/**
 * 获取系统基本配置
 */
export const getConfig = () => {
  return request({
    url: '/admin/config/getConfig',
    method: 'GET'
  })
}

/**
 * 更新系统配置信息
 */
export const saveConfig = (data) => {
  return request({
    url: '/admin/config/save',
    method: 'POST',
    data: data
  })
}

/**
 * 获取在线用户
 */
export const listOnline = (data) => {
  return request({
    url: '/admin/online/list',
    method: 'POST',
    data: data
  })
}

/**
 * 下线指定用户
 */
export const offline = (param) => {
  return request({
    url: `/admin/online/offline/${param}`,
    method: 'DELETE'
  })
}
