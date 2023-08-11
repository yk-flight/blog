import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import SvgIcons from './icons'

import './styles/style.scss'
import 'animate.css'

import { WOW } from 'wowjs'
import animated from 'wowjs/css/libs/animate.css'

Vue.config.productionTip = false
// 创建全局实例
Vue.prototype.$wow = new WOW({
  // 需要执行动画的元素的 class
  boxClass: 'wow',
  // animation.css 动画的 class
  animateClass: 'animated',
  // 距离可视区域多少开始执行动画
  offset: 200,
  // 是否在移动设备上执行动画
  mobile: true,
  // 异步加载的内容是否有效
  live: true
}
)

Vue.use(SvgIcons)
Vue.use(animated)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
