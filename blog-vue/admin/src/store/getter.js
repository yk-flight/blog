import { getItem } from '../utils/cookie'

// 快速获取数据
const getters = () => {
  // token
  const token = (state) => state.user.token || getItem('token') // eslint-disable-line no-unused-vars
}

export default getters
