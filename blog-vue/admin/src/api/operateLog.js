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
 * 获取指定操作日志信息
 */
export const getOperateLogById = (id) => {
  return request({
    url: `/admin/operate-log/getOperateLogById/${id}`,
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
