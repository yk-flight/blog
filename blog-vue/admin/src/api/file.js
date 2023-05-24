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

/**
 * 获取文件上传模式
 */
export const listModes = () => {
  return request({
    url: '/admin/file/listModes',
    method: 'GET'
  })
}
