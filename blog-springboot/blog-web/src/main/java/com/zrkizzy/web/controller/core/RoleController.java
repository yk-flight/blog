package com.zrkizzy.web.controller.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.SecurityConst;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Role;
import com.zrkizzy.data.dto.core.RoleDTO;
import com.zrkizzy.data.query.RoleQuery;
import com.zrkizzy.data.vo.RoleVO;
import com.zrkizzy.server.service.core.IRoleService;
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
@Api(tags = "角色管理控制器")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation("获取所有角色")
    @PostMapping("/list")
    public Result<PageResult<RoleVO>> listRoles(@RequestBody RoleQuery roleQuery) {
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
        // 检查操作的角色是否为系统最高权限角色
        if (roleDTO.getMark().equals(SecurityConst.ADMIN)) {
            return Result.failure(HttpStatusEnum.ROLE_NOT_ACTION);
        }
        return Result.success(roleService.saveRole(roleDTO));
    }

    @ApiOperation("获取指定角色信息")
    @GetMapping("/getRoleById/{roleId}")
    public Result<RoleVO> getRoleById (@PathVariable Long roleId) {
        return Result.success(BeanCopyUtil.copy(roleService.getRoleById(roleId), RoleVO.class));
    }

    @ApiOperation("批量删除角色数据")
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody List<Long> ids) {
        return Result.success(roleService.deleteBatch(ids));
    }

    @ApiOperation("获取角色选项")
    @GetMapping("/listRoleOptions")
    public Result<List<OptionsVO>> listRoleOptions() {
        return Result.success(roleService.listRoleOptions());
    }

}
