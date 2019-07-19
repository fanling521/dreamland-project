package com.fanling.dreamland.service.impl;
import com.fanling.dreamland.entitys.system.SysRole;
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

    /**
     * 根据条件返回角色表的信息，可查询和分页
     *
     * @param role
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return sysRoleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     */
    @Override
    public List<SysRole> selectRolesByUserId(String userId) {
        return sysRoleMapper.selectRolesByUserId(userId);
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId
     */
    @Override
    public SysRole selectRoleById(String roleId) {
        return sysRoleMapper.selectRoleById(roleId);
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     */
    @Override
    public int deleteRoleById(String roleId) {
        return sysRoleMapper.deleteRoleById(roleId);
    }

    /**
     * 修改角色信息
     *
     * @param role
     */
    @Override
    public int updateRole(SysRole role) {
        return sysRoleMapper.updateRole(role);
    }

    /**
     * 新增角色信息
     *
     * @param role
     */
    @Override
    public int insertRole(SysRole role) {
        role.setRole_status("0");
        role.setRole_id(UUID.randomUUID().toString());
        return sysRoleMapper.insertRole(role);
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
        return sysRoleMapper.selectRoleList(null);
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
        List<SysRole> perms = sysRoleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRole_key().trim().split(",")));
            }
        }
        return permsSet;
    }
}
