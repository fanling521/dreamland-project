package com.fanling.dreamland.service.system.impl;

import com.fanling.dreamland.domain.system.SysRole;
import com.fanling.dreamland.domain.system.SysUserRole;
import com.fanling.dreamland.mapper.system.SysRoleMapper;
import com.fanling.dreamland.service.system.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return sysRoleMapper.selectRoleList(role);
    }

    @Override
    public Set<String> selectRoleKeys(String userId) {
        return null;
    }

    @Override
    public List<SysRole> selectRolesByUserId(String userId) {
        return null;
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleMapper.selectRoleList(null);
    }

    @Override
    public SysRole selectRoleById(String roleId) {
        return null;
    }

    @Override
    public boolean deleteRoleById(String roleId) {
        return false;
    }

    @Override
    public int deleteRoleByIds(String ids) throws Exception {
        return 0;
    }

    @Override
    public int insertRole(SysRole role) {
        return 0;
    }

    @Override
    public int updateRole(SysRole role) {
        return 0;
    }

    @Override
    public int authDataScope(SysRole role) {
        return 0;
    }

    @Override
    public String checkRoleNameUnique(SysRole role) {
        return null;
    }

    @Override
    public String checkRoleKeyUnique(SysRole role) {
        return null;
    }

    @Override
    public int countUserRoleByRoleId(String roleId) {
        return 0;
    }

    @Override
    public int changeStatus(SysRole role) {
        return 0;
    }

    @Override
    public int deleteAuthUser(SysUserRole userRole) {
        return 0;
    }

    @Override
    public int deleteAuthUsers(String roleId, String userIds) {
        return 0;
    }

    @Override
    public int insertAuthUsers(String roleId, String userIds) {
        return 0;
    }
}
