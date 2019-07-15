package com.fanling.dreamland.shiro.realm;

import com.fanling.dreamland.domain.SysUser;
import com.fanling.dreamland.shiro.ShiroUtils;
import com.fanling.dreamland.shiro.jwt.JwtToken;
import com.fanling.dreamland.shiro.service.ISysMenuService;
import com.fanling.dreamland.shiro.service.ISysRoleService;
import com.fanling.dreamland.shiro.service.ISysUserService;
import com.fanling.dreamland.shiro.service.TokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm 处理登录 权限
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private TokenService tokenService;

    /**
     * 必须重写此方法，不然Shiro会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SysUser user = ShiroUtils.getSysUser();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roleService.selectRoleKeys(user.getUserId()));
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(menuService.selectPermsByUserId(user.getUserId()));
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String xToken = (String) token.getCredentials();
        SysUser user = sysUserService.selectUserById(tokenService.getUserId(xToken));
        if (user != null) {
            if ("1".equals(user.getStatus())) {
                throw new LockedAccountException("您的账户已经被禁用，请联系管理员！");
            } else {
                return new SimpleAuthenticationInfo(user, xToken, getName());
            }
        } else {
            throw new UnknownAccountException("您的账户不存在，请检查！");
        }
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
