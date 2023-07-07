package com.zrkizzy.data.domain.monitor;

import com.zrkizzy.common.utils.CalculateUtil;
import lombok.Setter;

import java.io.Serializable;

/**
 * CPU监控实体类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Setter
public class CpuMonitor implements Serializable {

    /**
     * CPU核心数
     */
    private int coreNum;

    /**
     * 用户使用率
     */
    private double userUseRate;

    /**
     * 系统使用率
     */
    private double systemUseRate;

    /**
     * 空闲率
     */
    private double freeRate;

    /**
     * 等待率
     */
    private double waitRate;

    /**
     * CPU总使用率
     */
    private double totalUseRate;

    public int getCoreNum() {
        return coreNum;
    }

    public double getUserUseRate() {
        return CalculateUtil.round(CalculateUtil.multiply(userUseRate / totalUseRate, 100), 2);
    }

    public double getSystemUseRate() {
        return CalculateUtil.round(CalculateUtil.multiply(systemUseRate / totalUseRate, 100), 2);
    }

    public double getFreeRate() {
        return CalculateUtil.round(CalculateUtil.multiply(freeRate / totalUseRate, 100), 2);
    }

    public double getWaitRate() {
        return CalculateUtil.round(CalculateUtil.multiply(waitRate / totalUseRate, 100), 2);
    }

    public double getTotalUseRate() {
        return CalculateUtil.round(CalculateUtil.multiply(totalUseRate, 100), 2);
    }

    @Override
    public String toString() {
        return "CpuMonitor{" + "CPU核心数：" + getCoreNum() +
                ", 用户使用率：" + getUserUseRate() +
                ", 系统使用率：" + getSystemUseRate() +
                ", 空闲率：" + getFreeRate() +
                ", 等待率：" + getWaitRate() +
                ", 总使用率：" + getTotalUseRate() +
                '}';
    }
}
