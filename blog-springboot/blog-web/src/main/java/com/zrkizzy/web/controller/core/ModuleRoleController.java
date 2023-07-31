package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.data.dto.core.ModuleRoleDTO;
import com.zrkizzy.server.service.core.IModuleRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private IModuleRoleService moduleRoleService;

    @ApiOperation("分配角色模块权限")
    @PostMapping("/save")
    @OperateLogAnnotation(type = AnnotationConst.ADD)
    public Result<Boolean> save(@Validated @RequestBody ModuleRoleDTO moduleRoleDTO) {
        moduleRoleService.save(moduleRoleDTO);
        return Result.success();
    }
}
