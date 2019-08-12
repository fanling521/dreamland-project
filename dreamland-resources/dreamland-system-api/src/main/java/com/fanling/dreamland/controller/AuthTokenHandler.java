package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.common.exception.AuthTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class AuthTokenHandler {
    /**
     * Token校验失败
     */
    @ExceptionHandler(AuthTokenException.class)
    public Object handleAuthorizationException(AuthTokenException e) {
        return R.error("权限验证，信息：" + e.getDefaultMsg());
    }

    /**
     * SQL唯一性检查
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Object handleSQL(SQLIntegrityConstraintViolationException e) {
        return R.error("唯一性检查，信息：" + e.getMessage());
    }
}
