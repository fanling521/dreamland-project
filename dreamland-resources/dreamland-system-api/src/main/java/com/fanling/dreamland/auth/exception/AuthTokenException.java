package com.fanling.dreamland.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Token验证异常
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthTokenException extends RuntimeException {
    private String defaultMsg;
}
