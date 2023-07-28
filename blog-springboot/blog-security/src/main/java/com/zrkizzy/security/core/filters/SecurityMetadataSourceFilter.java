package com.zrkizzy.security.core.filters;

import com.zrkizzy.common.context.SystemContext;
import com.zrkizzy.data.dto.ResourceRoleDTO;
import com.zrkizzy.security.service.DynamicSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 请求资源权限过滤器
 *
 * <p>主要用于确定在Web应用程序中请求的资源需要哪些权限</p>
 * <p>使用自定义 DynamicSecurityService 业务来实现动态安全策略</p>
 *
 * @author zhangrongkang
 * @since 2023/3/13
 */
@Slf4j
@Component
public class SecurityMetadataSourceFilter implements FilterInvocationSecurityMetadataSource {
    /**
     * 角色资源列表
     */
    private static List<ResourceRoleDTO> resourceRoleList;

    /**
     * 自定义动态权限
     */
    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    /**
     * 加载角色资源信息
     * <p>在项目启动时执行该方法，并且将数据保存到内存中</p>
     */
    @PostConstruct
    private void loadResourceData() {
        resourceRoleList = dynamicSecurityService.loadResourceRoleData();
    }

    /**
     * 路由匹配工具类
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        log.info("----------------- 进入请求资源权限过滤器 -----------------");
        FilterInvocation filter = (FilterInvocation) object;
        // 获取请求的方法
        String method = filter.getRequest().getMethod();
        // 获取请求的URL
        String requestUrl = filter.getRequestUrl();
//        log.info("当前的请求方式为：{}，请求的路径为：{}", method, requestUrl);

//        log.info("开始匹配权限......");
        // 匹配需要进行权限校验的请求路径
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleList) {
//            log.info("校验的请求方式为：{}，校验请求的路径为：{}", resourceRoleDTO.getMethod(), resourceRoleDTO.getUrl());
            // 如果请求路径与请求方法一致则进行权限添加操作
            if (antPathMatcher.match(resourceRoleDTO.getUrl(), requestUrl) && resourceRoleDTO.getMethod().equals(method)) {
                // 将当前请求的模块ID添加到全局线程变量中
                SystemContext.setModuleId(resourceRoleDTO.getModuleId());
                SystemContext.setOperateContent(resourceRoleDTO.getDescription());
//                log.info("开始对当前请求：{} 进行授权", resourceRoleDTO.getUrl());
//                log.info("授予的角色集合为： {}", Arrays.toString(resourceRoleDTO.getRoles().toArray()));
                return SecurityConfig.createList(resourceRoleDTO.getRoles().toArray(new String[]{}));
            }
        }

//        log.info("----------------- 退出请求资源权限过滤器 -----------------");
        // 没有匹配到的url默认登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
