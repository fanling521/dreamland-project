package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.domain.SysUserRole;
import com.fanling.dreamland.mapper.SysUserMapper;
import com.fanling.dreamland.mapper.SysUserRoleMapper;
import com.fanling.dreamland.service.ISysUserService;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    //@Autowired
    //private LoginService loginService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return sysUserMapper.selectUserList(user);
    }

    @Override
    public SysUser selectUserByLoginName(String loginName) {
        return sysUserMapper.selectUserByLoginName(loginName);
    }

    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public SysUser selectUserByEmail(String email) {
        return null;
    }

    @Override
    public SysUser selectUserById(String userId) {
        return sysUserMapper.selectUserById(userId);
    }

    @Override
    public int deleteUserById(String userId) {
        // 删除用户与角色关联
        sysUserRoleMapper.deleteUserRoleByUserId(userId);
        return sysUserMapper.deleteUserById(userId);
    }

    @Override
    public int insertUser(SysUser user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setSalt(UUID.randomUUID().toString());
        //默认密码111111
        //user.setPassword(loginService.encryptPassword(user.getLoginName(), "111111", user.getSalt()));
        //新增用户
        int rows = sysUserMapper.insertUser(user);
        insertUserRole(user);
        return rows;
    }

    @Override
    public int updateUser(SysUser user) {
        String userId = user.getUserId();
        // 删除用户与角色关联
        sysUserRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        return sysUserMapper.updateUser(user);
    }

    @Override
    public int resetUserPwd(SysUser user) {
        return 0;
    }

    @Override
    public String checkLoginNameUnique(String loginName) {
        return null;
    }

    @Override
    public String checkPhoneUnique(SysUser user) {
        return null;
    }

    @Override
    public String checkEmailUnique(SysUser user) {
        return null;
    }

    @Override
    public String selectUserRoleGroup(String userId) {
        return null;
    }

    @Override
    public String selectUserPostGroup(String userId) {
        return null;
    }

    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport) {
        return null;
    }

    @Override
    public int changeStatus(SysUser user) {
        return 0;
    }

    @Override
    public int updateLoginUser(SysUser user) {
        return sysUserMapper.updateUser(user);
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        String[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            for (String roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                sysUserRoleMapper.batchUserRole(list);
            }
        }
    }

}
