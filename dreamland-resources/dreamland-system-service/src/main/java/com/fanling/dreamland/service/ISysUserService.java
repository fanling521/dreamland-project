package com.fanling.dreamland.service;


import com.fanling.dreamland.entitys.request.SysUserReq;
import com.fanling.dreamland.entitys.request.SysUserSearch;
import com.fanling.dreamland.entitys.system.SysUser;

import java.util.List;

/**
 * 系统用户表
 */
public interface ISysUserService {
    /**
     * 根据条件返回系统用户表的信息，可查询和分页
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectUserList(SysUserSearch sysUser);

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
     * 通过系统用户ID查询系统用户
     *
     * @param userId
     */
    SysUser selectUserById(String userId);

    /**
     * 通过系统用户ID删除系统用户
     *
     * @param userId
     */
    int deleteUserById(String userId);

    /**
     * 修改系统用户
     *
     * @param sysUserReq
     */
    int updateUser(SysUserReq sysUserReq);

    /**
     * 更新用户信息
     *
     * @param sysUser
     * @return
     */
    int updateUserLoginInfo(SysUser sysUser);

    /**
     * 新增系统用户
     *
     * @param sysUserReq
     */
    int insertUser(SysUserReq sysUserReq);

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
