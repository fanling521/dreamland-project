package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.common.exception.AuthTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthTokenExceptionHandler {
    /**
     * Token校验失败
     */
    @ExceptionHandler(AuthTokenException.class)
    public Object handleAuthorizationException(AuthTokenException e) {
        return R.error(e.getDefaultMsg());
    }
}
