import request from '@/utils/request'

const BASE_URL = "auth-api"

/**
 * 登录
 * @param data
 */
export function login(data) {
  return request({
    url: BASE_URL + '/manage/login',
    method: 'post',
    data
  })
}

/**
 * 下线
 */
export function logout() {
  return request({
    url: BASE_URL + '/manage/login/logout',
    method: 'post'
  })
}

/**
 * 返回用户信息
 * @param token
 */
export function getInfo(token) {
  return request({
    url: BASE_URL + '/manage/login/get',
    method: 'post',
    params: {access_token: token}
  })
}

/**
 * 获取用户列表
 */
export function list(data) {
  return request({
    url: BASE_URL + '/manage/app/manager/list',
    method: 'post',
    data
  })
}

/**
 * 删除用户
 * @param id
 */
export function remove(id) {
  return request({
    url: BASE_URL + '/manage/app/manager/remove/' + id,
    method: 'delete'
  })
}

/**
 * 获取用户信息
 * @param id
 */
export function get(id) {
  return request({
    url: BASE_URL + '/manage/app/manager/select/' + id,
    method: 'get'
  })
}

/**
 * 保存用户信息
 * @param data
 */
export function update(data) {
  return request({
    url: BASE_URL + '/manage/app/manager/update',
    method: 'post',
    data
  })
}

/**
 * 新增用户信息
 * @param data
 */
export function add(data) {
  return request({
    url: BASE_URL + '/manage/app/manager/insert',
    method: 'post',
    data
  })
}
