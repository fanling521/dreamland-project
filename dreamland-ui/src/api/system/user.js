import request from '@/utils/request'

/**
 * 登录
 * @param data
 */
export function login(data) {
  return request({
    url: '/system/login',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息和角色
 */
export function getInfo() {
  return request({
    url: '/system/get_user_info',
    method: 'post',
  })
}

/**
 * 下线
 * @param userId
 */
export function logout(userId) {
  return request({
    url: '/system/logout',
    method: 'post',
    params: {userId: userId}
  })
}

/**
 * 获取用户列表
 */
export function userList(params) {
  return request({
    url: '/system/user/list',
    method: 'post',
    params: params
  })
}

/**
 * 删除用户
 * @param userId
 */
export function removeUser(userId) {
  return request({
    url: '/system/user/remove',
    method: 'post',
    params: {userId: userId}
  })
}

/**
 * 获取用户信息
 * @param userId
 */
export function getUser(userId) {
  return request({
    url: '/system/user/get/' + userId,
    method: 'post'
  })
}

/**
 * 保存用户信息
 * @param data
 */
export function saveUser(data) {
  return request({
    url: '/system/user/edit',
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
    url: '/system/user/add',
    method: 'post',
    data
  })
}
