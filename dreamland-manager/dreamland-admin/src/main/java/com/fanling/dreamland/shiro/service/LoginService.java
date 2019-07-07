package com.fanling.dreamland.shiro.service;

import com.fanling.dreamland.common.util.DateUtils;
import com.fanling.dreamland.common.util.ShiroUtils;
import com.fanling.dreamland.domain.system.SysUser;
import com.fanling.dreamland.service.system.ISysUserService;
import com.fanling.dreamland.shiro.exception.UserBlockedException;
import com.fanling.dreamland.shiro.exception.UserDeleteException;
import com.fanling.dreamland.shiro.exception.UserNotExistsException;
import com.fanling.dreamland.shiro.exception.UserPasswordNotMatchException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
            logger.info("[登录]-" + loginName + "-用户不存在");
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            logger.info("[登录]-" + loginName + "-用户已经被删除");
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            logger.info("[登录]-" + loginName + "-用户已经被禁用");
            throw new UserBlockedException();
        }
        if (!matches(user, password)) {
            logger.info("[登录]-" + loginName + "-用户密码不匹配");
            throw new UserPasswordNotMatchException();
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

    public boolean matches(SysUser user, String newPassword) {
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
    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        sysUserService.updateLoginUser(user);
    }
}
