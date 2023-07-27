package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.dto.ModuleDTO;
import com.zrkizzy.data.vo.ModuleVO;
import com.zrkizzy.server.service.core.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("获取请求模块选项")
    @GetMapping("/listModuleOptions")
    public Result<List<OptionsVO>> listModuleOptions() {
        return Result.success(moduleService.listModuleOptions());
    }

    @ApiOperation("分页查询资源模块")
    @PostMapping("/list")
    public Result<PageResult<ModuleVO>> list(@RequestBody ModuleDTO moduleDTO) {
        return null;
    }

}
