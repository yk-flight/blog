package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.server.service.core.IModuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 请求模块控制器
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Api(tags = "请求模块控制器")
@RestController
@RequestMapping("/admin/module")
public class ModuleController {
    @Autowired
    private IModuleService moduleService;

    @GetMapping("/listModuleOptions")
    public Result<List<OptionsVO>> listModuleOptions() {
        return Result.success(moduleService.listModuleOptions());
    }
}
