import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getter'
import user from './modules/user'
import app from './modules/app'
import settings from './modules/settings'
import permission from './modules/permission'

Vue.use(Vuex)

const store = new Vuex.Store({
  getters,
  modules: {
    app,
    user,
    settings,
    permission
  }
})

export default store
