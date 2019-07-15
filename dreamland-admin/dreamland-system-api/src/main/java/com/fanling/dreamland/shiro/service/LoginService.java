package com.fanling.dreamland.shiro.service;

import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.shiro.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 登录校验方法
 */
@Component
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     */
    public SysUser login(String loginName, String password) {
        // 查询用户信息
        SysUser user = sysUserService.selectUserByLoginName(loginName);
        if (user == null) {
            throw new UnknownAccountException("输入的账户不存在");
        }
        if ("1".equals(user.getStatus())) {
            throw new DisabledAccountException("您的账户已经被禁用");
        }
        if (!matches(user, password)) {
            throw new AuthenticationException("登录名和密码不匹配");
        }
        logger.info("[登录]-" + loginName + "-用户已经验证通过");
        recordLoginInfo(user);
        return user;
    }

    /**
     * 验证密码
     *
     * @param user
     * @param newPassword
     * @return
     */

    private boolean matches(SysUser user, String newPassword) {
        String pwd = encryptPassword(user.getLoginName(), newPassword, user.getSalt());
        return user.getPassword().equals(pwd);
    }

    /**
     * 计算加密的密码
     *
     * @param username
     * @param password
     * @param salt
     * @return
     */
    private String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }

    /**
     * 记录登录信息
     */
    private void recordLoginInfo(SysUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(new Date());
        sysUserService.updateLoginUser(user);
    }
}
