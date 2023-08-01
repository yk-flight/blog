import request from '../utils/request'

/**
 * 获取菜单列表
 */
export const listMenus = (data) => {
  return request({
    url: '/admin/menu/list',
    method: 'POST',
    data: data
  })
}

/**
 * 新增/更新菜单信息
 */
export const saveMenu = (data) => {
  return request({
    url: '/admin/menu/save',
    method: 'POST',
    data: data
  })
}

/**
 * 获取指定菜单信息
 */
export const getMenuById = (id) => {
  return request({
    url: `/admin/menu/getMenuById/${id}`,
    method: 'GET'
  })
}

/**
 * 批量删除菜单信息
 */
export const deleteMenu = (data) => {
  return request({
    url: '/admin/menu/delete',
    method: 'DELETE',
    data: data
  })
}