package com.fanling.dreamland.controller;

import com.fanling.dreamland.common.HttpResult;
import com.fanling.dreamland.common.page.PageDomain;
import com.fanling.dreamland.common.page.TableDataInfo;
import com.fanling.dreamland.common.page.TableSupport;
import com.fanling.dreamland.common.util.DateUtils;
import com.fanling.dreamland.common.util.ShiroUtils;
import com.fanling.dreamland.common.util.SqlUtil;
import com.fanling.dreamland.common.util.StringUtils;
import com.fanling.dreamland.domain.system.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;


/**
 * web层通用数据处理
 */
public class BaseController {
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
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
        rspData.setData(list);
        rspData.setCount(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected HttpResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected HttpResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public HttpResult success() {
        return HttpResult.success();
    }

    /**
     * 返回失败消息
     */
    public HttpResult error() {
        return HttpResult.error();
    }

    /**
     * 返回成功消息
     */
    public HttpResult success(String message) {
        return HttpResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public HttpResult error(String message) {
        return HttpResult.error(message);
    }


    public SysUser getSysUser() {
        return ShiroUtils.getSysUser();
    }

    public void setSysUser(SysUser user) {
        ShiroUtils.setSysUser(user);
    }

    public String getUserId() {
        return getSysUser().getUserId();
    }

    public String getLoginName() {
        return getSysUser().getLoginName();
    }
}