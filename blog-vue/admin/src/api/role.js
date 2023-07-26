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

/**
 * 获取指定角色信息
 */
export const getRoleById = (id) => {
  return request({
    url: `/admin/role/getRoleById/${id}`,
    method: 'GET'
  })
}

/**
 * 批量删除角色信息
 */
export const deleteRole = (data) => {
  return request({
    url: '/admin/role/delete',
    method: 'DELETE',
    data: data
  })
}

/**
 * 获取角色选项
 */
export const listRoleOptions = () => {
  return request({
    url: '/admin/role/listRoleOptions',
    method: 'GET'
  })
}

/**
 * 更新用户角色
 */
export const updateUserRole = (data) => {
  return request({
    url: '/admin/user-role/update',
    method: 'PUT',
    data: data
  })
}
