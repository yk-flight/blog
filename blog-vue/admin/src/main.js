import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import SvgIcons from './icons'
import ElementUI from 'element-ui'
import TagCloud from './utils/tag-cloud'
import Vue2Editor from 'vue2-editor'
import filters from './filter/index'
import './permission'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'
import 'nprogress/nprogress.css'
import './style/css/style.css'
import './style/scss/index.scss'

import { resetForm } from './utils/common'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(SvgIcons)
Vue.use(TagCloud)
Vue.use(Vue2Editor)

// 全局挂载方法
Vue.prototype.resetForm = resetForm

// 时间过滤器
Object.keys(filters).forEach((key) => {
  Vue.filter(key, filters[key])
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
