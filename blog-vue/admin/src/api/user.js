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
    url: '/admin/user/getUserInfo',
    method: 'GET'
  })
}

/**
 * 更新用户个人信息
 */
export const updateUserInfo = (data) => {
  return request({
    url: '/admin/user/updateUserInfo',
    method: 'POST',
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
    method: 'POST',
    data: data
  })
}
