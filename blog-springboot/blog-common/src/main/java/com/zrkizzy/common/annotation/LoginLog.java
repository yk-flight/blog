package com.zrkizzy.common.annotation;

import java.lang.annotation.*;

/**
 * 登录日志自定义注解
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginLog {

}
