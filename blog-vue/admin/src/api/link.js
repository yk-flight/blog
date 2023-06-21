import request from '../utils/request'

/**
 * 获取友情链接列表
 */
export const listLinks = (data) => {
  return request({
    url: '/admin/link/list',
    method: 'POST',
    data: data
  })
}

/**
 * 新增/更新友情链接信息
 */
export const saveLink = (data) => {
  return request({
    url: '/admin/link/save',
    method: 'POST',
    data: data
  })
}

/**
 * 获取指定友情链接信息
 */
export const getLinkById = (id) => {
  return request({
    url: `/admin/link/getLinkById/${id}`,
    method: 'GET'
  })
}

/**
 * 批量删除友情链接信息
 */
export const deleteLink = (data) => {
  return request({
    url: '/admin/link/delete',
    method: 'DELETE',
    data: data
  })
}
