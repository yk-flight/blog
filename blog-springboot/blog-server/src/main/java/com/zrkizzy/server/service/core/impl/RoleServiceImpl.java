package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.SecurityConst;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveRole(RoleDTO roleDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("roleDTO: {}", roleDTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != roleDTO.getId()) {
            // 更新角色时需要验证ID
            if (roleMapper.countByNameAndId(roleDTO.getName(), roleDTO.getId()) > 0) {
                // 返回角色名称已存在提示信息
                return Result.failure(HttpStatusEnum.ROLE_NAME_EXIST);
            }
            if (roleMapper.countByMarkAndId(roleDTO.getMark(), roleDTO.getId()) > 0) {
                // 返回角色标识已存在提示信息
                return Result.failure(HttpStatusEnum.ROLE_MARK_EXIST);
            }
            // 更新角色
            return updateRole(roleDTO);
        }
        // 新增角色时无需验证ID
        if (roleMapper.countByName(roleDTO.getName()) > 0) {
            // 返回角色名称已存在提示信息
            return Result.failure(HttpStatusEnum.ROLE_NAME_EXIST);
        }
        // 新增角色时前端传来的角色标识少了前缀
        roleDTO.setMark(SecurityConst.ROLE_PREFIX + roleDTO.getMark());
        if (roleMapper.countByMark(roleDTO.getMark()) > 0) {
            // 返回角色标识已存在提示信息
            return Result.failure(HttpStatusEnum.ROLE_MARK_EXIST);
        }
        // 添加角色
        return insertRole(roleDTO);
    }

    /**
     * 获取指定角色信息
     *
     * @param roleId 角色ID
     * @return 角色数据返回对象
     */
    @Override
    public Role getRoleById(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    /**
     * 批量删除角色数据
     *
     * @param ids 角色ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return roleMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 更新当前角色
     *
     * @param roleDTO 角色数据接收对象
     * @return 前端响应对象
     */
    private Result<?> updateRole(RoleDTO roleDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对角色进行更新操作并返回响应结果
        return roleMapper.updateById(BeanCopyUtil.copy(roleDTO, Role.class)) == 1 ?
                Result.success() : Result.failure();
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
        // TODO 分配角色的初始权限（页面权限和资源权限）
        // 添加角色数据并返回添加结果
        return roleMapper.insert(BeanCopyUtil.copy(roleDTO, Role.class)) == 1 ?
                 Result.success() : Result.failure();
    }
}
