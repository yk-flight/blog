package com.zrkizzy.common.annotation;

import com.zrkizzy.common.annotation.validator.IsEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 验证邮箱自定义注解
 *
 * @author zhangrongkang
 * @since 2023/4/12
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsEmailValidator.class)
public @interface IsEmail {

    /**
     * 是否必填，默认必填
     *
     * @return true：必填，false：非必填
     */
    boolean required() default true;

    /**
     * 约束注解验证时的输出信息
     *
     * @return String
     */
    String message() default "邮箱格式错误";

    /**
     * 约束注解在验证时所属的组别
     *
     * @return Class<?>[]
     */
    Class<?>[] groups() default {};

    /**
     * 约束注解的有效负载
     *
     * @return Class<? extends Payload>[]
     */
    Class<? extends Payload>[] payload() default {};

}
