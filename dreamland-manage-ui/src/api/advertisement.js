import request from '@/utils/request'

const BASE_SERVICE_URL = 'module-api'

/**
 * 分页查询
 * @param token
 */
export function list(data) {
  return request({
    url: BASE_SERVICE_URL+'/manage/marketing/list',
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
    url: BASE_SERVICE_URL+'/manage/marketing/insert',
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
    url: BASE_SERVICE_URL+'/manage/marketing/update',
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
    url: BASE_SERVICE_URL+'/manage/marketing/select/' + id,
    method: 'post',
  })
}

/**
 * 删除
 * @param id
 */
export function remove(id) {
  return request({
    url: BASE_SERVICE_URL+'/manage/marketing/remove/' + id,
    method: 'delete',
  })
}
