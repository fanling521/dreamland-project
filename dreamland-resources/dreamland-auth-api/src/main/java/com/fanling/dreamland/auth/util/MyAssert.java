package com.fanling.dreamland.auth.util;

import com.fanling.common.utils.StringUtils;
import org.springframework.lang.Nullable;

public class MyAssert {

    /**
     * 非空验证
     * @param string
     * @param message
     */
    public static void notNull(@Nullable String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }
}
