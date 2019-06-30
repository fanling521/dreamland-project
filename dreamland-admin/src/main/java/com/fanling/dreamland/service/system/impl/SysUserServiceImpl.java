package com.fanling.dreamland.service.system.impl;

import com.fanling.dreamland.domain.system.SysUser;
import com.fanling.dreamland.mapper.system.SysUserMapper;
import com.fanling.dreamland.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

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
        return null;
    }

    @Override
    public int deleteUserById(String userId) {
        return 0;
    }

    @Override
    public int deleteUserByIds(String ids) throws Exception {
        return 0;
    }

    @Override
    public int insertUser(SysUser user) {
        return 0;
    }

    @Override
    public int updateUser(SysUser user) {
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
}
