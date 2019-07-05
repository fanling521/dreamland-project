package com.fanling.dreamland.controller;

import com.fanling.dreamland.common.HttpResult;
import com.fanling.dreamland.common.util.ServletUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        if (ServletUtils.isAjaxRequest(request)) {
            return HttpResult.error(e.getMessage());
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("unauth");
            return modelAndView;
        }
    }
}
