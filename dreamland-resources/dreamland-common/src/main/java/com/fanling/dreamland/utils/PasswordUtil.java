package com.fanling.dreamland.utils;


import com.fanling.dreamland.entitys.system.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;

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


    /**
     * 验证密码
     *
     * @param user
     * @param newPassword
     * @return
     */
    public static boolean matches(SysUser user, String newPassword) {
        String pwd = PasswordUtil.encryptPassword(user.getPhone(), newPassword, user.getSalt());
        return user.getPassword().equals(pwd);
    }

    public static void main(String[] args) {
        String uui= "803236eb-d75c-4c39-b44c-9296d07efb7d";
        System.out.println(uui);
        System.out.println(encryptPassword("13451585202","e10adc3949ba59abbe56e057f20f883e",uui));
    }
}
