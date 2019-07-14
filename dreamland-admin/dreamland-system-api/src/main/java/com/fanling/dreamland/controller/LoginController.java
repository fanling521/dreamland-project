package com.fanling.dreamland.controller;

import com.fanling.dreamland.R;
import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.service.AccessTokenService;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    protected AccessTokenService accessTokenService;

    /**
     * 登录请求
     *
     * @return
     */
    @PostMapping("/login")
    public R login(String loginName, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("ad24bd87-1046-405f-a94c-3872b363cc89");
        sysUser.setLoginName(loginName);
        sysUser.setPassword(password);
        return R.success(accessTokenService.createToken(sysUser.getUserId(),
                sysUser.getLoginName(), sysUser.getPassword()));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/get_user_info")
    public R getUserInfo() {
        return null;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public R logout(@RequestParam("userId") String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            accessTokenService.expireToken(userId);
        }
        return R.success();
    }
}
