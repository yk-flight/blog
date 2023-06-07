package com.zrkizzy.web.controller.system.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.domain.Role;
import com.zrkizzy.data.dto.RoleDTO;
import com.zrkizzy.data.query.RoleQuery;
import com.zrkizzy.data.vo.RoleVO;
import com.zrkizzy.server.service.core.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理控制器
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
@Api(tags = "角色管理控制器")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation("获取所有角色")
    @PostMapping("/list")
    public Result<PageResult<RoleVO>> listRoles(@RequestBody RoleQuery roleQuery) {
        System.out.println(roleQuery);
        // 查询到对应的角色集合
        Page<Role> rolePage = roleService.listRoles(roleQuery);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<RoleVO>builder().total(rolePage.getTotal())
                // 角色集合
                .list(BeanCopyUtil.copyList(rolePage.getRecords(), RoleVO.class)).build());
    }

    @ApiOperation("添加-更新角色")
    @PostMapping("/save")
    public Result<?> saveRole(@RequestBody RoleDTO roleDTO) {
        return roleService.saveRole(roleDTO);
    }
}
