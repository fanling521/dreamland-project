package com.fanling.dreamland.mapper;

import com.fanling.dreamland.common.BaseMapper;
import com.fanling.dreamland.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户表的数据层
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过phone查询系统用户
     *
     * @param phone
     */
    SysUser selectByPhone(@Param("phone") String phone);

    /**
     * 通过email查询系统用户
     *
     * @param email
     */
    SysUser selectByEmail(@Param("email") String email);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone
     */
    int checkPhoneUnique(@Param("phone") String phone);

    /**
     * 校验email是否唯一
     *
     * @param email
     */
    int checkEmailUnique(@Param("email") String email);
}
