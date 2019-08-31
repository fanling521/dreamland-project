package com.fanling.dreamland.controller.app;

import com.fanling.common.R;
import com.fanling.common.web.BaseController;
import com.fanling.dreamland.auth.annotations.UseJwtToken;
import com.fanling.dreamland.auth.util.MyAssert;
import com.fanling.dreamland.auth.service.CaptchaService;
import com.fanling.dreamland.entity.AppDeviceInfo;
import com.fanling.dreamland.entity.AppIdCard;
import com.fanling.dreamland.entity.AppUser;
import com.fanling.dreamland.entity.UpdateAppUser;
import com.fanling.dreamland.service.IAppDeviceInfoService;
import com.fanling.dreamland.service.IAppIdCardService;
import com.fanling.dreamland.service.IAppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "个人信息维护")
@RestController
@RequestMapping("/app/user")
public class UserInfoController extends BaseController {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private IAppDeviceInfoService appDeviceInfoService;

    @Autowired
    private IAppIdCardService appIdCardService;


    @ApiOperation(value = "获取个人信息以及订单信息", notes = "用户根据标识获取个人信息以及订单信息")
    @ApiImplicitParam(name = "uid", value = "用户标识", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/index/{uid}")
    public R index(@PathVariable("uid") String uid) {
        MyAssert.notNull(uid, "用户标识不能为空！");
        AppUser appUser = appUserService.selectById(uid);
        if (appUser == null) {
            return error("用户不存在！");
        }
        R map = new R();
        map.put("id", appUser.getId());
        map.put("user_name", appUser.getUser_name());
        map.put("user_phone", appUser.getUser_phone());
        AppIdCard appIdCard = appIdCardService.selectById(appUser.getId());
        if (appIdCard != null) {
            map.put("real_status", appIdCard.getStatus());
        } else {
            map.put("real_status", "0");
        }
        map.put("order", "");
        return R.success(map);
    }

    @ApiOperation(value = "获取个人信息", notes = "用户根据标识获取个人信息")
    @ApiImplicitParam(name = "uid", value = "用户标识", dataType = "String", paramType = "path")
    @UseJwtToken
    @PostMapping("/info/{uid}")
    public R info(@PathVariable("uid") String uid) {
        MyAssert.notNull(uid, "用户标识不能为空！");
        AppUser appUser = appUserService.selectById(uid);
        if (appUser == null) {
            return error("用户不存在！");
        }
        R map = new R();
        map.put("id", appUser.getId());
        map.put("user_name", appUser.getUser_name());
        map.put("user_phone", appUser.getUser_phone());
        map.put("sex", appUser.getSex());
        return R.success(map);
    }


    @ApiOperation(value = "修改用户名", notes = "用户根据标识修改用户名")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @UseJwtToken
    @PostMapping("/name")
    public R name(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getUser_name(), "用户名不能为空！");
        AppUser appUser = new AppUser();
        appUser.setId(updateAppUser.getUid());
        appUser.setUser_name(updateAppUser.getUser_name());
        return R.success(appUserService.update(appUser));
    }

    @ApiOperation(value = "修改性别", notes = "用户根据标识修改性别")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @UseJwtToken
    @PostMapping("/gender")
    public R gender(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getSex(), "用户性别不能为空！");
        AppUser appUser = new AppUser();
        appUser.setId(updateAppUser.getUid());
        appUser.setSex(updateAppUser.getSex());
        return R.success(appUserService.update(appUser));
    }

    @ApiOperation(value = "修改手机号", notes = "用户根据标识修改手机号")
    @ApiImplicitParam(name = "updateAppUser", value = "用户维护信息", dataType = "UpdateAppUser", paramType = "body")
    @UseJwtToken
    @PostMapping("/phone")
    public R phone(@RequestBody UpdateAppUser updateAppUser) {
        MyAssert.notNull(updateAppUser.getUid(), "用户标识不能为空！");
        MyAssert.notNull(updateAppUser.getOld_phone(), "用户原手机号不能为空！");
        MyAssert.notNull(updateAppUser.getNew_phone(), "用户新手机号不能为空！");
        MyAssert.notNull(updateAppUser.getPassword(), "验证码不能为空！");
        MyAssert.notNull(updateAppUser.getRole_key(), "用户角色不能为空！");
        //验证用户和验证码
        AppUser appUser_old = appUserService.selectByLogin(updateAppUser.getOld_phone(), updateAppUser.getRole_key());
        if (appUser_old == null) {
            return error("用户不存在！");
        } else {
            if (captchaService.checkCaptcha(updateAppUser.getNew_phone() + "rebind", updateAppUser.getPassword())) {
                AppUser appUser = new AppUser();
                appUser.setId(updateAppUser.getUid());
                appUser.setUser_phone(updateAppUser.getNew_phone());
                saveDevice(appUser, updateAppUser);
                return R.success(appUserService.update(appUser));
            } else {
                return error("验证码错误或者已经失效，请重新获取！");
            }
        }
    }

    /**
     * 保存手机信息
     *
     * @param appUser       用户信息
     * @param updateAppUser 注册信息
     */
    private void saveDevice(AppUser appUser, UpdateAppUser updateAppUser) {
        AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
        appDeviceInfo.setIMEI(updateAppUser.getIMEI());
        appDeviceInfo.setUser_id(appUser.getId());
        appDeviceInfo.setUser_phone(appUser.getUser_phone());
        appDeviceInfo.setPhone_os(updateAppUser.getPhone_os());
        appDeviceInfoService.insert(appDeviceInfo);
    }
}
