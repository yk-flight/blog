import request from '../utils/request'

/**
 * 获取文件列表
 */
export const listFiles = () => {
  return request({
    url: '/admin/file-type/list',
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

/**
 * 上传文件
 */
export const upload = (data) => {
  return request({
    url: '/admin/file/upload',
    method: 'POST',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

/**
 * 批量删除文件
 */
export const deleteBatch = (data) => {
  return request({
    url: '/admin/file/delete',
    method: 'DELETE',
    data: data
  })
}

/**
 * 根据ID获取文件分类对象
 */
export const getFileTypeById = (id) => {
  return request({
    url: `/admin/file-type/getFileTypeById/${id}`,
    method: 'GET'
  })
}
