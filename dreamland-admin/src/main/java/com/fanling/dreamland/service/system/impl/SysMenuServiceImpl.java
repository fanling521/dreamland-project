package com.fanling.dreamland.service.system.impl;

import com.fanling.dreamland.common.AjaxResult;
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
        return sysMenuMapper.selectMenuList(menu);
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
    public int deleteMenuById(String menuId) {
        return sysMenuMapper.deleteMenuById(menuId);
    }

    @Override
    public SysMenu selectMenuById(String menuId) {
        return sysMenuMapper.selectMenuById(menuId);
    }

    @Override
    public int selectCountMenuByParentId(String parentId) {
        return sysMenuMapper.selectCountMenuByParentId(parentId);
    }

    @Override
    public List<AjaxResult> selectMenuTree() {
        List<SysMenu> list = sysMenuMapper.selectMenuTree();
        List<AjaxResult> results = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("id",sysMenu.getMenuId());
            ajaxResult.put("value",sysMenu.getMenuName());
            results.add(ajaxResult);
        }
        return results;
    }

    @Override
    public int selectCountRoleMenuByMenuId(String menuId) {
        return sysRoleMenuMapper.selectCountRoleMenuByMenuId(menuId);
    }

    @Override
    public int insertMenu(SysMenu menu) {
        menu.setMenuId(UUID.randomUUID().toString());
        return sysMenuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return sysMenuMapper.updateMenu(menu);
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        SysMenu info = sysMenuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().equals(menu.getMenuId())) {
            return "1";
        }
        return "0";
    }
}
