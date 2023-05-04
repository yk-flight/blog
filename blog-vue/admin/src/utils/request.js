import axios from 'axios'
import { getItem } from './cookie'
import { Message, MessageBox } from 'element-ui'
import { TOKEN } from '../constant/index'
import store from '../store/index'

const service = axios.create({
  baseURL: '/api',
  // 超时时间 => 15s
  timeout: 15 * 1000
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 统一注入 token
    var token = getItem(TOKEN)
    if (token) {
      // 如果存在 Token 则在每一次请求的请求头中添加 Token
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise(error)
  }
)

// 在过期时可能会拦截多个接口请求，为了只显示一次错误信息
export let messageShow = false

// 响应拦截器
service.interceptors.response.use((response) => {
  // 从服务端返回的数据中提取到需要的数据
  const { message, data, code } = response.data
  // 处理请求成功的情况
  if (response.status && response.status === 200) {
    // Token失效的情况，或管理员强制下线情况
    if (code === 506 || code === 2000) {
      if (!messageShow) {
        messageShow = true
        MessageBox.confirm('登录状态已过期，请重新登录', '系统提示',
          { confirmButtonText: '重新登录', cancelButtonText: '取消', type: 'warning' })
          .then(() => {
            messageShow = false
            store.dispatch('user/logout').then(() => {
            // 跳转到登录页面
              location.href = '/login'
            })
          }).catch(() => {
            // 发生异常
            messageShow = false
          })
      }
      return Promise.reject(new Error('会话已过期，请重新登录'))
    }
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
