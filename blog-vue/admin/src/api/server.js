import request from '../utils/request'

/**
 * 获取服务监控信息
 */
export const getMonitorInfo = () => {
  return request({
    url: '/admin/service-monitor/getMonitorInfo',
    method: 'GET'
  })
}
