package com.zrkizzy.security.config;

import com.zrkizzy.security.core.filters.AccessDecisionManagerFilter;
import com.zrkizzy.security.core.filters.JwtAuthenticationTokenFilter;
import com.zrkizzy.security.core.filters.SecurityMetadataSourceFilter;
import com.zrkizzy.security.core.response.MyAccessDeniedHandler;
import com.zrkizzy.security.core.response.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 未登录或token失效返回结果
     */
    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 当访问接口没有权限时返回结果
     */
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    /**
     * 资源角色权限过滤器
     */
    @Autowired
    private AccessDecisionManagerFilter accessDecisionManager;
    /**
     * 请求资源权限过滤器
     */
    @Autowired
    private SecurityMetadataSourceFilter securityMetadataSource;
    /**
     * JWT登录授权过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                // 放行登录和退出
                "/login/**",
                "/logout",
                // 放行验证码
                "/captcha/**",
                // 放行静态资源文件
                "/css/**",
                "/js/**",
                // 放行网站图标
                "/favicon.ico",
                // 放行swagger相关文件
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                // 放行测试请求
                "/test/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 基于token，不需要使用csrf
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 自定义动态权限
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        // 判断当前请求资源需要哪些角色
                        object.setSecurityMetadataSource(securityMetadataSource);
                        // 判断当前请求用户是否具备资源需要的角色
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                // 所有请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 禁用缓存
                .headers().cacheControl();

        // 添加Jwt登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置自定义返回结果
        http.exceptionHandling()
                // 访问无权限
                .accessDeniedHandler(accessDeniedHandler)
                // 未授权
                .authenticationEntryPoint(authenticationEntryPoint);
    }

}
