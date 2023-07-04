package com.zrkizzy.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
public class TimeUtil {

    /**
     * 生成当前时间（无分隔符）
     *
     * @return 当前时间
     */
    public static String generateNowTime() {
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        // 定义格式化模板
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 返回格式化后的名称
        return simpleDateFormat.format(date);
    }

    /**
     * <p>获取当前时间</p>
     * YYYY/M/d：2023/6/11
     *
     * @return 当前时间
     */
    public static String getNowDate() {
        // 获取当前地区时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 将当前格式时间返回
        return localDateTime.getYear() + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getDayOfMonth();
    }

    /**
     * 将传来的String类型转为LocalDateTime类型
     *
     * @param str String类型日期数据
     * @return LocalDateTime数据
     */
    public static LocalDateTime parseLocalDateTime(String str) {
        // 定义格式化模板
        String format = switch (str.length()) {
            case 10 -> "yyyy-MM-dd";
            case 19 -> "yyyy-MM-dd HH:mm:ss";
            default -> throw new IllegalStateException("Unexpected value: " + str.length());
        };
        // 根据定义好的模板来格式化数据
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        // 对于 yyyy-MM-dd 格式的数据需要单独处理
        if ("yyyy-MM-dd".equals(format)) {
            LocalDate date = LocalDate.parse(str, formatter);
            return date.atStartOfDay();
        }
        // 返回格式化的数据
        return LocalDateTime.parse(str, formatter);
    }

}
