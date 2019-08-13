package com.fanling.dreamland.service;

import com.fanling.dreamland.common.service.IService;
import com.fanling.dreamland.entity.SysUser;

/**
 * 系统用户表
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 通过phone查询系统用户
     *
     * @param phone
     */
    SysUser selectUserByPhone(String phone);

    /**
     * 通过email查询系统用户
     *
     * @param email
     */
    SysUser selectUserByEmail(String email);

    /**
     * 更新用户信息
     *
     * @param sysUser
     */
    void updateUserLoginInfo(SysUser sysUser);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone
     */
    boolean checkPhoneUnique(String phone);

    /**
     * 校验email是否唯一
     *
     * @param email
     */
    boolean checkEmailUnique(String email);
}
