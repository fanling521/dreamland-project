package com.fanling.dreamland.mapper;

import com.fanling.dreamland.entitys.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户表的数据层
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据条件返回系统用户表的信息，可查询和分页
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 通过phone查询系统用户
     *
     * @param phone
     */
    SysUser selectUserByPhone(@Param("phone") String phone);

    /**
     * 通过email查询系统用户
     *
     * @param email
     */
    SysUser selectUserByEmail(@Param("email") String email);

    /**
     * 通过系统用户ID查询系统用户
     *
     * @param userId
     */
    SysUser selectUserById(@Param("user_id") String userId);

    /**
     * 通过系统用户ID删除系统用户
     *
     * @param userId
     */
    int deleteUserById(@Param("user_id") String userId);

    /**
     * 修改系统用户
     *
     * @param user
     */
    int updateUser(SysUser user);

    /**
     * 新增系统用户
     *
     * @param user
     */
    int insertUser(SysUser user);
}
