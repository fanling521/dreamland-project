package com.fanling.dreamland.service.impl;

import com.fanling.dreamland.common.service.ServiceImpl;
import com.fanling.dreamland.config.SystemEnum;
import com.fanling.dreamland.entity.SysRole;
import com.fanling.dreamland.mapper.SysRoleMapper;
import com.fanling.dreamland.mapper.SysUserRoleMapper;
import com.fanling.dreamland.service.ISysRoleService;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    @Override
    public List<SysRole> ListById(String userId) {
        List<SysRole> userRoles = sysRoleMapper.listById(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getRole_id().equals(userRole.getRole_id())) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 新增角色信息
     *
     * @param role
     */
    @Override
    public int insert(SysRole role) {
        role.setRole_status(SystemEnum.USER_COMMON.getCode());
        role.setRole_id(UUID.randomUUID().toString());
        return sysRoleMapper.insert(role);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName
     */
    @Override
    public boolean checkRoleNameUnique(String roleName) {
        return sysRoleMapper.checkRoleNameUnique(roleName) > 0;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey
     */
    @Override
    public boolean checkRoleKeyUnique(String roleKey) {
        return sysRoleMapper.checkRoleKeyUnique(roleKey) > 0;
    }

    /**
     * 获取所有角色列表
     *
     * @return
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleMapper.list(null);
    }

    /**
     * 检查角色是否分配了用户
     *
     * @param roleId
     * @return
     */
    @Override
    public boolean checkUserCount(String roleId) {
        return sysUserRoleMapper.countUserRoleByRoleId(roleId) > 0;
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(String userId) {
        List<SysRole> perms = sysRoleMapper.listById(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRole_key().trim().split(",")));
            }
        }
        return permsSet;
    }
}
