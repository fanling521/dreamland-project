package com.fanling.dreamland.controller.system;

import com.fanling.dreamland.common.AjaxResult;
import com.fanling.dreamland.common.util.StringUtils;
import com.fanling.dreamland.controller.BaseController;
import com.fanling.dreamland.domain.system.SysMenu;
import com.fanling.dreamland.domain.system.SysRole;
import com.fanling.dreamland.service.system.ISysMenuService;
import lombok.extern.java.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    private String prefix = "system/menu";
    @Autowired
    private ISysMenuService sysMenuService;

    @RequiresPermissions("system:menu:view")
    @GetMapping()
    public String menu() {
        return prefix + "/list";
    }

    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    @ResponseBody
    public List<SysMenu> list(SysMenu menu) {
        List<SysMenu> menuList = sysMenuService.selectMenuList(menu);
        return menuList;
    }

    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") String parentId, ModelMap mmap) {
        SysMenu menu;
        if (StringUtils.isNotEmpty(parentId)) {
            //二级菜单
            menu = sysMenuService.selectMenuById(parentId);
        } else {
            //一级菜单
            menu = new SysMenu();
            menu.setMenuId(UUID.randomUUID().toString());
            menu.setMenuName("根");
        }
        mmap.put("menu", menu);
        return prefix + "/menu-add";
    }

    /**
     * 新增保存菜单
     */
    @RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysMenu menu) {
        return toAjax(sysMenuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") String menuId, ModelMap mmap) {
        mmap.put("menu", sysMenuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysMenu menu) {
        return toAjax(sysMenuService.updateMenu(menu));
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(SysMenu menu) {
        return sysMenuService.checkMenuNameUnique(menu);
    }

    /**
     * 加载角色菜单列表树
     */
    /*@GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(SysRole role)
    {
        List<Ztree> ztrees = sysMenuService.roleMenuTreeData(role);
        return ztrees;
    }*/

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") String menuId, ModelMap mmap) {
        mmap.put("menu", sysMenuService.selectMenuById(menuId));
        return prefix + "/tree";
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:menu:remove")
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") String menuId) {
        if (sysMenuService.selectCountMenuByParentId(menuId) > 0) {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (sysMenuService.selectCountRoleMenuByMenuId(menuId) > 0) {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        return toAjax(sysMenuService.deleteMenuById(menuId));
    }
}
