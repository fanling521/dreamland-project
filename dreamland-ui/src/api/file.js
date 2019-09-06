import request from '@/utils/request'

const BASE_SERVICE_URL = 'attachment-api'

/**
 * 分页查询
 * @param token
 */
export function list(data) {
  return request({
    url: BASE_SERVICE_URL+'/manager/file/attachment/list',
    method: 'post',
    data
  })
}

/**
 * 删除
 * @param id
 */
export function remove(id) {
  return request({
    url: BASE_SERVICE_URL+'/manager/file/attachment/remove/' + id,
    method: 'delete',
  })
}

