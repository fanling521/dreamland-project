package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.common.service.ServiceImpl;
import com.fanling.dreamland.config.SystemEnum;
import com.fanling.dreamland.entity.SysUser;
import com.fanling.dreamland.entity.SysUserRole;
import com.fanling.dreamland.mapper.SysUserMapper;
import com.fanling.dreamland.mapper.SysUserRoleMapper;
import com.fanling.dreamland.service.ISysUserService;
import com.fanling.dreamland.utils.PasswordUtil;
import com.fanling.dreamland.utils.StringUtils;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过phone查询系统用户
     *
     * @param phone
     */
    @Override
    public SysUser selectUserByPhone(String phone) {
        return sysUserMapper.selectByPhone(phone);
    }

    /**
     * 通过email查询系统用户
     *
     * @param email
     */
    @Override
    public SysUser selectUserByEmail(String email) {
        return sysUserMapper.selectByEmail(email);
    }
    /**
     * 通过系统用户ID删除系统用户
     *
     * @param userId
     */
    @Override
    public int delete(String userId) {
        // 删除用户与角色关联
        sysUserRoleMapper.deleteUserRoleByUserId(userId);
        return sysUserMapper.delete(userId);
    }

    /**
     * 修改系统用户
     *
     * @param sysUser
     */
    @Override
    public int update(SysUser sysUser) {
        // 删除用户与角色关联
        sysUserRoleMapper.deleteUserRoleByUserId(sysUser.getUser_id());
        // 新增用户与角色管理
        insertUserRole(sysUser);
        return sysUserMapper.update(sysUser);
    }

    /**
     * 更新用户信息
     *
     * @param sysUser
     */
    @Override
    public void updateUserLoginInfo(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    /**
     * 新增系统用户
     *
     * @param sysUser
     */
    @Override
    public int insert(SysUser sysUser) {
        sysUser.setUser_id(UUID.randomUUID().toString());
        sysUser.setSalt(Long.toString(new Date().getTime()));
        sysUser.setStatus(SystemEnum.USER_COMMON.getCode());
        sysUser.setPassword(PasswordUtil.encryptPassword(sysUser.getLogin_name(), SystemEnum.DEFAULT_PWD.getCode(), sysUser.getSalt()));
        int rows = sysUserMapper.insert(sysUser);
        insertUserRole(sysUser);
        return rows;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param phone
     */
    @Override
    public boolean checkPhoneUnique(String phone) {
        return sysUserMapper.checkPhoneUnique(phone) > 0;
    }

    /**
     * 校验email是否唯一
     *
     * @param email
     */
    @Override
    public boolean checkEmailUnique(String email) {
        return sysUserMapper.checkEmailUnique(email) > 0;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    private void insertUserRole(SysUser user) {
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
