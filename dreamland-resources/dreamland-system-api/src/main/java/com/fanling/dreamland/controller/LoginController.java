package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.auth.JwtTokenService;
import com.fanling.dreamland.auth.annotations.UseJwtToken;
import com.fanling.dreamland.entitys.request.LoginForm;
import com.fanling.dreamland.entitys.system.SysUser;
import com.fanling.dreamland.service.ISysRoleService;
import com.fanling.dreamland.service.ISysUserService;
import com.fanling.dreamland.utils.PasswordUtil;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(tags = "登录和用户验证")
@RestController
@RequestMapping("/system")
public class LoginController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @UseJwtToken(required = false)
    @ApiOperation(value = "用户登录", notes = "账号和密码登录获得token")
    @ApiImplicitParam(name = "loginForm", value = "登录信息", dataType = "LoginForm", paramType = "body")
    @PostMapping("/login")
    public R login(@RequestBody LoginForm loginForm) {
        // 查询用户信息和密码验证
        SysUser user = sysUserService.selectUserByPhone(loginForm.getUsername());
        if (user == null) {
            user = sysUserService.selectUserByEmail(loginForm.getUsername());
            if (user == null) {
                return error("用户不存在！");
            }
        }
        if ("1".equals(user.getStatus())) {
            return error("您的账户已经禁用！");
        }
        if (!PasswordUtil.matches(user, loginForm.getPassword())) {
            return error("账号和密码不匹配！");
        }
        //记录登录信息
        user.setLogin_ip("");
        user.setLogin_date(new Date());
        sysUserService.updateUserLoginInfo(user);
        //颁发token
        return R.success(jwtTokenService.createToken(user.getUser_id(), user.getPhone(), user.getPassword()));
    }

    @UseJwtToken(required = false)
    @ApiOperation(value = "获取用户信息", notes = "根据token获取信息，不需要拦截，直接将token传输过来")
    @GetMapping("/get_user_info")
    public R getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("x-access-token");
        if (StringUtils.isEmpty(token)) {
            return error("令牌为空，无法获取用户信息！");
        }
        //获取用户信息和权限，路由等信息
        R result = new R();
        SysUser sysUser = sysUserService.selectUserById(jwtTokenService.getUserId(token));
        if (sysUser != null) {
            result.put("uid", sysUser.getUser_id());
            result.put("name", sysUser.getUser_name());
            result.put("roles", sysRoleService.selectRoleKeys(sysUser.getUser_id()));
            return R.success(result);
        } else {
            return error("获取用户信息失败，请重新登录！");
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @UseJwtToken
    @ApiOperation(value = "注销用户", notes = "根据用户id清空和删除对应的用户token")
    @ApiImplicitParam(name = "userId", value = "用户标识", dataType = "String", paramType = "path")
    @PostMapping("/logout/{userId}")
    public R logout(@PathVariable("userId") String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            jwtTokenService.expireToken(userId);
        }
        return R.success();
    }
}