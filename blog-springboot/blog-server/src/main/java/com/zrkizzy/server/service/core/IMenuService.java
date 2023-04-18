package com.zrkizzy.server.service.core;

import com.zrkizzy.server.vo.route.RouterVO;

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
}
