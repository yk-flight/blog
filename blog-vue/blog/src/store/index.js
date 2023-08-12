import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getter'
import settings from './modules/settings'

Vue.use(Vuex)

const store = new Vuex.Store({
  getters,
  modules: {
    settings
  }
})

export default store
