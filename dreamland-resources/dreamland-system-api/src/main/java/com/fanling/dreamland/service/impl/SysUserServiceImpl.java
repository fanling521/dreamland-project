package com.fanling.dreamland.service.impl;
import com.fanling.dreamland.entitys.request.SysUserReq;
import com.fanling.dreamland.entitys.system.SysUser;
import com.fanling.dreamland.entitys.system.SysUserRole;
import com.fanling.dreamland.mapper.SysUserMapper;
import com.fanling.dreamland.mapper.SysUserRoleMapper;
import com.fanling.dreamland.service.ISysUserService;
import com.fanling.dreamland.utils.PasswordUtil;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 系统用户表业务层
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据条件返回系统用户表的信息，可查询和分页
     *
     * @param sysUser
     * @return
     */
    public List<SysUser> selectUserList(SysUser sysUser) {
        return sysUserMapper.selectUserList(sysUser);
    }

    /**
     * 通过phone查询系统用户
     *
     * @param phone
     */
    @Override
    public SysUser selectUserByPhone(String phone) {
        return sysUserMapper.selectUserByPhone(phone);
    }

    /**
     * 通过email查询系统用户
     *
     * @param email
     */
    @Override
    public SysUser selectUserByEmail(String email) {
        return sysUserMapper.selectUserByEmail(email);
    }

    /**
     * 通过系统用户ID查询系统用户
     *
     * @param userId
     */
    @Override
    public SysUser selectUserById(String userId) {
        return sysUserMapper.selectUserById(userId);
    }

    /**
     * 通过系统用户ID删除系统用户
     *
     * @param userId
     */
    @Override
    public int deleteUserById(String userId) {
        // 删除用户与角色关联
        sysUserRoleMapper.deleteUserRoleByUserId(userId);
        return sysUserMapper.deleteUserById(userId);
    }

    /**
     * 修改系统用户
     *
     * @param sysUserReq
     */
    @Override
    public int updateUser(SysUserReq sysUserReq) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserReq, sysUser);
        // 删除用户与角色关联
        sysUserRoleMapper.deleteUserRoleByUserId(sysUser.getUser_id());
        // 新增用户与角色管理
        insertUserRole(sysUser);
        return sysUserMapper.updateUser(sysUser);
    }

    /**
     * 更新用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public int updateUserLoginInfo(SysUser sysUser) {
        return sysUserMapper.updateUser(sysUser);
    }

    /**
     * 新增系统用户
     *
     * @param sysUserReq
     */
    @Override
    public int insertUser(SysUserReq sysUserReq) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserReq, sysUser);
        sysUser.setUser_id(UUID.randomUUID().toString());
        sysUser.setSalt(Long.toString(new Date().getTime()));
        sysUser.setStatus("0");
        sysUser.setPassword(PasswordUtil.encryptPassword(sysUser.getPhone(), "123456", sysUser.getSalt()));
        int rows = sysUserMapper.insertUser(sysUser);
        insertUserRole(sysUser);
        return rows;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        String[] roles = user.getRole_ids();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            for (String roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUser_id(user.getUser_id());
                ur.setRole_id(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                sysUserRoleMapper.batchUserRole(list);
            }
        }
    }
}
