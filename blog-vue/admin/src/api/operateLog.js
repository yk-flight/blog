import request from '../utils/request'

/**
 * 获取操作日志列表
 */
export const listOperateLogs = (data) => {
  return request({
    url: '/admin/operate-log/list',
    method: 'POST',
    data: data
  })
}

/**
 * 清空所有操作日志信息
 */
export const clearOperateLogs = () => {
  return request({
    url: '/admin/operate-log/clear',
    method: 'GET'
  })
}

/**
 * 批量删除操作日志信息
 */
export const deleteOperateLog = (data) => {
  return request({
    url: '/admin/operate-log/delete',
    method: 'DELETE',
    data: data
  })
}
