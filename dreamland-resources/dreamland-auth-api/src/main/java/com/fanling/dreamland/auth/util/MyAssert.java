package com.fanling.dreamland.auth.util;

import com.fanling.common.utils.StringUtils;
import org.springframework.lang.Nullable;

public class MyAssert {

    /**
     * 非空验证
     * @param object
     * @param message
     */
    public static void notNull(@Nullable Object object, String message) {
        if (StringUtils.isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }
}
