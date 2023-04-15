package com.zrkizzy.common.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式校验工具类
 *
 * @author zhangrongkang
 * @since 2023/4/12
 */
public class ValidatorUtil {
    /**
     * 邮箱正则表达式
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");

    /**
     * 手机号正则表达式
     */
    private static final Pattern MOBILE_PATTERN = Pattern.compile("[1]([3-9])[0-9]{9}$");

    /**
     * 校验字符是否为邮箱格式
     *
     * @param email 传入字符
     * @return true：邮箱格式；false：非邮箱格式
     */
    public static Boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }


    /**
     * 判断当前传来的字符是否为手机号
     *
     * @param mobile 传入字符
     * @return true：是手机号，false：不是手机号
     */
    public static Boolean validateMobile(String mobile) {
        // 判空
        if (!StringUtils.hasLength(mobile)) {
            return false;
        }
        // 匹配是不是手机号
        Matcher matcher = MOBILE_PATTERN.matcher(mobile);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(validateEmail("441030517@QQ..com"));
        System.out.println(validateEmail("1107531656@q?q?.com"));
        System.out.println(validateEmail("5579001QQ@.COM"));
        System.out.println(validateEmail("xxxxxxxxx@wwew_163sadasdf.com.cn"));
        System.out.println(validateEmail("xxxxxxxxx@_.com.cn"));

        System.out.println(validateEmail("1072876976@qq.com"));
        System.out.println(validateEmail("xxxxxxxxx@wwew-163.com.cn"));

    }

}
