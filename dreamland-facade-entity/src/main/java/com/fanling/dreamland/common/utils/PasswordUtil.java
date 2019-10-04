package com.fanling.dreamland.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {
    /**
     * 密码加密
     *
     * @param account
     * @param password
     * @param salt
     * @return
     */
    private static String encryptPassword(String account, String password, String salt) {
        return new Md5Hash(account + password + salt).toHex().toUpperCase();
    }

    /**
     * 默认密码123456
     *
     * @param account
     * @return
     */
    public static String defaultPassword(String account) {
        return encryptPassword(account, "e10adc3949ba59abbe56e057f20f883e", "ebf37607-8db0-48a9-9768-d10f71b975c5");
    }

}
