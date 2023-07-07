package com.zrkizzy.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 高精度数值计算工具类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
public class CalculateUtil {

    /**
     * 默认除法运算精度
     */
    private static final int DEFAULT_DIVISION_SCALE = 10;

    /**
     * 两数相加
     *
     * @param addend1 加数
     * @param addend2 加数
     * @return 和
     */
    public static double add(double addend1, double addend2) {
        BigDecimal b1 = new BigDecimal(Double.toString(addend1));
        BigDecimal b2 = new BigDecimal(Double.toString(addend2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @return 积
     */
    public static double multiply(double multiplicand, double multiplier) {
        BigDecimal b1 = new BigDecimal(Double.toString(multiplicand));
        BigDecimal b2 = new BigDecimal(Double.toString(multiplier));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * <p>除法运算</p>
     * 当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @return 商
     */
    public static double divide(double dividend, double divisor) {
        return divide(dividend, divisor, DEFAULT_DIVISION_SCALE);
    }

    /**
     * <p>指定精度的除法运算</p>
     * 当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入
     *
     * @param dividend 被除数
     * @param divisor 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 商
     */
    public static double divide(double dividend, double divisor, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        if (b1.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.doubleValue();
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 小数位四舍五入处理
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double value, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = BigDecimal.ONE;
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }

}
