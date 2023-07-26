package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户ID更新用户角色
     *
     * @param roleId 角色ID
     * @param userId 用户ID
     * @return 受影响行数
     */
    Integer updateByUserId(@Param("roleId") Long roleId, @Param("userId") Long userId);

    /**
     * 根据用户ID获取角色ID
     *
     * @param ids 用户ID集合
     * @return 角色ID集合
     */
    List<Long> listRoleIdByUserId(@Param("ids") List<Long> ids);
}
