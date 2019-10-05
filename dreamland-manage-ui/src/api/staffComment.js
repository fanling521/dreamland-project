import request from '@/utils/request'

const BASE_URL = 'module-api'
/**
 * 获取维修服务列表
 */
export function list(data) {
  return request({
    url: BASE_URL + '/manage/staff/comment/list',
    method: 'post',
    data
  })
}

/**
 * 删除维修服务
 * @param id
 */
export function remove(id) {
  return request({
    url: BASE_URL + '/manage/staff/comment/remove/' + id,
    method: 'delete'
  })
}

/**
 * 获取维修服务信息
 * @param id
 */
export function get(id) {
  return request({
    url: BASE_URL + '/manage/staff/comment/select/' + id,
    method: 'get'
  })
}

