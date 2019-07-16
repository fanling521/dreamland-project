package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.domain.LoginReq;
import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.shiro.ShiroUtils;
import com.fanling.dreamland.shiro.service.ISysMenuService;
import com.fanling.dreamland.shiro.service.ISysRoleService;
import com.fanling.dreamland.shiro.service.LoginService;
import com.fanling.dreamland.shiro.service.TokenService;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.fanling.dreamland.R.error;
import static com.fanling.dreamland.R.success;

@RestController
@RequestMapping("/system")
public class LoginController extends BaseController {

    @Autowired
    protected TokenService tokenService;

    @Autowired
    protected LoginService loginService;

    @Autowired
    protected ISysRoleService sysRoleService;

    @Autowired
    protected ISysMenuService menuService;

    /**
     * 登录请求
     *
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginReq loginReq) {
        try {
            //登录验证成功返回你的token
            SysUser sysUser = loginService.login(loginReq.getLoginName(), loginReq.getPassword());
            return R.success(tokenService.createToken(sysUser.getUserId(),
                    sysUser.getLoginName(), sysUser.getPassword()));
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping("/get_user_info")
    public R getUserInfo() {
        //获取用户信息和权限，路由等信息
        R result = new R();
        SysUser sysUser = ShiroUtils.getSysUser();
        result.put("userId", sysUser.getUserId());
        result.put("userName", sysUser.getUserName());
        result.put("roles", sysRoleService.selectRoleKeys(sysUser.getUserId()));
        return R.success(result);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public R logout(@RequestParam("userId") String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            tokenService.expireToken(userId);
            ShiroUtils.logout();
        }
        return R.success();
    }
}
