package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.entitys.request.LoginForm;
import com.fanling.dreamland.utils.StringUtils;
import com.fanling.dreamland.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP系统登录")
@RestController
@RequestMapping("/user/login")
public class AppLoginController extends BaseController {

    @ApiOperation(value = "普通用户登录", notes = "普通用户填写手机号和密码进行登录")
    @ApiImplicitParam(name = "loginForm", value = "登录信息", dataType = "LoginForm", paramType = "body")
    @PostMapping("/common/login")
    public R commonLogin(@RequestBody LoginForm loginForm) {
        R error = check(loginForm);
        if (error != null) {
            return error;
        } else {
            return success();
        }
    }

    @ApiOperation(value = "专业用户登录", notes = "专业用户填写手机号和密码进行登录")
    @ApiImplicitParam(name = "loginForm", value = "登录信息", dataType = "LoginForm", paramType = "body")
    @PostMapping("/pro/login")
    public R proLogin(@RequestBody LoginForm loginForm) {
        R error = check(loginForm);
        if (error != null) {
            return error;
        } else {
            return success();
        }
    }

    /**
     * 检查登录信息
     *
     * @param loginForm
     * @return
     */
    private R check(LoginForm loginForm) {
        if (StringUtils.isEmpty(loginForm.getUsername())) {
            return error("手机号码必须填写！");
        }
        if (StringUtils.isEmpty(loginForm.getPassword())) {
            return error("密码为空，请填写！");
        }
        return null;
    }
}
