import { login } from '../../api/system'
import { getLoginUser, updateLoginUserAvatar } from '../../api/user'
import { TOKEN } from '../../constant/index'
import { setItem, getItem, removeItem } from '../../utils/cookie'

const user = {
  namespaced: true,
  state: {
    // 用户token
    token: getItem(TOKEN),
    // 用户角色
    roles: '',
    // 用户头像
    avatar: '',
    // 用户昵称
    nickname: ''
  },
  mutations: {
    SET_TOKEN (state, token) {
      state.token = token
    },
    SET_ROLES (state, roles) {
      state.roles = roles
    },
    SET_AVATAR (state, avatar) {
      state.avatar = avatar
    },
    SET_NICK (state, nickname) {
      state.nickname = nickname
    }
  },
  // 异步处理方式
  actions: {
    /**
     * 用户登录
     */
    login ({ commit }, loginForm) {
      // 获取到用户参数
      const { username, password, code, track } = loginForm
      // 调用登录方法，在获取到数据后向外传递
      return new Promise((resolve, reject) => {
        login({ username, password, code, track }).then((data) => {
          // 设置token值
          commit('SET_TOKEN', data)
          setItem(TOKEN, data)
          resolve()
        }).catch((error) => {
          // 如果出现登录异常的情况将其捕获并抛出
          reject(error)
        })
      })
    },
    /**
     * 获取用户信息
     */
    getUserInfo ({ commit }) {
      return new Promise((resolve, reject) => {
        // 获取用户信息
        getLoginUser().then(res => {
          // 存储用户信息
          const user = res
          const avatar = (user.avatar === '' || user.avatar === null) ? require('@/assets/images/logo.png') : user.avatar
          commit('SET_AVATAR', avatar)
          // 设置当前用户角色到Vuex中
          commit('SET_ROLES', user.roles)
          // 设置当前用户昵称到Vuex中
          commit('SET_NICK', user.nickname)
          resolve(user)
        }).catch((error) => {
          // 如果出现登录异常的情况将其捕获并抛出
          reject(error)
        })
      })
    },
    /**
     * 更新用户头像
     *
     * @param {*} param0
     * @param {*} avatarForm 用户头像表单
     */
    updateAvatar ({ commit }, avatarForm) {
      return new Promise((resolve, reject) => {
        // 更新用户头像信息
        const { userId, src } = avatarForm
        updateLoginUserAvatar({ userId, src }).then((res) => {
          // 更新用户头像（返回内容为用户新头像的访问路径）
          commit('SET_AVATAR', res)
          resolve(res)
        }).catch((error) => {
        // 如果出现更新异常的情况将其捕获并抛出
          reject(error)
        })
      })
    },
    /**
     * 退出登录
     *
     * @param {*} param0
     */
    logout ({ commit }) {
      // 清除Token
      removeItem(TOKEN)
      // 清除用户角色数据
      commit('SET_ROLES', '')
    }
  }
}

export default user
