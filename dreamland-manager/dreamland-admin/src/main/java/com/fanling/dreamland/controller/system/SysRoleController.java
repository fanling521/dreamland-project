package com.fanling.dreamland.controller.system;

import com.fanling.dreamland.common.AjaxResult;
import com.fanling.dreamland.common.page.TableDataInfo;
import com.fanling.dreamland.controller.BaseController;
import com.fanling.dreamland.domain.system.SysRole;
import com.fanling.dreamland.service.system.ISysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    private String prefix = "system/role";

    @Autowired
    private ISysRoleService sysRoleService;

    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role() {
        return prefix + "/role-list";
    }

    /**
     * 查看用户列表
     *
     * @param role
     * @return
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> list = sysRoleService.selectRoleList(role);
        return getDataTable(list);
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/role-add";
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("system:role:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysRole role) {
        role.setCreateBy(getLoginName());
        return toAjax(sysRoleService.insertRole(role));

    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") String roleId, ModelMap mmap) {
        mmap.put("role", sysRoleService.selectRoleById(roleId));
        return prefix + "/role-edit";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("system:role:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysRole role) {
        return toAjax(sysRoleService.updateRole(role));
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequiresPermissions("system:role:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String roleId) {
        if ("d92a9960-dc74-4a55-b3bd-46ec909205e0".equals(roleId)) {
            return error("默认角色不可删除");
        }
        return toAjax(sysRoleService.deleteRoleById(roleId));
    }
}
