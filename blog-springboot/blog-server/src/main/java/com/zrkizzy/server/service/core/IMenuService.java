package com.zrkizzy.server.service.core;

import com.zrkizzy.data.query.MenuQuery;
import com.zrkizzy.data.vo.MenuVO;
import com.zrkizzy.data.vo.route.RouterVO;

import java.util.List;

/**
 * 菜单业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
public interface IMenuService {
    /**
     * 获取菜单数据
     *
     * @return 当前登录用户的菜单
     */
    List<RouterVO> getRoutes();

    /**
     * 获取菜单列表
     *
     * @param menuQuery 菜单信息查询对象
     * @return 菜单列表
     */
    List<MenuVO> listMenu(MenuQuery menuQuery);
}
