package com.fanling.dreamland.controller.app;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.config.InitializingMap;
import com.fanling.dreamland.entity.AppUser;
import com.fanling.dreamland.entity.request.LoginBody;
import com.fanling.dreamland.service.IAppUserService;
import com.fanling.dreamland.service.auth.CaptchaService;
import com.fanling.dreamland.service.auth.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP系统登录服务")
@RestController
@RequestMapping("/app/login")
public class LoginController extends BaseController {
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @ApiOperation(value = "验证码登录", notes = "用户填写手机号和获取验证码进行登录")
    @ApiImplicitParam(name = "loginBody", value = "登录信息", dataType = "LoginBody", paramType = "body")
    @PostMapping("/login_by_captcha")
    public R login(@RequestBody LoginBody loginBody) {
        MyAssert.notNull(loginBody.getAccount(), "手机号码不能为空！");
        MyAssert.notNull(loginBody.getPassword(), "验证码不能为空！");
        //验证码
        if (!captchaService.checkCaptcha(loginBody.getAccount()+ "-login", loginBody.getPassword())) {
            return error("验证码验证失败，请重新获取!");
        }
        if (InitializingMap.checkRole(loginBody.getRole_key())) {
            return error("用户角色类型不存在！");
        }
        //验证用户并且颁发token
        AppUser appUser = appUserService.selectByLogin(loginBody.getAccount(), loginBody.getRole_key());
        if (appUser == null) {
            return error("用户信息不存在！");
        } else {
            return R.success(jwtTokenService.createToken(appUser.getId(), loginBody.getPassword()));
        }
    }
}
