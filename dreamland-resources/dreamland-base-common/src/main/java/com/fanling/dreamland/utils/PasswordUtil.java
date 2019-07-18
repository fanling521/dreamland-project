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
    public static String EncryptPassword(String str1, String password, String salt) {
        return new Md5Hash(str1 + password + salt).toHex();
    }

    /**
     * 生成APP_KEY
     *
     * @param appId
     * @return
     */
    public static String getAppKey(String appId) {
        String random = Integer.toString(new Random().nextInt(1000));
        String security = "JIN_WU_TONG";
        return EncryptPassword(random, appId, security).toUpperCase();
    }
}
