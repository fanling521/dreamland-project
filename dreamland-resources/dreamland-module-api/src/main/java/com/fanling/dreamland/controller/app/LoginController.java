package com.fanling.dreamland.controller.app;

import com.fanling.dreamland.R;
import com.fanling.dreamland.entity.request.LoginBody;
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
@RequestMapping("/app/login")
public class LoginController extends BaseController {

    @ApiOperation(value = "普通用户登录", notes = "普通用户填写手机号和密码进行登录")
    @ApiImplicitParam(name = "loginBody", value = "登录信息", dataType = "LoginBody", paramType = "body")
    @PostMapping("/login_by_captcha")
    public R login(@RequestBody LoginBody loginBody) {
        return success();
    }
}
