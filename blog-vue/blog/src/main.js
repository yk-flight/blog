import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import SvgIcons from './icons'

import './styles/style.scss'

Vue.config.productionTip = false

Vue.use(SvgIcons)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
