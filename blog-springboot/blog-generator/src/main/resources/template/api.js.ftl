import request from '../utils/request'

/**
 * 获取${objectName}列表
 */
export const list${entityName}s = (data) => {
  return request({
    url: '/admin/${mapping}/list',
    method: 'POST',
    data: data
  })
}

/**
 * 新增/更新${objectName}信息
 */
export const save${entityName} = (data) => {
  return request({
    url: '/admin/${mapping}/save',
    method: 'POST',
    data: data
  })
}

/**
 * 获取指定${objectName}信息
 */
export const get${entityName}ById = (id) => {
  return request({
    url: `/admin/${mapping}/get${entityName}ById/<#noparse>${id}</#noparse>`,
    method: 'GET'
  })
}

/**
 * 批量删除${objectName}信息
 */
export const delete${entityName} = (data) => {
  return request({
    url: '/admin/${entityLowerName}/delete',
    method: 'DELETE',
    data: data
  })
}
