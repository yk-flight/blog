package com.zrkizzy.security.core.filters;

import com.zrkizzy.common.enums.HttpStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 资源角色权限过滤器
 *
 * <p>如果 decide 方法正常返回（即没有抛出异常），那么就表示访问控制通过，可以允许用户访问对应的资源</p>
 * <p>如果 decide 方法抛出了 AccessDeniedException 异常，那么就表示访问控制拒绝，不允许用户访问对应的资源</p>
 *
 * @author zhangrongkang
 * @since  2023/3/9
 */
@Component
@Slf4j
public class AccessDecisionManagerFilter implements AccessDecisionManager {

    /**
     * 根据用户的角色信息来判断是否具有访问该资源的权限
     *
     * @param authentication 当前经过身份验证的用户信息
     * @param object 正在访问的资源对象
     * @param configAttributes 访问该资源所需的权限信息
     * @throws AccessDeniedException 拒绝访问异常
     * @throws InsufficientAuthenticationException 身份授权不足异常
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("----------------- 进入资源角色权限过滤器 -----------------");
        // 如果当前请求的资源不需要访问的角色则直接放行
        if (configAttributes == null) {
            return;
        }
        // 如果当前请求需要对应的角色则需要访问的角色集合进行遍历
        for (ConfigAttribute attribute : configAttributes) {
            // 获取到需要的角色
            String needRole = attribute.getAttribute();
            log.info("当前资源需要的角色为： {}", needRole);
            // 遍历当前用户所具有的角色
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                log.info("当前用户的角色为：{}", grantedAuthority.getAuthority());
                // 如果当前用户所具有的角色和需要的角色一致则直接放行
                if (needRole.equals(grantedAuthority.getAuthority())) {
                    log.info("完成资源的角色过滤 =====> 当前用户可以访问对应资源");
                    log.info("----------------- 退出请求资源角色过滤器 -----------------");
                    return;
                }
            }
        }
        log.info("完成资源的角色过滤 =====> 当前用户不具备访问资源的权限");
        log.info("----------------- 退出请求资源角色过滤器 -----------------");
        // 如果用户不具备访问该资源具有的角色则抛出拒绝访问异常
        throw new AccessDeniedException(HttpStatusEnum.FORBIDDEN.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
