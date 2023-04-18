import axios from 'axios'
import { getItem } from './cookie'
import { Message } from 'element-ui'

const service = axios.create({
  baseURL: '/api',
  // 超时时间 => 15s
  timeout: 15 * 1000
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 统一注入 token
    var token = getItem('token')
    if (token) {
      // 如果存在 Token 则在每一次请求的请求头中添加 Token
      config.headers.Authorization = `Bearer ${token}`
      console.log(config.headers.Authorization)
    }
    return config
  },
  (error) => {
    return Promise(error)
  }
)

// 响应拦截器
service.interceptors.response.use((response) => {
  // 从服务端返回的数据中提取到需要的数据
  const { message, data, code } = response.data
  // 处理请求成功的情况
  if (response.status && response.status === 200) {
    // Token失效的情况
    // 根据服务器返回数据中的状态码进行返回
    if (code !== 200) {
      // 业务逻辑错误，输出错误信息并返回
      Message.error({ message: message })
      return Promise.reject(new Error(message))
    }
    return data
  } else {
    // 请求失败情况
    return Promise.reject(new Error(message))
  }
},
// 请求应失败的处理
(error) => {
  return Promise.reject(new Error(error))
})

export default service
