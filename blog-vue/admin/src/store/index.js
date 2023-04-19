import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getter'
import user from './modules/user'
import app from './modules/app'

Vue.use(Vuex)

const store = new Vuex.Store({
  getters,
  modules: {
    app,
    user
  }
})

export default store
