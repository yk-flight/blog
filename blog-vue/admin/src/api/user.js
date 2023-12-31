import request from '../utils/request'

/**
 * 获取用户信息
 */
export const getLoginUser = () => {
  return request({
    url: '/admin/user/getLoginUser',
    method: 'GET'
  })
}

/**
 * 获取用户个人信息
 */
export const getUserInfo = () => {
  return request({
    url: '/admin/user-info/getUserInfo',
    method: 'GET'
  })
}

/**
 * 更新用户个人信息
 */
export const updateLoginUser = (data) => {
  return request({
    url: '/admin/user/updateLoginUser',
    method: 'PUT',
    data: data
  })
}

/**
 * 更新用户头像信息
 */
export const updateLoginUserAvatar = (data) => {
  return request({
    url: '/admin/user/updateLoginUserAvatar',
    method: 'PUT',
    data: data
  })
}

/**
 * 发送邮箱验证码
 */
export const sendEmailCode = () => {
  return request({
    url: '/admin/email/password',
    method: 'GET'
  })
}

/**
 * 更新用户密码
 */
export const updatePassword = (data) => {
  return request({
    url: '/admin/user/updatePassword',
    method: 'PUT',
    data: data
  })
}

/**
 * 获取用户选项数据
 */
export const listUserOptions = () => {
  return request({
    url: '/admin/user/listUserOptions',
    method: 'GET'
  })
}

/**
 * 获取用户列表
 */
export const listUsers = (data) => {
  return request({
    url: '/admin/user/list',
    method: 'POST',
    data: data
  })
}

/**
 * 新增用户
 */
export const insert = (data) => {
  return request({
    url: '/admin/user/insert',
    method: 'POST',
    data: data
  })
}

/**
 * 获取指定用户信息
 */
export const getUserInfoById = (id) => {
  return request({
    url: `/admin/user-info/getUserInfoById/${id}`,
    method: 'GET'
  })
}

/**
 * 更新指定用户个人信息
 */
export const updateUser = (data) => {
  return request({
    url: '/admin/user/updateUser',
    method: 'PUT',
    data: data
  })
}

/**
 * 获取指定用户状态
 */
export const updateUserStatus = (id) => {
  return request({
    url: `/admin/user/updateUserStatus/${id}`,
    method: 'GET'
  })
}

/**
 * 重置指定用户密码
 */
export const resetPassword = (id) => {
  return request({
    url: `/admin/user/resetPassword/${id}`,
    method: 'GET'
  })
}

/**
 * 批量删除用户信息
 */
export const deleteUser = (data) => {
  return request({
    url: '/admin/user/delete',
    method: 'DELETE',
    data: data
  })
}
