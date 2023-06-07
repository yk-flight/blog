import request from '../utils/request'

/**
 * 获取角色列表
 */
export const listRoles = (data) => {
  return request({
    url: '/admin/role/list',
    method: 'POST',
    data: data
  })
}

/**
 * 新增/更新角色信息
 */
export const saveRole = (data) => {
  return request({
    url: '/admin/role/save',
    method: 'POST',
    data: data
  })
}
