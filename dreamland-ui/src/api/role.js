import request from '@/utils/request'

const BASE_URL = "/system-service"

export function selectAllRole() {
  return request({
    url: BASE_URL + '/system/role/select_all_role',
    method: 'post'
  })
}

/**
 * 获取用户的角色信息
 * @param userId
 * @constructor
 */
export function selectRoles(userId) {
  return request({
    url: BASE_URL + '/system/role/select_roles/' + userId,
    method: 'post'
  })
}

export function roleList(data) {
  return request({
    url: BASE_URL + '/system/role/list',
    method: 'post',
    data
  })
}

/**
 * 删除用户
 * @param roleId
 */
export function removeRole(roleId) {
  return request({
    url: BASE_URL + '/system/role/remove/' + roleId,
    method: 'delete'
  })
}

/**
 * 获取角色信息
 * @param roleId
 */
export function getRole(roleId) {
  return request({
    url: BASE_URL + '/system/role/get/' + roleId,
    method: 'post'
  })
}

/**
 * 保存角色信息
 * @param data
 */
export function saveRole(data) {
  return request({
    url: BASE_URL + '/system/role/edit',
    method: 'post',
    data
  })
}

/**
 * 新增角色信息
 * @param data
 */
export function insertRole(data) {
  return request({
    url: BASE_URL + '/system/role/add',
    method: 'post',
    data
  })
}
