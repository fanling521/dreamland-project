package com.fanling.dreamland.controller.app;

import com.fanling.dreamland.R;
import com.fanling.dreamland.config.CaptchaService;
import com.fanling.dreamland.entity.request.RegBody;
import com.fanling.dreamland.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP系统注册相关")
@RestController
@RequestMapping("/app/reg")
public class RegController extends BaseController {

    @Autowired
    private CaptchaService captchaService;

    @ApiOperation(value = "用户注册", notes = "普通用户注册成为系统的普通会员，后续信息将自行填写")
    @ApiImplicitParam(name = "regBody", value = "注册信息", dataType = "RegBody", paramType = "body")
    @PostMapping("/reg_by_captcha")
    public R commonReg(RegBody regBody) {
        if (!captchaService.checkCaptcha(regBody.getAccount(), regBody.getPassword())) {
            return error("验证码验证失败，请重新获取!");
        }
        return success();
    }


}
