import request from '../utils/request'

/**
 * 获取资源列表
 */
export const listResources = (data) => {
  return request({
    url: '/admin/resource/list',
    method: 'POST',
    data: data
  })
}

/**
 * 更新资源信息
 */
export const saveResource = (data) => {
  return request({
    url: '/admin/resource/save',
    method: 'PUT',
    data: data
  })
}

/**
 * 获取指定资源信息
 */
export const getResourceById = (id) => {
  return request({
    url: `/admin/resource/getResourceById/${id}`,
    method: 'GET'
  })
}
