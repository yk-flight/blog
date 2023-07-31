package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.data.domain.core.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色数据持久化接口
 * 
 * @author zhangrongkang
 * @since 2023/3/8
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过角色名称获取角色
     *
     * @param name 角色名称
     * @return 角色对象
     */
    Role getRoleByName(String name);

    /**
     * 通过角色标识获取角色
     *
     * @param mark 角色标识
     * @return 角色对象
     */
    Role getRoleByMark(String mark);

    /**
     * 获取角色名称相同但角色ID不同但角色数量
     *
     * @param name 角色名称
     * @param id 角色ID
     * @return 对应角色数量
     */
    Integer countByNameAndId(@Param("name") String name, @Param("id") Long id);

    /**
     * 获取角色标识相同但角色ID不同但角色数量
     *
     * @param mark 角色标识
     * @param id 角色ID
     * @return 对应角色数量
     */
    Integer countByMarkAndId(@Param("mark") String mark, @Param("id") Long id);

    /**
     * 获取角色名称相同的角色数量
     *
     * @param name 角色名称
     * @return 角色数量
     */
    Integer countByName(String name);

    /**
     * 获取角色名称相同的角色数量
     *
     * @param mark 角色标识
     * @return 角色数量
     */
    Integer countByMark(String mark);

    /**
     * 获取所有角色选项
     *
     * @return 角色选项集合
     */
    List<OptionsVO> listRoleOptions();
}
