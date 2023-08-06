package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.common.base.response.OptionsVO;
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
     * @param roleMark 角色标识
     * @return 角色对应菜单
     */
    List<Menu> getRoutes(String roleMark);

    /**
     * 查询符合条件的所有菜单数据
     *
     * @param menuQuery 菜单信息查询对象
     * @return 符合条件的菜单集合
     */
    List<Menu> listAllMenus(@Param("menuQuery") MenuQuery menuQuery);

    /**
     * 查询指定角色符合条件的所有菜单数据
     *
     * @param menuQuery 菜单信息查询对象
     * @param roleMark 角色标识
     * @return 符合条件的菜单集合
     */
    List<Menu> listMenus(@Param("menuQuery") MenuQuery menuQuery, @Param("roleMark") String roleMark);

    /**
     * 查询所有菜单选项
     *
     * @return 当前用户具有的菜单选项
     */
    List<OptionsVO> listAllMenuOptions();

    /**
     * 查询对应角色的菜单选项
     *
     * @param roleId 角色ID
     * @return 角色对应的菜单选项
     */
    List<OptionsVO> listMenuOptions(Long roleId);
}
