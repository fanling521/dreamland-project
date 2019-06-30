package com.fanling.dreamland.service.system.impl;

import com.fanling.dreamland.common.util.StringUtils;
import com.fanling.dreamland.common.util.TreeUtils;
import com.fanling.dreamland.domain.system.SysMenu;
import com.fanling.dreamland.domain.system.SysUser;
import com.fanling.dreamland.mapper.system.SysMenuMapper;
import com.fanling.dreamland.mapper.system.SysRoleMenuMapper;
import com.fanling.dreamland.service.system.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        return TreeUtils.getChildPerms(sysMenuMapper.selectMenusByUserId(user.getUserId()), "4438c79d-a398-4b35-ac2c-90bc70ddd44c");
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuAll() {
        return null;
    }

    @Override
    public Set<String> selectPermsByUserId(String userId) {
        List<String> perms = sysMenuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public Map<String, String> selectPermsAll() {
        return null;
    }

    @Override
    public int deleteMenuById(String menuId) {
        return 0;
    }

    @Override
    public SysMenu selectMenuById(String menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(String parentId) {
        return 0;
    }

    @Override
    public int selectCountRoleMenuByMenuId(String menuId) {
        return 0;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        return null;
    }
}
