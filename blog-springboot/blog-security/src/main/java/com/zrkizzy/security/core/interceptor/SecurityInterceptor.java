package com.zrkizzy.security.core.interceptor;

import com.zrkizzy.security.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限系统拦截器
 *
 * @author zhangrongkang
 * @since 2023/7/12
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除拦截器中的资源，避免内存溢出
        SecurityContext.remove();
    }
}
