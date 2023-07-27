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
 * 新增/更新资源信息
 */
export const saveResource = (data) => {
  return request({
    url: '/admin/resource/save',
    method: 'POST',
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

/**
 * 批量删除资源信息
 */
export const deleteResource = (data) => {
  return request({
    url: '/admin/resource/delete',
    method: 'DELETE',
    data: data
  })
}
