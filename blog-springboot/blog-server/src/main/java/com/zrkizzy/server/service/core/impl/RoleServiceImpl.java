package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.Role;
import com.zrkizzy.data.dto.RoleDTO;
import com.zrkizzy.data.mapper.RoleMapper;
import com.zrkizzy.data.query.RoleQuery;
import com.zrkizzy.data.vo.RoleVO;
import com.zrkizzy.server.service.core.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
     * @param roleQuery 角色信息查询对象
     * @return 角色分页数据
     */
    @Override
    public Page<Role> listRoles(RoleQuery roleQuery) {
        // 开启分页
        Page<Role> page = new Page<>(roleQuery.getCurrentPage(), roleQuery.getPageSize());
        // 定义查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // 角色名称
        if (StringUtils.hasLength(roleQuery.getName())) {
            queryWrapper.eq("name", roleQuery.getName());
        }
        // 角色标识
        if (StringUtils.hasLength(roleQuery.getMark())) {
            queryWrapper.eq("mark", roleQuery.getMark());
        }
        // 获取时间范围
        List<String> dataRange = roleQuery.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        // 查询分页
        return roleMapper.selectPage(page, queryWrapper);
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
        // 定义查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // 角色名称和角色标识
        queryWrapper.eq("name", roleDTO.getName()).or().eq("mark", roleDTO.getMark());
        // 获取数据库中更新后重名或标识重复的角色
        Role role = roleMapper.selectOne(queryWrapper);
        // 如果同名角色不为空并且ID不相同则说明角色重复
        if (null != role && !role.getId().equals(roleDTO.getId())) {
            // 返回角色已存在提示信息
            return Result.failure(HttpStatusEnum.ROLE_INFO_EXIST);
        }
        // 根据是否包含ID来判断添加-更新操作
        if (roleDTO.getId() != null) {
            // 更新角色
            return updateRole(roleDTO);
        }
        // 添加角色
        return insertRole(roleDTO);
    }

    /**
     * 更新当前角色
     *
     * @param roleDTO 角色数据接收对象
     * @return 前端响应对象
     */
    private Result<?> updateRole(RoleDTO roleDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
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
    private Result<RoleVO> insertRole(RoleDTO roleDTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
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
