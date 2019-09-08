package com.fanling.dreamland.controller.app;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.config.InitializingMap;
import com.fanling.dreamland.service.auth.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "验证码服务")
@RestController
@Slf4j
@RequestMapping("/app/captcha")
public class CaptchaController extends BaseController {
    @Autowired
    private CaptchaService captchaService;

    @ApiOperation(value = "获取验证码", notes = "用户根据手机号码获取验证码，过期时间为1分钟")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", required = true, value = "手机号码", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "type", required = true, value = "验证码类型", dataType = "String", paramType = "path")
    })
    @PostMapping("/{type}/{phone}")
    public R getCaptcha(@PathVariable("phone") String phone, @PathVariable("type") String type) {
        MyAssert.notNull(phone, "手机号码不能为空!");
        MyAssert.notNull(type, "验证码类型不能为空!");
        if (InitializingMap.checkCaptcha(type)) {
            return error("验证码类型填写错误！");
        }
        if (!isPhone(phone)) {
            return error("非法手机号！");
        }
        //校验手机号获取间隔是否超过了120秒
        if (captchaService.getCaptcha(phone + "_" + type)) {
            return error("120秒内不可重复提交获取验证码的请求");
        }
        if (InitializingMap.checkCaptcha(type)) {
            return error("验证码类型不准确");
        }
        //将验证码存redis
        String captcha = captchaService.randomCaptcha();
        log.info("---> 验证码：{}", captcha);
        captchaService.setCaptcha(phone + "_" + type, captcha);
        //TODO 调用阿里短信接口
        return success(captcha);
    }

    /**
     * 手机正则测试
     *
     * @param phone
     * @return
     */
    private boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
