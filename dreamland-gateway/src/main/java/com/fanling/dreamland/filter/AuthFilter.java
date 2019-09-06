package com.fanling.dreamland.filter;

import com.alibaba.fastjson.JSONArray;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class AuthFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> ops;

    //排除过滤的 uri 地址
    private static final String LOGIN_URI = "/auth-api/app/reg/phone";
    private static final String REGISTER_URI = "/auth-api/app/user/login/phone";
    private static final String CAPTCHA_URI = "/auth-api/app/captcha/";
    private static final String MANAGER_LOGIN_URI = "/auth-api/manager/login";
    //无权限时的提示语
    private static final String INVALID_TOKEN = "INVALID TOKEN";
    private static final String INVALID_USER_ID = "INVALID USER_ID";
    //ACCESS_ID
    private final static String ACCESS_ID = "app_id_";

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("uri:{}", request.getRequestURI());
        //注册和登录接口不拦截，其他接口都要拦截校验 token
        return !LOGIN_URI.equals(request.getRequestURI()) &&
                !REGISTER_URI.equals(request.getRequestURI()) &&
                !MANAGER_LOGIN_URI.equals(request.getRequestURI()) &&
                !request.getRequestURI().contains(CAPTCHA_URI);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //从 header 中读取
        String headerToken = request.getHeader("x-access-token");
        logger.info("headerToken={}", headerToken);
        if (StringUtils.isEmpty(headerToken)) {
            setUnauthorizedResponse(requestContext, INVALID_TOKEN);
        } else {
            verifyToken(requestContext, request, headerToken);
        }
        return null;
    }

    /**
     * 从Redis中校验token
     */
    private void verifyToken(RequestContext requestContext, HttpServletRequest request, String token) {
        //从header中取userId
        String userId = request.getHeader("x-user-id");
        logger.info("userId={}", userId);
        if (StringUtils.isEmpty(userId)) {
            setUnauthorizedResponse(requestContext, INVALID_USER_ID);
        } else {
            String redisToken = ops.get(ACCESS_ID + userId);
            if (StringUtils.isEmpty(redisToken) || !redisToken.equals(token)) {
                setUnauthorizedResponse(requestContext, INVALID_TOKEN);
            }
        }
    }

    /**
     * 设置 401 无权限状态
     */
    private void setUnauthorizedResponse(RequestContext requestContext, String msg) {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("msg", msg);
        requestContext.setResponseBody(JSONArray.toJSONString(result));
    }
}
