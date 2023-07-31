package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.data.dto.core.UserRoleDTO;
import com.zrkizzy.server.service.core.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色关联控制器
 *
 * @author zhangrongkang
 * @since 2023/7/26
 */
@Api(tags = "用户角色关联控制器")
@RestController
@RequestMapping("/admin/user-role")
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation("更新用户角色")
    @PutMapping("/update")
    @OperateLogAnnotation(type = AnnotationConst.UPDATE)
    public Result<Boolean> update(@Validated @RequestBody UserRoleDTO userRoleDTO) {
        // 更新用户角色
        return Result.success(userRoleService.update(userRoleDTO));
    }
}
