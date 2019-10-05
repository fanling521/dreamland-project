import request from '@/utils/request'

const BASE_URL = 'module-api'


/**
 * 获取设计服务列表
 */
export function list(data) {
  return request({
    url: BASE_URL + '/manage/design/service/order/list',
    method: 'post',
    data
  })
}

/**
 * 删除设计服务
 * @param id
 */
export function remove(id) {
  return request({
    url: BASE_URL + '/manage/design/service/order/remove/' + id,
    method: 'delete'
  })
}

/**
 * 获取设计服务信息
 * @param id
 */
export function get(id) {
  return request({
    url: BASE_URL + '/manage/design/service/order/select/' + id,
    method: 'get'
  })
}
