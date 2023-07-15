import request from '../utils/request'

/**
 * 获取所有Redis缓存键类型
 */
export const listCacheType = () => {
  return request({
    url: '/admin/cache/listCacheType',
    method: 'GET'
  })
}

/**
 * 获取所有缓存键
 */
export const listCacheKeys = (type) => {
  return request({
    url: `/admin/cache/listCacheKeys/${type}`,
    method: 'GET'
  })
}

/**
 * 获取指定缓存信息
 */
export const getCacheInfoByKey = (key) => {
  return request({
    url: `/admin/cache/getCacheInfoByKey/${key}`,
    method: 'GET'
  })
}

/**
 * 清除缓存列表
 */
export const clearCacheKeys = (type) => {
  return request({
    url: `/admin/cache/clearCacheKeys/${type}`,
    method: 'DELETE'
  })
}

/**
 * 删除指定缓存
 */
export const deleteCacheKey = (key) => {
  return request({
    url: `/admin/cache/deleteCacheKey/${key}`,
    method: 'DELETE'
  })
}
