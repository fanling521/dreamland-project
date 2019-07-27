import request from '@/utils/request'

const BASE_URL = "/system-service"

/**
 * 登录
 * @param data
 */
export function login(data) {
  return request({
    url: BASE_URL + '/system/login',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息和角色
 */
export function getInfo(token) {
  return request({
    url: BASE_URL + '/system/get_user_info',
    method: 'get'
  })
}

/**
 * 下线
 * @param userId
 */
export function logout(userId) {
  return request({
    url: BASE_URL + '/system/logout/' + userId,
    method: 'post'
  })
}

/**
 * 获取用户列表
 */
export function userList(data) {
  return request({
    url: BASE_URL + '/system/user/list',
    method: 'post',
    data
  })
}

/**
 * 删除用户
 * @param userId
 */
export function removeUser(userId) {
  return request({
    url: BASE_URL + '/system/user/remove/' + userId,
    method: 'delete'
  })
}

/**
 * 获取用户信息
 * @param userId
 */
export function getUser(userId) {
  return request({
    url: BASE_URL + '/system/user/get/' + userId,
    method: 'post'
  })
}

/**
 * 保存用户信息
 * @param data
 */
export function saveUser(data) {
  return request({
    url: BASE_URL + '/system/user/edit',
    method: 'post',
    data
  })
}

/**
 * 新增用户信息
 * @param data
 */
export function insertUser(data) {
  return request({
    url: BASE_URL + '/system/user/add',
    method: 'post',
    data
  })
}
