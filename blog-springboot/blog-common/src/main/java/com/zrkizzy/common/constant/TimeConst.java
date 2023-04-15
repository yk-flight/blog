package com.zrkizzy.common.constant;

/**
 * <p>时间全局静态常量（秒）</p>
 * 在RedisServiceImpl中定义过期时间为秒
 *
 * @author zhangrongkang
 * @since 2023/3/10
 */
public class TimeConst {

    /**
     * 五分钟
     */
    public static final Long FIVE_MINUTE = 300L;

    /**
     * 十分钟
     */
    public static final Long TEN_MINUTE = 600L;

    /**
     * 半小时
     */
    public static final Long HALF_HOUR = 1800L;

    /**
     * 一小时
     */
    public static final Long ONE_HOUR = 3600L;

    /**
     * 两小时
     */
    public static final Long TWO_HOUR = 7200L;

    /**
     * 一天
     */
    public static final Long ONE_DAY = 86400L;

    /**
     * 两天
     */
    public static final Long TWO_DAY = 172800L;

}
