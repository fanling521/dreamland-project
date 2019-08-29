package com.fanling.dreamland.controller.app;

import com.fanling.common.R;
import com.fanling.common.utils.StringUtils;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.auth.DefaultEnum;
import com.fanling.dreamland.auth.util.MyAssert;
import com.fanling.dreamland.auth.util.PasswordUtil;
import com.fanling.dreamland.config.CaptchaService;
import com.fanling.dreamland.entity.AppDeviceInfo;
import com.fanling.dreamland.entity.AppRole;
import com.fanling.dreamland.entity.AppUser;
import com.fanling.dreamland.entity.request.RegBody;
import com.fanling.dreamland.service.IAppDeviceInfoService;
import com.fanling.dreamland.service.IAppRoleService;
import com.fanling.dreamland.service.IAppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(tags = "APP系统注册服务")
@RestController
@RequestMapping("/app/reg")
public class RegController extends BaseController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private IAppRoleService appRoleService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IAppDeviceInfoService appDeviceInfoService;

    @ApiOperation(value = "用户注册", notes = "用户注册填写手机号、用户类型和验证码提交注册，后续信息将自行填写")
    @ApiImplicitParam(name = "regBody", value = "注册信息", dataType = "RegBody", paramType = "body")
    @PostMapping("/reg_by_captcha")
    public R commonReg(@RequestBody RegBody regBody) {
        //校验信息
        MyAssert.notNull(regBody.getAccount(), "手机号码不能为空！");
        MyAssert.notNull(regBody.getPassword(), "验证码不能为空！");
        MyAssert.notNull(regBody.getRole_key(), "请选择正确的用户类型！");
        //验证码
        if (!captchaService.checkCaptcha(regBody.getAccount(), regBody.getPassword())) {
            return error("验证码已经失效，请重新获取!");
        }
        AppRole appRole = appRoleService.selectByRoleName(regBody.getRole_key());
        if (appRole == null) {
            return error("用户类型查询错误，不存在此类型的用户");
        }
        //新增用户信息
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setPassword(PasswordUtil.defaultPassword(regBody.getAccount()));
        appUser.setSex(DefaultEnum.DEFAULT_SEX.getCode());
        appUser.setSlat(DefaultEnum.DEFAULT_SALT.getCode());
        appUser.setUser_name(regBody.getAccount());
        appUser.setUser_phone(regBody.getAccount());
        appUser.setRole_key(appRole.getId());

        saveDevice(appUser, regBody);

        return toAjax(appUserService.insert(appUser));
    }

    /**
     * 保存手机信息
     *
     * @param appUser 用户信息
     * @param regBody 注册信息
     */
    private void saveDevice(AppUser appUser, RegBody regBody) {
        AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
        appDeviceInfo.setIMEI(regBody.getIMEI());
        appDeviceInfo.setUser_id(appUser.getId());
        appDeviceInfo.setUser_phone(appUser.getUser_phone());
        appDeviceInfo.setPhone_os(regBody.getPhone_os());
        appDeviceInfoService.insert(appDeviceInfo);
    }

}
