import defaultSettings from '../../settings'
// 从默认的主题配置中获取到默认参数
const { theme } = defaultSettings
// 获取本地配置的参数
const storageSetting = JSON.parse(localStorage.getItem('layout-setting')) || ''

const setting = {
  namespaced: true,
  state: {
    // 主题色
    theme: storageSetting.theme || theme
  },
  mutations: {
    /**
     * 修改主题配置
     *
     * @param {*} state
     * @param {*} param1
     */
    CHANGE_SETTING: (state, { key, value }) => {
      // 调用原型链上真正的 hasOwnProperty 方法，判断是否含有key属性
      if (Object.prototype.hasOwnProperty.call(state, key)) {
        state[key] = value
      }
    }
  },
  // 异步处理方式
  actions: {
    // 修改布局设置
    changeSetting ({ commit }, data) {
      commit('CHANGE_SETTING', data)
    }
  }
}

export default setting
