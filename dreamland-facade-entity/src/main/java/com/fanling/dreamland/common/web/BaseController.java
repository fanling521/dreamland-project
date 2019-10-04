package com.fanling.dreamland.common.web;
import com.fanling.dreamland.common.R;
import com.fanling.dreamland.common.entity.BaseSearch;
import com.fanling.dreamland.common.utils.SqlUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * web层通用数据处理
 */
public class BaseController {

    /**
     * 设置请求分页数据
     * 默认 每页10条
     */
    protected void startPage(BaseSearch req) {
        PageHelper.startPage(req.getPage_num(), req.getPage_size(), SqlUtil.escapeOrderBySql(req.getOrder_by()));
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setData(list);
        rspData.setCount(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 没有分页
     *
     * @param list
     * @return
     */
    protected R getDatalist(List<?> list) {
        R rspData = new R();
        rspData.put("code", 0);
        rspData.put("msg", "ok");
        rspData.put("data", list);
        return rspData;
    }

    /**
     * 返回实体信息
     *
     * @param object
     * @return
     */
    protected R getEntity(Object object) {
        R r = new R();
        r.put("code", 0);
        r.put("msg", "ok");
        r.put("data", object);
        return r;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回成功
     */
    public R success() {
        return R.success();
    }

    /**
     * 返回失败消息
     */
    public R error() {
        return R.error();
    }

    /**
     * 返回成功消息
     */
    public R success(String message) {
        return R.success(message);
    }

    /**
     * 返回失败消息
     */
    public R error(String message) {
        return R.error(message);
    }

}
