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
