package com.zrkizzy.data.domain.monitor;

import com.zrkizzy.common.utils.CalculateUtil;
import lombok.Setter;

import java.io.Serializable;

/**
 * 内存监控实体类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Setter
public class MemoryMonitor implements Serializable {

    /**
     * 总内存
     */
    private double total;

    /**
     * 已使用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public double getTotal() {
        return CalculateUtil.divide(total, (1024 * 1024 * 1024), 2);
    }

    public double getUsed() {
        return CalculateUtil.divide(used, (1024 * 1024 * 1024), 2);
    }

    public double getFree() {
        return CalculateUtil.divide(free, (1024 * 1024 * 1024), 2);
    }

    /**
     * 内存使用率
     *
     * @return 使用率
     */
    public double getUsage() {
        return CalculateUtil.multiply(CalculateUtil.divide(used, total, 4), 100);
    }

}
