import { login } from '../../api/system'
import { setItem, getItem } from '../../utils/cookie'

export default {
  namespaced: true,
  state: {
    // 用户token
    token: getItem('token')
  },
  mutations: {
    setToken (state, token) {
      state.token = token
    }
  },
  // 异步处理方式
  actions: {
    /**
     * 用户登录
     */
    login (context, loginForm) {
      // 获取到用户参数
      const { username, password, code } = loginForm
      // 调用登录方法，在获取到数据后向外传递
      return new Promise((resolve, reject) => {
        login({ username, password, code }).then((data) => {
          // 设置token值
          this.commit('user/setToken', data)
          setItem('token', data)
          resolve()
        }).catch((error) => {
          // 如果出现登录异常的情况将其捕获并抛出
          reject(error)
        })
      })
    }
  }
}
