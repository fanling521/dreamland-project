import axios from 'axios'
import {Message} from 'element-ui'
import store from '@/store'
import {getToken} from '@/utils/auth'

const service = axios.create({
  // url = base url + request url
  baseURL: "http://localhost:7003",
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['x-access-token'] = getToken()
    }else{
      console.log("token is null")
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
      return res
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
