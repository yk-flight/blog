package com.zrkizzy.web.controller.monitor;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.vo.monitor.ServiceMonitorVO;
import com.zrkizzy.server.service.monitor.IServiceMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

/**
 * 服务监控控制器
 *
 * @author zhangrongkang
 * @since 2023/7/7
 */
@Api(tags = "服务监控控制器")
@RestController
@RequestMapping("/admin/service-monitor")
public class ServiceMonitorController {

    @Autowired
    private IServiceMonitorService serviceMonitorService;

    @ApiOperation("获取服务监控信息")
    @GetMapping("/getMonitorInfo")
    public Result<ServiceMonitorVO> getMonitorInfo() throws UnknownHostException {
        // 获取系统监控信息
        return Result.success(serviceMonitorService.getMonitorInfo());
    }

}
