package com.fanling.dreamland.controller.system;

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
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap mmap) {
        mmap.put("user", sysUserService.selectUserById(userId));
        mmap.put("roles", sysRoleService.selectRolesByUserId(userId));
        return prefix + "/edit";
    }
}
