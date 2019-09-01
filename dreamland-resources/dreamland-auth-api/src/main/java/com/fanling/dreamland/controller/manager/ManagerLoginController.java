package com.fanling.dreamland.controller.manager;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.config.DefaultEnum;
import com.fanling.dreamland.entity.request.LoginBody;
import com.fanling.dreamland.service.auth.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理端系统登录服务")
@RestController
@RequestMapping("/manager/login")
public class ManagerLoginController extends BaseController {
    @Autowired
    private JwtTokenService jwtTokenService;

    @ApiOperation(value = "唯一用户登录", notes = "唯一用户登录")
    @ApiImplicitParam(name = "loginBody", value = "登录信息", dataType = "LoginBody", paramType = "body")
    @PostMapping
    public R login(@RequestBody LoginBody loginBody) {
        MyAssert.notNull(loginBody.getAccount(), "登录账号不能为空！");
        MyAssert.notNull(loginBody.getPassword(), "密码不能为空！");
        //验证用户并且颁发token
        if (!"admin".equals(loginBody.getAccount())) {
            return error("登录失败，用户不存在！");
        }
        if (!DefaultEnum.DEFAULT_PASSWORD.getCode().equals(loginBody.getPassword())) {
            return error("登录失败，密码错误！");
        } else {
            return R.success(jwtTokenService.createToken(DefaultEnum.DEFAULT_PWD_MANAGER.getCode(), loginBody.getPassword()));
        }
    }

    @ApiOperation(value = "唯一用户登录", notes = "唯一用户登录")
    @ApiImplicitParam(name = "loginBody", value = "登录信息", dataType = "LoginBody", paramType = "body")
    @PostMapping("logout")
    public R logout() {
        return success();
    }
}
