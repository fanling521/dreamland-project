package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.dreamland.auth.exception.AuthTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {
    /**
     * Token校验失败
     */
    @ExceptionHandler(AuthTokenException.class)
    public Object handleAuthorizationException(AuthTokenException e) {
        log.info("---> 您无权访问该资源");
        return R.error("您无权访问该资源");
    }

    /**
     * SQL唯一性检查
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Object handleSQL(SQLIntegrityConstraintViolationException e) {
        log.info("---> 重复提交，错误信息：" + e.getMessage());
        return R.error("重复提交，错误信息：" + e.getMessage());
    }

    /**
     * 参数空值验证
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleArgument(IllegalArgumentException e) {
        log.info("---> 参数空值，错误信息：" + e.getMessage());
        return R.error("参数空值，错误信息：" + e.getMessage());
    }

    /**
     * 内部网络问题
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConnectException.class)
    public Object handleConnect(ConnectException e) {
        log.info("---> 内部网络问题，错误信息：" + e.getMessage());
        return R.error("内部网络问题，错误信息：" + e.getMessage());
    }
}
