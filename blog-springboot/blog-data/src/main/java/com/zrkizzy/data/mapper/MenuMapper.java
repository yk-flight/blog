package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.core.Menu;
import com.zrkizzy.data.query.MenuQuery;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询符合条件的所有菜单数据
     *
     * @param menuQuery 菜单信息查询对象
     * @return 符合条件的菜单集合
     */
    List<Menu> listMenus(@Param("menuQuery") MenuQuery menuQuery);

}
