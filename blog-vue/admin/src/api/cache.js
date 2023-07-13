import request from '../utils/request'

/**
 * 获取所有Redis缓存键类型
 */
export const listRedisKeyType = () => {
  return request({
    url: '/admin/cache/listRedisKeyType',
    method: 'GET'
  })
}
