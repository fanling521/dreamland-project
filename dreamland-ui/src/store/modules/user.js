import {getToken, removeToken, setToken} from '@/utils/auth'
import {resetRouter} from '@/router'

const state = {
  token: getToken(),
  name: '系统管理员',
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  }
}

const actions = {
  // user login
  login({commit}, userInfo) {
    const {loginName, password} = userInfo
    debugger
    return new Promise((resolve, reject) => {
      if (loginName.trim() === "admin" && password === "admin") {
        commit('SET_TOKEN', "1B44BAC29B1947EFA3A2C59F8165CFDD")
        setToken("1B44BAC29B1947EFA3A2C59F8165CFDD")
        resolve()
      } else {
        reject("验证失败！")
      }
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
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resetRouter()
      resolve()
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

