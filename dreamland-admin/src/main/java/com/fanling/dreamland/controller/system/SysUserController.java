package com.fanling.dreamland.controller.system;

import com.fanling.dreamland.common.AjaxResult;
import com.fanling.dreamland.common.page.TableDataInfo;
import com.fanling.dreamland.controller.BaseController;
import com.fanling.dreamland.domain.system.SysUser;
import com.fanling.dreamland.service.system.ISysRoleService;
import com.fanling.dreamland.service.system.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user-list";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = sysUserService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", sysRoleService.selectRoleAll());
        return prefix + "/user-add";
    }


    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysUser user) {
        return toAjax(sysUserService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap mmap) {
        mmap.put("user", sysUserService.selectUserById(userId));
        mmap.put("roles", sysRoleService.selectRolesByUserId(userId));
        return prefix + "/user-edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysUser user) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(sysUserService.updateUser(user));
    }

    @RequiresPermissions("system:user:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String userId) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(userId)) {
            return error("不允许删除超级管理员用户");
        }
        return toAjax(sysUserService.deleteUserById(userId));
    }
}
