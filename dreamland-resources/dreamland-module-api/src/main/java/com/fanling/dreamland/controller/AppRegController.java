package com.fanling.dreamland.controller;

import com.fanling.dreamland.config.CaptchaService;
import com.fanling.dreamland.R;
import com.fanling.dreamland.entitys.moudle.member.AppCommonUser;
import com.fanling.dreamland.entitys.moudle.member.AppProUser;
import com.fanling.dreamland.entitys.request.RegForm;
import com.fanling.dreamland.utils.StringUtils;
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
@RequestMapping("/system/reg")
public class AppRegController extends BaseController {

    @Autowired
    private CaptchaService captchaService;

    @ApiOperation(value = "获取验证码", notes = "用户根据手机号码获取验证码，过期时间为1分钟")
    @ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", paramType = "query")
    @PostMapping("/captcha")
    public R getCaptcha(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return error("手机号码不能为空!");
        }
        //校验手机号获取间隔是否超过了1分钟
        if (captchaService.getCaptcha(phone)) {
            return error("1分钟内不可重复提交获取验证码的请求");
        }
        //TODO 调用阿里短信接口
        //将验证码存redis
        String captcha = captchaService.randomCaptcha();
        captchaService.setCaptcha(phone, captcha);
        return success("验证码已经发送，注意查收！");
    }

    @ApiOperation(value = "普通用户注册", notes = "普通用户注册成为系统的普通会员，后续信息将自行填写")
    @ApiImplicitParam(name = "regForm", value = "注册信息", dataType = "RegForm", paramType = "body")
    @PostMapping("/common/reg")
    public R commonReg(RegForm regForm) {
        //检查手机号是否被注册
        //检查验证码-redis
        if (!captchaService.checkCaptcha(regForm.getPhone(), regForm.getCaptcha())) {
            return error("验证码验证失败，请重新获取!");
        }
        AppCommonUser appCommonUser = new AppCommonUser();
        appCommonUser.setPhone(regForm.getPhone());
        appCommonUser.setReg_ip(regForm.getReg_ip());
        return success();
    }

    @ApiOperation(value = "专业用户注册", notes = "专业用户注册成为系统的维修人员，后续信息将自行填写，将进行实名验证")
    @ApiImplicitParam(name = "regForm", value = "注册信息", dataType = "RegForm", paramType = "body")
    @PostMapping("/pro/reg")
    public R proReg(RegForm regForm) {
        //检查手机号是否被注册
        //检查验证码-redis
        if (!captchaService.checkCaptcha(regForm.getPhone(), regForm.getCaptcha())) {
            return error("验证码验证失败，请重新获取!");
        }
        AppProUser appProUser = new AppProUser();
        appProUser.setReg_ip(regForm.getReg_ip());
        appProUser.setPhone(regForm.getPhone());
        return success();
    }


}
