package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.domain.SysRole;
import com.fanling.dreamland.page.TableDataInfo;
import com.fanling.dreamland.shiro.ShiroUtils;
import com.fanling.dreamland.shiro.service.ISysRoleService;
import com.fanling.dreamland.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

    @Autowired
    protected ISysRoleService sysRoleService;

    /**
     * 查看用户列表
     *
     * @param role
     * @return
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> list = sysRoleService.selectRoleList(role);
        return getDataTable(list);
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("system:role:add")
    @PostMapping("/add")
    public R addSave(@RequestBody SysRole role) {
        role.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(sysRoleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("system:role:edit")
    @PostMapping("/edit")
    public R editSave(@RequestBody SysRole role) {
        return toAjax(sysRoleService.updateRole(role));
    }

    /**
     * 获取角色
     *
     * @param roleId
     * @return
     */
    @PostMapping("/get/{roleId}")
    public R getUser(@PathVariable("roleId") String roleId) {
        return R.success(sysRoleService.selectRoleById(roleId));
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequiresPermissions("system:role:remove")
    @PostMapping("/remove")
    public R remove(String roleId) {
        if ("d92a9960-dc74-4a55-b3bd-46ec909205e0".equals(roleId)) {
            return error("默认角色不可删除");
        }
        return toAjax(sysRoleService.deleteRoleById(roleId));
    }

    /**
     * 获取所有角色信息
     *
     * @return
     */
    @PostMapping("/select_all_role")
    public R selectAllRole() {
        return R.success(sysRoleService.selectRoleAll());
    }

    /**
     * 根据用户选择角色
     *
     * @param userId
     * @return
     */
    @PostMapping("/select_roles/{userId}")
    public R selectRoleByUserId(@PathVariable String userId) {
        return R.success(sysRoleService.selectRolesByUserId(userId));
    }
}
