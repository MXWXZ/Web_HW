package com.imwxz.store.util.jwt;

import com.imwxz.store.entity.UserEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(AdminToken.class)) {
            AdminToken adminToken = method.getAnnotation(AdminToken.class);
            if (adminToken.required()) {
                if (token == null) {
                    throw new RuntimeException("You need token to visit this page!");
                }
                UserEntity user = JWTUtil.parseJWT(token);
                if (user == null || user.getUserPermission() != 1)
                    throw new RuntimeException("You need admin permission!");
                return true;
            }
        }

        if (method.isAnnotationPresent(UserToken.class)) {
            UserToken userToken = method.getAnnotation(UserToken.class);
            if (userToken.required()) {
                if (token == null)
                    throw new RuntimeException("You need token to visit this page!");
                UserEntity user = JWTUtil.parseJWT(token);
                if (user == null)
                    throw new RuntimeException("You need user permission!");
                if (userToken.verifyId()) {
                    String userId = httpServletRequest.getParameter("userId");
                    if (userToken.jsonBody())
                        userId = httpServletRequest.getHeader("userId");
                    if (!userToken.adminFetch() && user.getUserPermission() == 1)
                        throw new RuntimeException("Need non-admin!");
                    if (userToken.adminFetch() && user.getUserPermission() == 1 && userId == null)
                        return true;
                    else if (userId == null)
                        throw new RuntimeException("Need userid!");
                    int id = Integer.valueOf(userId);
                    if (user.getUserId() != id)
                        throw new RuntimeException("Wrong userid!");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}

