package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.domain.SysRole;
import com.fanling.dreamland.mapper.SysRoleMapper;
import com.fanling.dreamland.mapper.SysUserRoleMapper;
import com.fanling.dreamland.service.ISysRoleService;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return sysRoleMapper.selectRoleList(role);
    }

    @Override
    public Set<String> selectRoleKeys(String userId) {
        List<SysRole> perms = sysRoleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
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
    public List<SysRole> selectRolesByMenuId(String menuId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByMenuId(menuId);
        for (SysRole userRole : userRoles) {
            userRole.setFlag(true);
        }
        return userRoles;
    }

    @Override
    public int countUserRoleByRoleId(String roleId) {
        return sysUserRoleMapper.countUserRoleByRoleId(roleId);
    }

    @Override
    public int changeStatus(SysRole role) {
        return sysRoleMapper.updateRole(role);
    }
}
