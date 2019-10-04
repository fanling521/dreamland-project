package com.fanling.dreamland.common.utils;

public class MyAssert {
    /**
     * 非空验证
     *
     * @param string
     * @param message
     */
    public static void notNull(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }
}
