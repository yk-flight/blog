package com.zrkizzy.common.annotation;

import com.zrkizzy.common.constant.AnnotationConst;

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
public @interface OperateLogAnnotation {

    /**
     * 操作类型 0 其他操作，1 新增，2 修改， 3 删除， 4 查询
     */
    String type() default AnnotationConst.QUERY;

}
