package com.zrkizzy.web.controller.system.core;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.dto.RoleDTO;
import com.zrkizzy.server.service.core.IRoleService;
import com.zrkizzy.server.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理控制器
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
@Api(tags = "角色管理模块")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation("获取所有角色")
    @PostMapping("/list")
    public List<RoleVO> listRoles() {
        return BeanCopyUtil.copyList(roleService.listRoles(), RoleVO.class);
    }

    @ApiOperation("添加-更新角色")
    @PostMapping("/save")
    public Result<?> saveRole(@RequestBody RoleDTO roleDTO) {
        return roleService.saveRole(roleDTO);
    }
}
