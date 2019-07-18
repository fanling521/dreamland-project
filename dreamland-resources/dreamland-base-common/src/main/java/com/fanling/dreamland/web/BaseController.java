package com.fanling.dreamland.web;

import com.fanling.dreamland.Constants;
import com.fanling.dreamland.R;
import com.fanling.dreamland.utils.ServletUtils;
import com.fanling.dreamland.utils.SqlUtil;
import com.fanling.dreamland.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class BaseController {

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = new PageDomain();

        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));

        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setMsg("ok");
        rspData.setData(list);
        rspData.setCount(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 权限不正确的返回消息
     *
     * @param msg
     * @return
     */
    protected TableDataInfo getDataTable(String msg) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(403);
        rspData.setMsg(msg);
        rspData.setData(null);
        rspData.setCount(0);
        return rspData;
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
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected R toAjax(boolean result) {
        return result ? success() : error();
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
