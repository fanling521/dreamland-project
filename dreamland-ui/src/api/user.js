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
export function logout(uid) {
  return request({
    url: BASE_URL + '/manager/login/logout/' + uid,
    method: 'post'
  })
}

/**
 * 返回用户信息
 * @param token
 */
export function getInfo(token) {
  return request({
    url: BASE_URL + '/manager/login/get',
    method: 'post',
    params: {access_token: token}
  })
}
