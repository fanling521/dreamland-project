package com.fanling.dreamland.auth.controller;

import com.fanling.common.R;
import com.fanling.common.utils.MyAssert;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.auth.entity.AppDeviceInfo;
import com.fanling.dreamland.auth.entity.AppIdCard;
import com.fanling.dreamland.auth.entity.AppUser;
import com.fanling.dreamland.auth.feign.UserSaleFeign;
import com.fanling.dreamland.auth.request.UpdateAppUser;
import com.fanling.dreamland.auth.service.IAppDeviceInfoService;
import com.fanling.dreamland.auth.service.IAppIdCardService;
import com.fanling.dreamland.auth.service.IAppUserService;
import com.fanling.dreamland.auth.service.auth.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "个人信息维护")
@RestController
@RequestMapping("/app/user")
public class AppUserInfoController extends BaseController {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private IAppDeviceInfoService appDeviceInfoService;

    @Autowired
    private IAppIdCardService appIdCardService;

    @Autowired
    private UserSaleFeign userSaleFeign;


    @ApiOperation(value = "获取个人信息以及订单信息", notes = "用户根据标识获取个人信息以及订单信息")
    @ApiImplicitParam(name = "uid", value = "用户标识", dataType = "String", paramType = "path")
    @PostMapping("/personal_center/{uid}")
    public R index(@PathVariable("uid") String uid) {
        MyAssert.notNull(uid, "用户标识不能为空！");
        AppUser appUser = appUserService.selectById(uid);
        if (appUser == null) {
            return error("用户不存在！");
        }
        R map = new R();
        map.put("id", appUser.getId());
        map.put("avatar", appUser.getAvatar());
        map.put("user_name", appUser.getUser_name());
        map.put("user_phone", appUser.getUser_phone());
        AppIdCard appIdCard = appIdCardService.selectById(appUser.getId());
        if (appIdCard != null) {
            map.put("real_status", appIdCard.getStatus());
            map.put("real_name", appIdCard.getReal_name());
        } else {
            map.put("real_status", "0");
            map.put("real_name", "");
        }
        R user_order = userSaleFeign.selectById(appUser.getId());
        if (Integer.parseInt(user_order.getOrDefault("code", "500").toString()) == 0) {
            map.put("user_sale", user_order.get("data"));
        } else {
            return error("用户的服务数据信息查询失败！");
        }
        return R.success(map);
    }

    @ApiOperation(value = "获取个人信息", notes = "用户根据标识获取个人信息")
    @ApiImplicitParam(name = "uid", value = "用户标识", dataType = "String", paramType = "path")
    @PostMapping("/personal_info/{uid}")
    public R info(@PathVariable("uid") String uid) {
        MyAssert.notNull(uid, "用户标识不能为空！");
        AppUser appUser = appUserService.selectById(uid);
        if (appUser == null) {
            return error("用户不存在！");
        }
        R map = new R();
        map.put("id", appUser.getId());
        map.put("gender", appUser.getGender());
        map.put("user_name", appUser.getUser_name());
        map.put("avatar", appUser.getAvatar());
        map.put("user_phone", appUser.getUser_phone());
        AppIdCard appIdCard = appIdCardService.selectById(appUser.getId());
        if (appIdCard != null) {
            map.put("real_name", appIdCard.getReal_name());
        } else {
            map.put("real_name", "");
        }
        return R.success(map);
    }


    @ApiOperation(value = "修改用户名", notes = "用户根据标识修改用户名")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @PostMapping("/modify/name")
    public R name(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getUser_name(), "用户名不能为空！");
        AppUser appUser = new AppUser();
        appUser.setId(updateAppUser.getUid());
        appUser.setUser_name(updateAppUser.getUser_name());
        return toAjax(appUserService.update(appUser));
    }

    @ApiOperation(value = "修改性别", notes = "用户根据标识修改性别")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @PostMapping("/modify/gender")
    public R gender(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getGender(), "用户性别不能为空！");
        AppUser appUser = new AppUser();
        appUser.setId(updateAppUser.getUid());
        appUser.setGender(updateAppUser.getGender());
        return toAjax(appUserService.update(appUser));
    }

    @ApiOperation(value = "修改手机号", notes = "用户根据标识修改手机号")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @PostMapping("/modify/phone")
    public R phone(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getOld_phone(), "用户原手机号不能为空！");
        MyAssert.notNull(updateAppUser.getNew_phone(), "用户新手机号不能为空！");
        MyAssert.notNull(updateAppUser.getPassword(), "验证码不能为空！");
        //验证用户和验证码
        AppUser appUser_old = appUserService.selectByLogin(updateAppUser.getOld_phone());
        if (appUser_old == null) {
            return error("用户不存在！");
        } else {
            if (captchaService.checkCaptcha(updateAppUser.getNew_phone() + "_R2", updateAppUser.getPassword())) {
                AppUser appUser = new AppUser();
                appUser.setId(updateAppUser.getUid());
                appUser.setUser_phone(updateAppUser.getNew_phone());
                saveDevice(appUser, updateAppUser);
                return toAjax(appUserService.update(appUser));
            } else {
                return error("验证码错误或者已经失效，请重新获取！");
            }
        }
    }

    @ApiOperation(value = "修改头像", notes = "用户根据标识修改头像")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @PostMapping("/modify/avatar")
    public R avatar(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getAvatar(), "头像地址不能为空！");
        AppUser appUser = new AppUser();
        appUser.setId(updateAppUser.getUid());
        appUser.setAvatar(updateAppUser.getAvatar());
        return toAjax(appUserService.update(appUser));
    }

    /**
     * 保存手机信息
     *
     * @param appUser       用户信息
     * @param updateAppUser 注册信息
     */
    private void saveDevice(AppUser appUser, UpdateAppUser updateAppUser) {
        AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
        appDeviceInfo.setUser_id(appUser.getId());
        appDeviceInfo.setUser_phone(appUser.getUser_phone());
        appDeviceInfo.setPhone_version(updateAppUser.getPhone_version());
        appDeviceInfo.setPhone_type(updateAppUser.getPhone_type());
        appDeviceInfoService.insert(appDeviceInfo);
    }
}
