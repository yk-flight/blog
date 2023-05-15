import request from '../utils/request'

/**
 * 获取文件列表
 */
export const listFiles = () => {
  return request({
    url: '/admin/file/list',
    method: 'GET'
  })
}
