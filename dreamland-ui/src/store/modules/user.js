import {getToken, removeToken, setToken} from '@/utils/auth'
import {login, logout} from '@/api/user'
import {resetRouter} from '@/router'
import md5 from 'js-md5';

const state = {
  token: getToken(),
  name: '',
  uid: ''
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_UID: (state, uid) => {
    state.uid = uid
  }
}

const actions = {
  // user login
  login({commit}, userInfo) {
    const {loginName, password} = userInfo
    return new Promise((resolve, reject) => {
      login({account: loginName.trim(), password: md5(password)}).then(response => {
        const {data} = response
        debugger
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        commit('SET_UID', data.id)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      commit('SET_NAME', "系统管理员")
      resolve()
    })
  },

  // user logout
  logout({commit, state}) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        removeToken()
        resetRouter()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

