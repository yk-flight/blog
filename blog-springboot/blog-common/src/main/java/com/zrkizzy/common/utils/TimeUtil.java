package com.zrkizzy.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
}
