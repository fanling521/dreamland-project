package com.fanling.dreamland.controller;

import com.fanling.common.R;
import com.fanling.dreamland.auth.exception.AuthTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class WebExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * Token校验失败
     */
    @ExceptionHandler(AuthTokenException.class)
    public Object handleAuthorizationException(AuthTokenException e) {
        logger.info("---> 权限验证，错误信息：" + e.getMessage());
        return R.error("权限验证，错误信息：" + e.getMessage());
    }

    /**
     * SQL唯一性检查
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Object handleSQL(SQLIntegrityConstraintViolationException e) {
        logger.info("---> 重复提交，错误信息：" + e.getMessage());
        return R.error("重复提交，错误信息：" + e.getMessage());
    }

    /**
     * 参数空值验证
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleArgument(IllegalArgumentException e) {
        logger.info("---> 参数空值，错误信息：" + e.getMessage());
        return R.error("参数空值，错误信息：" + e.getMessage());
    }
}
