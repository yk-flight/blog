package com.zrkizzy.common.utils;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 雪花算法生成ID工具类
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Component
public class SnowFlakeUtil {

    /**
     * 数据中心ID
     */
    @Value("${snowflake.datacenterId}")
    private long datacenterId;

    /**
     * 机器ID
     */
    @Value("${snowflake.machineId}")
    private long machineId;

    private final Snowflake snowflake = new Snowflake(datacenterId, machineId);

    /**
     * 根据雪花算法生成ID
     *
     * @return 返回生成的ID
     */
    public Long nextId() {
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil();
        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlakeUtil.nextId());
        }
    }

}
