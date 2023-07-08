package com.zrkizzy.server.service.monitor.impl;

import com.zrkizzy.common.utils.IpUtil;
import com.zrkizzy.common.utils.ServletUtil;
import com.zrkizzy.data.domain.monitor.CpuMonitor;
import com.zrkizzy.data.domain.monitor.JvmMonitor;
import com.zrkizzy.data.domain.monitor.ServerMonitor;
import com.zrkizzy.data.vo.monitor.ServiceMonitorVO;
import com.zrkizzy.server.service.monitor.IServiceMonitorService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.Util;

import java.util.Properties;

/**
 * 系统监控业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Service
public class ServiceMonitorServiceImpl implements IServiceMonitorService {

    /**
     * CPU消息获取等待时间
     */
    private static final int OSHI_WAIT_SECOND = 1000;

    /**
     * 获取服务监控数据信息
     *
     * @return 服务监控数据返回对象
     */
    @Override
    public ServiceMonitorVO getMonitorInfo() {
        // 定义返回结果
        ServiceMonitorVO serviceMonitorVO = new ServiceMonitorVO();
        // 获取系统信息对象
        SystemInfo systemInfo = new SystemInfo();

        // 获取硬件抽象层对象，用于获取硬件信息
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        // 设置CPU信息
        serviceMonitorVO.setCpuMonitor(setCpuInfo(hardware.getProcessor()));
        // 设置JVM信息
        serviceMonitorVO.setJvmMonitor(setJvmInfo());
        // 设置服务器信息
        serviceMonitorVO.setServerMonitor(setServerInfo());

        return serviceMonitorVO;
    }

    /**
     * 设置服务器信息
     *
     * @return 服务器信息
     */
    private ServerMonitor setServerInfo() {
        // 服务监控实体
        ServerMonitor serverMonitor = new ServerMonitor();
        // 获取系统参数
        Properties props = System.getProperties();
        // 服务器名称
        serverMonitor.setServerName(IpUtil.getHostName());
        // 服务器IP
        serverMonitor.setServerIp(IpUtil.getIpAddress(ServletUtil.getRequest()));
        // 操作系统
        serverMonitor.setOs(props.getProperty("os.name"));
        // 系统架构
        serverMonitor.setOsArch(props.getProperty("os.arch"));
        // 项目路径
        serverMonitor.setProjectPath(props.getProperty("user.dir"));

        return serverMonitor;
    }

    /**
     * 设置CPU信息
     *
     * @param processor 处理器信息
     * @return CPU监控实体
     */
    private CpuMonitor setCpuInfo(CentralProcessor processor) {
        // 实例化CPU监控实体
        CpuMonitor cpuMonitor = new CpuMonitor();

        // 获取CPU使用情况，包含不同类型的CPU使用时间
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 让程序暂停一段时间，等待一段时间后获取新的CPU使用情况
        Util.sleep(OSHI_WAIT_SECOND);
        // 获取最新CPU使用情况
        long[] ticks = processor.getSystemCpuLoadTicks();
        // 用户进程以调整过优先级的方式运行的时间
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        // 中断请求（IRQ）的时间
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        // 软件中断请求（IRQ）的时间
        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        // 当前虚拟机实例试图执行另外一个虚拟处理器上运行的任务时被“窃取”的时间
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        // 内核空间程序的时间
        long system = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        // 用户空间程序的时间
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        // 等待输入/输出完成的时间
        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        //  CPU 非活动时间
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        // 计算总的CPU使用时间
        long totalCpu = user + nice + system + idle + ioWait + irq + softIrq + steal;

        // CPU核数
        cpuMonitor.setCoreNum(processor.getLogicalProcessorCount());
        // 总使用时间
        cpuMonitor.setTotalUseRate(totalCpu);
        // 系统使用率
        cpuMonitor.setSystemUseRate(system);
        // 用户使用率
        cpuMonitor.setUserUseRate(user);
        // 等待率
        cpuMonitor.setWaitRate(ioWait);
        // 空闲率
        cpuMonitor.setFreeRate(idle);

        return cpuMonitor;
    }

    /**
     * 设置Java虚拟机信息
     *
     * @return Jvm虚拟机信息
     */
    private JvmMonitor setJvmInfo() {
        // 定义返回结果
        JvmMonitor jvmMonitor = new JvmMonitor();

        Properties props = System.getProperties();
        // 占用内存大小
        jvmMonitor.setTotalMemory(Runtime.getRuntime().totalMemory());
        // 最大可用内存大小
        jvmMonitor.setMaxAble(Runtime.getRuntime().maxMemory());
        // 空闲内存大小
        jvmMonitor.setFreeMemory(Runtime.getRuntime().freeMemory());
        // Java版本
        jvmMonitor.setVersion(props.getProperty("java.version"));
        // Jdk路径
        jvmMonitor.setHome(props.getProperty("java.home"));

        return jvmMonitor;
    }

}
