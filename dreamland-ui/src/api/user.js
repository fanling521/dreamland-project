import request from '@/utils/request'

const BASE_URL = "auth-api"

/**
 * 登录
 * @param data
 */
export function login(data) {
  return request({
    url: BASE_URL + '/manager/login',
    method: 'post',
    data
  })
}

/**
 * 下线
 * @param userId
 */
export function logout() {
  return request({
    url: BASE_URL + '/manager/login/logout',
    method: 'post'
  })
}
