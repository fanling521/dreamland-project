import Cookies from 'js-cookie'

const state = {
  showSettings: false,
  fixedHeader: true,
  sidebarLogo: true,
  sidebar: {
    opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
    withoutAnimation: false
  },
  title: 'DREAMLAND综合信息管理系统',
  device: 'desktop'
}

const mutations = {
  CHANGE_SETTING: (state, {key, value}) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  },
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened
    state.sidebar.withoutAnimation = false
    if (state.sidebar.opened) {
      Cookies.set('sidebarStatus', 1)
    } else {
      Cookies.set('sidebarStatus', 0)
    }
  },
  CLOSE_SIDEBAR: (state, withoutAnimation) => {
    Cookies.set('sidebarStatus', 0)
    state.sidebar.opened = false
    state.sidebar.withoutAnimation = withoutAnimation
  },
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  }
}

const actions = {
  changeSetting({commit}, data) {
    commit('CHANGE_SETTING', data)
  },
  toggleSideBar({commit}) {
    commit('TOGGLE_SIDEBAR')
  },
  closeSideBar({commit}, {withoutAnimation}) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  toggleDevice({commit}, device) {
    commit('TOGGLE_DEVICE', device)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

