package com.fanling.dreamland.utils;


import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;

public class PasswordUtil {

    /**
     * 密码加密
     *
     * @param str1
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String str1, String password, String salt) {
        return new Md5Hash(str1 + password + salt).toHex().toUpperCase();
    }
}
