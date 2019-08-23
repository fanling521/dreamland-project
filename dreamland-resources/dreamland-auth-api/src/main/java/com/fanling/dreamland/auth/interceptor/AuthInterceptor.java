package com.fanling.dreamland.auth.interceptor;

import com.fanling.dreamland.auth.JwtTokenService;
import com.fanling.dreamland.common.annotations.UseJwtToken;
import com.fanling.dreamland.common.exception.AuthTokenException;
import com.fanling.dreamland.entity.AppUser;
import com.fanling.dreamland.service.IAppUserService;
import com.fanling.dreamland.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 验证拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("x-access-token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有IgnoreJwtToken注释，有则跳过认证
        if (method.isAnnotationPresent(UseJwtToken.class)) {
            UseJwtToken useJwtToken = method.getAnnotation(UseJwtToken.class);
            if (useJwtToken.required()) {
                // 执行认证
                if (StringUtils.isEmpty(token)) {
                    throw new AuthTokenException("Token值为空，无法处理请求！");
                }
                // 获取 token 中的 user id
                AppUser appUser = appUserService.selectById(jwtTokenService.getUserId(token));
                if (appUser == null) {
                    throw new AuthTokenException("用户不存在，请重新登录！");
                } else {
                    //在这里直接取redis中的数据
                    String redisToken = jwtTokenService.getToken(appUser.getId());
                    if (StringUtils.isEmpty(redisToken) || !redisToken.equals(token)) {
                        throw new AuthTokenException("Token验证失败，请重新登录！");
                    }
                    return true;
                }
            } else {
                return true;
            }
        } else {
            throw new AuthTokenException("资源访问受限，请咨询服务提供者！");
        }
    }
}