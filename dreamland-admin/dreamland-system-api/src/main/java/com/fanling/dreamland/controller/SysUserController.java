package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.page.TableDataInfo;
import com.fanling.dreamland.shiro.service.ISysUserService;
import com.fanling.dreamland.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    protected ISysUserService sysUserService;

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> list = sysUserService.selectUserList(sysUser);
        return getDataTable(list);
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @PostMapping("/add")
    public R addSave(@RequestBody SysUser user) {
        return toAjax(sysUserService.insertUser(user));
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @PostMapping("/edit")
    public R editSave(@RequestBody SysUser user) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(user.getUserId())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(sysUserService.updateUser(user));
    }

    @PostMapping("/get/{userId}")
    public R getUser(@PathVariable("userId") String userId) {
        return R.success(sysUserService.selectUserById(userId));
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequiresPermissions("system:user:remove")
    @PostMapping("/remove")
    public R remove(String userId) {
        if ("d466b2b8-223d-4983-bb34-c4253621bb58".equals(userId)) {
            return error("不允许删除超级管理员用户");
        }
        return toAjax(sysUserService.deleteUserById(userId));
    }
}
