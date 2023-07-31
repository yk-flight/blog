package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.core.Menu;

import java.util.List;


/**
 * 菜单数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取所有菜单
     *
     * @return 菜单集合
     */
    List<Menu> getAllRoutes();

    /**
     * 根据角色名称获取对应菜单
     *
     * @param roleName 角色名称
     * @return 角色对应菜单
     */
    List<Menu> getRoutes(String roleName);
}
