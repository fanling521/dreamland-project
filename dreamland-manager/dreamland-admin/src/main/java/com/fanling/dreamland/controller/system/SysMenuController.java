package com.fanling.dreamland.controller.system;

import com.fanling.dreamland.common.AjaxResult;
import com.fanling.dreamland.common.page.TableDataInfo;
import com.fanling.dreamland.controller.BaseController;
import com.fanling.dreamland.domain.system.SysMenu;
import com.fanling.dreamland.domain.system.SysRole;
import com.fanling.dreamland.service.system.ISysMenuService;
import com.fanling.dreamland.service.system.ISysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    private String prefix = "system/menu";
    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleService sysRoleService;

    @RequiresPermissions("system:menu:view")
    @GetMapping()
    public String menu() {
        return prefix + "/menu-list";
    }

    @RequiresPermissions("system:menu:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysMenu menu) {
        startPage();
        List<SysMenu> list = sysMenuService.selectMenuList(menu);
        return getDataTable(list);
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("tree", sysMenuService.selectMenuTree());
        mmap.put("roles", sysRoleService.selectRoleAll());
        return prefix + "/menu-add";
    }

    /**
     * 新增保存菜单
     */
    @RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysMenu menu) {
        menu.setParentName(getMenuName(menu.getParentId()));
        return toAjax(sysMenuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") String menuId, ModelMap mmap) {
        mmap.put("menu", sysMenuService.selectMenuById(menuId));
        List<SysRole> list = sysRoleService.selectRolesByMenuId(menuId);
        if (list == null || list.size() == 0) {
            list = sysRoleService.selectRoleAll();
        }
        mmap.put("roles", list);
        mmap.put("tree", sysMenuService.selectMenuTree());
        return prefix + "/menu-edit";
    }

    /**
     * 修改保存菜单
     */
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysMenu menu) {
        menu.setParentName(getMenuName(menu.getParentId()));
        return toAjax(sysMenuService.updateMenu(menu));
    }

    /**
     * 获取菜单名
     *
     * @param parentId
     * @return
     */
    private String getMenuName(String parentId) {
        if ("4438c79d-a398-4b35-ac2c-90bc70ddd44c".equals(parentId)) {
            return "根";
        } else {
            SysMenu sysMenu = sysMenuService.selectMenuById(parentId);
            if (sysMenu != null) {
                return sysMenu.getMenuName();
            }
        }
        return "";
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:menu:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String menuId) {
        if (sysMenuService.selectCountMenuByParentId(menuId) > 0) {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (sysMenuService.selectCountRoleMenuByMenuId(menuId) > 0) {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        return toAjax(sysMenuService.deleteMenuById(menuId));
    }
}
