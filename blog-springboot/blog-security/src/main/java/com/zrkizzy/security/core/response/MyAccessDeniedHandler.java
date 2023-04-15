package com.zrkizzy.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.zrkizzy.common.constant.CommonConst.APPLICATION_JSON;
import static com.zrkizzy.common.constant.CommonConst.UTF_8;

/**
 * 权限不足自定义返回结果
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding(UTF_8);
        response.setContentType(APPLICATION_JSON);
        // 获取输出流
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(Result.failure(HttpStatusEnum.FORBIDDEN)));
        out.flush();
        out.close();
    }
}
