package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.Role;
import com.zrkizzy.data.mapper.RoleMapper;
import com.zrkizzy.data.dto.RoleDTO;
import com.zrkizzy.server.service.core.IRoleService;
import com.zrkizzy.data.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取所有角色
     *
     * @return 所有角色集合
     */
    @Override
    public List<Role> listRoles() {
        return roleMapper.listRoles();
    }

    /**
     * 添加或更新角色
     *
     * @param roleDTO 角色数据接收对象
     * @return 前端响应对象
     */
    @Override
    public Result<?> saveRole(RoleDTO roleDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("roleDTO: {}", roleDTO);

        // 获取数据库中重名或更新后重名的角色
        Role role = roleMapper.getRoleByName(roleDTO.getMark());
        // 根据是否包含ID来判断添加-更新操作
        if (roleDTO.getId() != null) {
            // 更新角色，如果同名角色不为空并且ID不相同则说明角色重复
            return updateRole(roleDTO, null != role && !role.getId().equals(roleDTO.getId()));
        }
        // 添加角色
        return insertRole(roleDTO, null != role);
    }

    /**
     * 更新当前角色
     *
     * @param roleDTO 角色数据接收对象
     * @param isExist 是否存在当前角色 true 存在， false 不存在
     * @return 前端响应对象
     */
    private Result<?> updateRole(RoleDTO roleDTO, Boolean isExist) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 如果数据库中否存在修改后同名的角色
        if (isExist) {
            return Result.failure(HttpStatusEnum.ROLE_EXIST);
        }
        // 修改当前角色的更新时间
        roleDTO.setUpdateTime(LocalDateTime.now());
        // 对角色进行更新操作并返回响应结果
        return roleMapper.updateRoleById(BeanCopyUtil.copy(roleDTO, Role.class)) == 1 ?
                Result.success(HttpStatusEnum.SUCCESS, "角色更新成功") : Result.failure();
    }

    /**
     * 添加新的角色
     *
     * @param roleDTO 角色数据接收对象
     * @return 前端响应对象
     */
    private Result<RoleVO> insertRole(RoleDTO roleDTO, Boolean isExist) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 判断数据库中是否存在同名角色
        if (isExist) {
            return Result.failure(HttpStatusEnum.ROLE_EXIST);
        }
        // 生成角色ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        roleDTO.setId(id);
        // 设置创建时间以及更新时间
        roleDTO.setCreateTime(LocalDateTime.now());
        roleDTO.setUpdateTime(LocalDateTime.now());

        // 添加角色数据并返回添加结果
        return roleMapper.insertRole(BeanCopyUtil.copy(roleDTO, Role.class)) == 1 ?
                 Result.success(HttpStatusEnum.SUCCESS, "角色添加成功") :
                Result.failure();
    }
}
