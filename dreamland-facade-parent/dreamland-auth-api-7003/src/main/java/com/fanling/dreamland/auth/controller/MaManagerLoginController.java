package com.fanling.dreamland.auth.controller;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.auth.config.PasswordUtil;
import com.fanling.dreamland.auth.entity.AppManager;
import com.fanling.dreamland.auth.request.ManagerLogin;
import com.fanling.dreamland.auth.service.IAppManagerService;
import com.fanling.dreamland.auth.service.auth.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "管理端系统登录服务")
@RestController
@RequestMapping("/manage/login")
public class MaManagerLoginController extends BaseController {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private IAppManagerService appManagerService;

    @ApiOperation(value = "唯一用户登录", notes = "唯一用户登录")
    @ApiImplicitParam(name = "managerLogin", value = "登录信息", dataType = "ManagerLogin", paramType = "body")
    @PostMapping
    public R login(@RequestBody ManagerLogin managerLogin) {
        MyAssert.notNull(managerLogin.getAccount(), "登录账号不能为空！");
        MyAssert.notNull(managerLogin.getPassword(), "密码不能为空！");
        AppManager appManager = appManagerService.selectByLogin(managerLogin.getAccount()
                , PasswordUtil.encryptPassword(managerLogin.getAccount(), managerLogin.getPassword(), "F1N2G3L4I5N6G"));
        //验证用户并且颁发token
        if (appManager != null) {
            return R.success(jwtTokenService.createToken(appManager.getId(), appManager.getPassword()));
        } else {
            return error("登录失败,请检查账号和密码！");
        }
    }

    @ApiOperation(value = "用户下线", notes = "用户下线")
    @ApiImplicitParam(name = "uid", value = "用户标识", dataType = "String", paramType = "path")
    @PostMapping("/logout/{uid}")
    public R logout(@PathVariable("uid") String uid) {
        if (jwtTokenService.deleteToken(uid)) {
            return success();
        } else {
            return error();
        }
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParam(name = "token", value = "token信息", dataType = "String", paramType = "query")
    @PostMapping("/get")
    public R get(@RequestParam("access_token") String token) {
        String uid = jwtTokenService.getUserId(token);
        if (StringUtils.isEmpty(uid)) {
            return error();
        }
        R data = new R();
        AppManager appManager = appManagerService.selectById(uid);
        if (appManager == null) {
            return error("用户不存在！");
        }
        data.put("id", uid);
        data.put("name", appManager.getUser_name());
        return R.success(data);
    }
}
