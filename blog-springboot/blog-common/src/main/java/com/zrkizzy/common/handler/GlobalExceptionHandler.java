package com.zrkizzy.common.handler;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.BusinessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局异常处理类
 *
 * @author zhangrongkang
 * @since 2023/03/14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数异常全局处理
     *
     * @param e 参数异常
     * @return 前端响应对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> exceptionHandler(MethodArgumentNotValidException e) {
        // 返回对应的错误信息到前端
        return Result.failure(HttpStatusEnum.BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 用户登录已过期异常全局处理
     *
     * @param e 参数异常
     * @return 前端响应对象
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result<?> exceptionHandler(UsernameNotFoundException e) {
        // 返回对应的错误信息到前端
        return Result.failure(HttpStatusEnum.USER_NOT_FOUND, e.getMessage());
    }

    /**
     * 业务逻辑异常全局处理
     *
     * @param e 参数异常
     * @return 前端响应对象
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> exceptionHandler(BusinessException e) {
        // 返回对应的错误信息到前端
        return Result.failure(e.getHttpStatusEnum());
    }

    /**
     * 全局异常处理
     *
     * @param e 大异常
     * @return 前端响应对象
     */
//    @ExceptionHandler(Exception.class)
//    public Result<?> exceptionHandler(Exception e) {
//        return Result.failure();
//    }
}