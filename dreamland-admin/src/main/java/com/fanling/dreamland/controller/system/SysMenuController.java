package com.fanling.dreamland.controller.system;

import com.fanling.dreamland.domain.system.SysMenu;
import com.fanling.dreamland.service.system.ISysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class SysMenuController {
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

}
