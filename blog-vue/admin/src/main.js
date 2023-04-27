import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import SvgIcons from './icons'
import ElementUI from 'element-ui'
import './permission'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'
import 'nprogress/nprogress.css'
import './style/css/style.css'
import './style/scss/index.scss'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(SvgIcons)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
