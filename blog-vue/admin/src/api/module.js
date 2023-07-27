import request from '../utils/request'

/**
 * 获取模块选项数据
 */
export const listModuleOptions = () => {
  return request({
    url: '/admin/module/listModuleOptions',
    method: 'GET'
  })
}

/**
 * 获取资源模块列表
 */
export const listModules = (data) => {
  return request({
    url: '/admin/module/list',
    method: 'POST',
    data: data
  })
}

/**
 * 新增/更新资源模块信息
 */
export const saveModule = (data) => {
  return request({
    url: '/admin/module/save',
    method: 'POST',
    data: data
  })
}

/**
 * 获取指定资源模块信息
 */
export const getModuleById = (id) => {
  return request({
    url: `/admin/module/getModuleById/${id}`,
    method: 'GET'
  })
}

/**
 * 批量删除资源模块信息
 */
export const deleteModule = (data) => {
  return request({
    url: '/admin/module/delete',
    method: 'DELETE',
    data: data
  })
}
