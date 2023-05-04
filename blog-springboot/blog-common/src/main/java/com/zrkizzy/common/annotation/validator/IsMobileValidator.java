package com.zrkizzy.common.annotation.validator;

import com.zrkizzy.common.annotation.IsMobile;
import com.zrkizzy.common.utils.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义手机号校验注解实现类
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    /**
     * 初始默认为必填项
     */
    private boolean required = true;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("测试是否开始校验" + value);
        if (required) {
            return ValidatorUtil.validateMobile(value);
        }
        // 非空判断
        if (!StringUtils.hasLength(value)) {
            return false;
        }
        // 返回校验结果
        return ValidatorUtil.validateMobile(value);
    }
}
