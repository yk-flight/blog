package com.zrkizzy.common.annotation;

import java.lang.annotation.*;

/**
 * 操作日志自定义注解
 *
 * @author zhangrongkang
 * @since 2023/6/23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateAnnotation {
}
