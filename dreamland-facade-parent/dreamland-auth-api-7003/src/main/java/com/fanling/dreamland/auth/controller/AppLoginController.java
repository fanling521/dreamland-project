package com.fanling.dreamland.auth.controller;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.auth.entity.AppUser;
import com.fanling.dreamland.auth.request.LoginBody;
import com.fanling.dreamland.auth.service.IAppUserService;
import com.fanling.dreamland.auth.service.auth.CaptchaService;
import com.fanling.dreamland.auth.service.auth.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "APP系统登录服务")
@RestController
@RequestMapping("/app/user/login")
public class AppLoginController extends BaseController {
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @ApiOperation(value = "会员验证码登录", notes = "用户填写手机号和获取验证码进行登录")
    @ApiImplicitParam(name = "loginBody", value = "登录信息", dataType = "LoginBody", paramType = "body")
    @PostMapping("/phone")
    public R login(@RequestBody LoginBody loginBody) {
        MyAssert.notNull(loginBody.getAccount(), "手机号码不能为空！");
        MyAssert.notNull(loginBody.getPassword(), "验证码不能为空！");
        //验证码
        if (!captchaService.checkCaptcha(loginBody.getAccount() + "_L1", loginBody.getPassword())) {
            return error("验证码验证失败，请重新获取!");
        }
        //验证用户并且颁发token
        AppUser appUser = appUserService.selectByLogin(loginBody.getAccount());
        if (appUser == null) {
            return error("用户信息不存在！");
        } else {
            appUser.setLast_login_date(Long.toString(new Date().getTime()));
            appUser.setLast_login_ip("");
            return R.success(jwtTokenService.createToken(appUser.getId(), appUser.getPassword()));
        }
    }

    @ApiOperation(value = "会员注销登录", notes = "会员注销登录")
    @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "header")
    @PostMapping("/logout")
    public R logout(@RequestHeader("x-access-token") String token) {
        String uid = jwtTokenService.getUserId(token);
        MyAssert.notNull(uid, "用户标识查询失败，请重新登录！");
        jwtTokenService.expireToken(uid);
        return success();
    }
}
