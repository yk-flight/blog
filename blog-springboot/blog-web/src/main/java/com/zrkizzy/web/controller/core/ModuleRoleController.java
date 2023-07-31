package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.data.dto.core.ModuleRoleDTO;
import com.zrkizzy.data.vo.resource.ModuleRoleVO;
import com.zrkizzy.data.vo.resource.ModuleTreeVO;
import com.zrkizzy.server.service.core.IModuleRoleService;
import com.zrkizzy.server.service.core.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块角色关联控制器
 *
 * @author zhangrongkang
 * @since 2023/7/31
 */
@Api(tags = "模块角色关联控制器")
@RestController
@RequestMapping("/admin/module-role")
public class ModuleRoleController {

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IModuleRoleService moduleRoleService;

    @ApiOperation("获取角色对应模块权限")
    @GetMapping("/listByRoleId/{id}")
    public Result<ModuleRoleVO> listByRoleId(@PathVariable Long id) {
        // 获取当前角色已有的模块权限
        List<ModuleTreeVO> moduleTree = moduleRoleService.listModuleByRoleId(id);
        // 获取所有模块ID
        List<Long> moduleIds = moduleService.getAllModuleId();

        List<String> list = new ArrayList<>();
        for (Long moduleId : moduleIds) {
            list.add(String.valueOf(moduleId));
        }
        return Result.success(new ModuleRoleVO(list, moduleTree));
    }

    @ApiOperation("分配角色模块权限")
    @PostMapping("/save")
    @OperateLogAnnotation(type = AnnotationConst.ADD)
    public Result<Boolean> save(@Validated @RequestBody ModuleRoleDTO moduleRoleDTO) {
        moduleRoleService.save(moduleRoleDTO);
        return Result.success();
    }
}
