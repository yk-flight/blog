package com.zrkizzy.security.core.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.TokenErrorException;
import com.zrkizzy.common.exception.TokenExpiredException;
import com.zrkizzy.common.utils.security.JwtTokenUtil;
import com.zrkizzy.security.entity.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.zrkizzy.common.constant.CommonConst.*;

/**
 * Jwt登录授权过滤器
 *
 * @author zhangrongkang
 * @since 2023/3/13
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    /**
     * Jwt处理Token工具类
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 用户信息加载业务
     */
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("----------------- 进入自定义Token授权过滤器 -----------------");
        // 从请求中获取Token
        String authHeader = request.getHeader(AUTHORIZATION);
        // 判断请求中是否存在Token
        if (null != authHeader && authHeader.startsWith(BEARER)) {
            // 如果存在token则将token令牌截取出来
            String token = authHeader.substring(BEARER.length()).trim();
            try {
                // 判断Token是否合法
                if (!jwtTokenUtil.validateToken(token)) {
                    // 解析出当前 token 中的用户并将用户信息设置到 SecurityContextHolder 中
//                    log.info("加载用户信息...... ");
                    // 解析出 token 中的用户名
                    String username = jwtTokenUtil.getUsernameFromToken(token);
                    // 如果当前Token不合法
//                    log.info("当前进行鉴权的用户为：{}", username);
                    MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(username);
//                    log.info("当前用户的角色标识为：{}", userDetails.getRoleMark());
                    // 用户详细信息（userDetails）、空密码、用户权限（userDetails.getAuthorities()）来获取认证令牌
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将认证令牌设置为当前线程的安全上下文中的认证对象
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    render(response, HttpStatusEnum.INTERNAL_SERVER_ERROR);
                    return;
                }
            } catch (Exception e) {
                // 根据异常类型的不同来定义不同的响应结果
                if (e instanceof TokenExpiredException) {
                    render(response, HttpStatusEnum.TOKEN_EXPIRED);
                } else if (e instanceof TokenErrorException) {
                    render(response, HttpStatusEnum.TOKEN_ERROR);
                } else if (e instanceof UsernameNotFoundException) {
                    render(response, HttpStatusEnum.USER_NOT_FOUND);
                } else {
                    render(response, HttpStatusEnum.INTERNAL_SERVER_ERROR);
                }
                // 最终返回到前端
                return;
            }

        }
//        log.info("----------------- 退出自定义Token授权过滤器 -----------------");
        // 放行
        filterChain.doFilter(request, response);
    }

    /**
     * 定义返回对象体
     *
     * @param response 前端响应
     * @param httpStatusEnum  请求状态枚举
     * @throws IOException IO异常
     */
    private void render(HttpServletResponse response, HttpStatusEnum httpStatusEnum) throws IOException {
        // 定义响应编码格式
        response.setCharacterEncoding(UTF_8);
        // 定义返回对象格式
        response.setContentType(APPLICATION_JSON);
        // 定义返回结果
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(Result.failure(httpStatusEnum)));
        out.flush();
        out.close();
    }

}
