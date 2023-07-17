package com.zrkizzy.common.annotation;

import java.lang.annotation.*;

/**
 * 输入/返回参数自定义注解
 *
 * @author zhangrongkang
 * @since 2023/7/17
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamMean {

    /**
     * 输入参数含义，多个参数用","隔开
     *
     * @return 输入参数
     */
    String in();

    /**
     * 返回参数含义，多个参数用","隔开
     *
     * @return 返回参数
     */
    String out();

}
