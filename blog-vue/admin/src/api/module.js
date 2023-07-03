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
