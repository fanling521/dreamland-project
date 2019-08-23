package com.fanling.dreamland.controller.app;

import com.fanling.dreamland.R;
import com.fanling.dreamland.config.CaptchaService;
import com.fanling.dreamland.utils.RegexUtils;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "验证码服务")
@RestController
@RequestMapping("/app/captcha")
public class CaptchaController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    @Autowired
    private CaptchaService captchaService;

    @ApiOperation(value = "获取验证码", notes = "用户根据手机号码获取验证码，过期时间为1分钟")
    @ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", paramType = "path")
    @PostMapping("/{phone}")
    public R getCaptcha(@PathVariable("phone") String phone) {
        if (StringUtils.isEmpty(phone)) {
            return error("手机号码不能为空!");
        }
        if (!RegexUtils.isPhone(phone)) {
            return error("非法手机号！");
        }
        //校验手机号获取间隔是否超过了1分钟
        if (captchaService.getCaptcha(phone)) {
            return error("1分钟内不可重复提交获取验证码的请求");
        }
        //将验证码存redis
        String captcha = captchaService.randomCaptcha();
        logger.info("---> 验证码：{}", captcha);
        captchaService.setCaptcha(phone, captcha);
        //TODO 调用阿里短信接口

        return success();
    }
}