package com.zrkizzy.server.service.monitor;

import com.zrkizzy.data.vo.monitor.ServiceMonitorVO;

import java.net.UnknownHostException;

/**
 * 系统监控业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
public interface IServiceMonitorService {

    /**
     * 获取服务监控数据信息
     *
     * @return 服务监控数据返回对象
     */
    ServiceMonitorVO getMonitorInfo() throws UnknownHostException;
}
