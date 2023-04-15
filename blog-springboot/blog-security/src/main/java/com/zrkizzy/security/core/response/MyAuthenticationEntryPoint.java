package com.zrkizzy.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.zrkizzy.common.constant.CommonConst.APPLICATION_JSON;
import static com.zrkizzy.common.constant.CommonConst.UTF_8;

/**
 * 未授权自定义返回结果
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 设置编码格式
        response.setCharacterEncoding(UTF_8);
        // 定义返回的对象格式
        response.setContentType(APPLICATION_JSON);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(Result.failure(HttpStatusEnum.UNAUTHORIZED)));
        out.flush();
        out.close();
    }

}
