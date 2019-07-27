import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import settings from './modules/settings'
import permission from './modules/permission'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    settings, user, permission
  },
  getters
})

export default store
