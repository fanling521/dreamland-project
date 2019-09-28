import axios from 'axios'
import {Message,MessageBox} from 'element-ui'
import store from '@/store'
import {getToken} from '@/utils/auth'

const service = axios.create({
  baseURL: process.env.NODE_URL,
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['x-access-token'] = getToken()
      config.headers['x-user-id'] = store.getters.uid
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code === 500) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      if (res.code === 401) {
        MessageBox.confirm('登录已经过期，请重新登录！', '重新登录', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
        return Promise.reject(new Error(res.msg || 'Error'))
      } else {
        return res
      }
    }
  },
  error => {
    Message({
      message: '网络连接失败！',
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
