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

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化时间
     *
     * @param date   日期对象
     * @param format 格式化内容
     * @return 格式化后的数据
     */
    public static String formatDate(Date date, String format) {
        // 返回格式化后的数据
        return new SimpleDateFormat(format).format(date);
    }

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
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return new Date();
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
            case 10 -> YYYY_MM_DD;
            case 19 -> YYYY_MM_DD_HH_MM_SS;
            default -> throw new IllegalStateException("Unexpected value: " + str.length());
        };
        // 根据定义好的模板来格式化数据
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        // 对于 yyyy-MM-dd 格式的数据需要单独处理
        if (YYYY_MM_DD.equals(format)) {
            LocalDate date = LocalDate.parse(str, formatter);
            return date.atStartOfDay();
        }
        // 返回格式化的数据
        return LocalDateTime.parse(str, formatter);
    }

    /**
     * 计算时间差
     *
     * @param endDate   最后时间
     * @param startTime 开始时间
     * @return 时间差（天/小时/分钟）
     */
    public static String timeDistance(Date endDate, Date startTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

}
