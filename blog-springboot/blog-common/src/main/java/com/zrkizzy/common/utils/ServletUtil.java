package com.zrkizzy.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求响应工具类
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Component
public class ServletUtil {

    /**
     * 获取当前线程请求
     *
     * @return request请求
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取当前线程响应
     *
     * @return request请求
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取当前线程中的Servlet相关属性
     *
     * @return Servlet相关属性
     */
    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
