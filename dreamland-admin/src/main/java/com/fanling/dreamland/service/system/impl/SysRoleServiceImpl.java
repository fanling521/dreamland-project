package com.fanling.dreamland.service.system.impl;

import com.fanling.dreamland.common.util.StringUtils;
import com.fanling.dreamland.domain.system.SysRole;
import com.fanling.dreamland.domain.system.SysUserRole;
import com.fanling.dreamland.mapper.system.SysRoleMapper;
import com.fanling.dreamland.service.system.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getRoleId().equals(userRole.getRoleId())) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleMapper.selectRoleList(null);
    }

    @Override
    public SysRole selectRoleById(String roleId) {
        return sysRoleMapper.selectRoleById(roleId);
    }

    @Override
    public boolean deleteRoleById(String roleId) {
        return sysRoleMapper.deleteRoleById(roleId) > 0;
    }

    @Override
    public int insertRole(SysRole role) {
        role.setStatus("0");
        role.setRoleId(UUID.randomUUID().toString());
        return sysRoleMapper.insertRole(role);
    }

    @Override
    public int updateRole(SysRole role) {
        return sysRoleMapper.updateRole(role);
    }

    @Override
    public String checkRoleNameUnique(SysRole role) {
        SysRole info = sysRoleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().equals(role.getRoleId())) {
            return "1";
        }
        return "0";
    }

    @Override
    public String checkRoleKeyUnique(SysRole role) {
        SysRole info = sysRoleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().equals(role.getRoleId())) {
            return "1";
        }
        return "0";
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
