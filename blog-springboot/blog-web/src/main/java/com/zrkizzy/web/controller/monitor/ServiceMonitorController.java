package com.zrkizzy.web.controller.monitor;

import com.zrkizzy.server.service.monitor.IServiceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务监控控制器
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@RestController
@RequestMapping("/admin/service-monitor")
public class ServiceMonitorController {

    @Autowired
    private IServiceMonitorService serviceMonitorService;
}
