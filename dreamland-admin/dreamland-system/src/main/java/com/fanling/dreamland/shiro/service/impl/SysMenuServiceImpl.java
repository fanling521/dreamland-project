package com.fanling.dreamland.shiro.service.impl;


import com.fanling.dreamland.R;
import com.fanling.dreamland.domain.SysMenu;
import com.fanling.dreamland.domain.SysMenuPure;
import com.fanling.dreamland.domain.SysRoleMenu;
import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.mapper.SysMenuMapper;
import com.fanling.dreamland.mapper.SysRoleMenuMapper;
import com.fanling.dreamland.shiro.service.ISysMenuService;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.utils.TreeUtils;
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
    public List<SysMenuPure> selectMenusByUserId(String userId) {
        return TreeUtils.getChildPerms(sysMenuMapper.selectMenusByUserId(userId),
                "4438c79d-a398-4b35-ac2c-90bc70ddd44c");
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
    public List<R> selectMenuTree() {
        List<SysMenu> list = sysMenuMapper.selectMenuTree();
        List<R> results = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            R httpResult = new R();
            httpResult.put("id", sysMenu.getMenuId());
            httpResult.put("value", sysMenu.getMenuName());
            results.add(httpResult);
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
        insertRoleMenu(menu);
        return sysMenuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(SysMenu menu) {
        sysRoleMenuMapper.deleteRoleMenuByMenuId(menu.getMenuId());
        insertRoleMenu(menu);
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

    /**
     * 批量插入菜单和角色信息
     *
     * @param sysMenu
     */
    public void insertRoleMenu(SysMenu sysMenu) {
        String[] roles = sysMenu.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysRoleMenu> list = new ArrayList<>();

            for (String roleId : roles) {
                SysRoleMenu ur = new SysRoleMenu();
                ur.setMenuId(sysMenu.getMenuId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                sysRoleMenuMapper.batchRoleMenu(list);
            }
        }
    }
}
