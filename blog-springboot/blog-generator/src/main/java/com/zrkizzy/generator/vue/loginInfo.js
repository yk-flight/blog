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
 * 新增/更新用户登录信息信息
 */
export const saveLoginInfo = (data) => {
  return request({
    url: '/admin/login-info/save',
    method: 'POST',
    data: data
  })
}

/**
 * 获取指定用户登录信息信息
 */
export const getLoginInfoById = (id) => {
  return request({
    url: `/admin/login-info/getLoginInfoById/${id}`,
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
