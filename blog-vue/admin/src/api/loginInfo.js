import request from '../utils/request'

/**
 * 获取用户登录信息列表
 */
export const listLoginInfos = (data) => {
  return request({
    url: '/admin/login-info/list',
    method: 'POST',
    data: data
  })
}

/**
 * 清空所有登录日志信息
 */
export const clearLoginInfo = () => {
  return request({
    url: '/admin/login-info/clear',
    method: 'GET'
  })
}

/**
 * 批量删除用户登录信息信息
 */
export const deleteLoginInfo = (data) => {
  return request({
    url: '/admin/login-info/delete',
    method: 'DELETE',
    data: data
  })
}
