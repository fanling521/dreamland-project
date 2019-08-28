package com.fanling.dreamland.auth.utils;


import com.fanling.dreamland.auth.DefaultEnum;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {

    /**
     * 密码加密
     *
     * @param phone
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String phone, String password, String salt) {
        return new Md5Hash(phone + password + salt).toHex().toUpperCase();
    }

    /**
     * 默认密码123456
     * @param phone
     * @return
     */
    public static String defaultPassword(String phone) {
        return encryptPassword(phone, DefaultEnum.DEFAULT_PASSWORD.getCode(), DefaultEnum.DEFAULT_SALT.getCode());
    }
}
