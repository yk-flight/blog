package com.zrkizzy.common.annotation.validator;

import com.zrkizzy.common.annotation.IsEmail;
import com.zrkizzy.common.utils.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义邮箱校验注解实现类
 *
 * @author zhangrongkang
 * @since 2023/4/12
 */
public class IsEmailValidator implements ConstraintValidator<IsEmail, String> {

    @Override
    public void initialize(IsEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // 非空判断
        if (!StringUtils.hasLength(value)) {
            return false;
        }
        // 返回校验结果
        return ValidatorUtil.validateEmail(value);
    }
}
