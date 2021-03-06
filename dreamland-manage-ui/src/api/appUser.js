import request from '@/utils/request'

const BASE_SERVICE_URL = 'auth-api'

/**
 * 分页查询
 * @param token
 */
export function list(data) {
  return request({
    url: BASE_SERVICE_URL+'/manage/app/user/list',
    method: 'post',
    data
  })
}

/**
 * 新增
 * @param data
 */
export function add(data) {
  return request({
    url: BASE_SERVICE_URL+'/manage/app/user/insert',
    method: 'post',
    data
  })
}

/**
 * 编辑
 * @param data
 */
export function edit(data) {
  return request({
    url: BASE_SERVICE_URL+'/manage/app/user/update',
    method: 'post',
    data
  })
}

/**
 * 根据id获取
 * @param id
 */
export function get(id) {
  return request({
    url: BASE_SERVICE_URL+'/manage/app/user/select/' + id,
    method: 'get',
  })
}

/**
 * 删除
 * @param id
 */
export function remove(id) {
  return request({
    url: BASE_SERVICE_URL+'/manage/app/user/remove/' + id,
    method: 'delete',
  })
}
