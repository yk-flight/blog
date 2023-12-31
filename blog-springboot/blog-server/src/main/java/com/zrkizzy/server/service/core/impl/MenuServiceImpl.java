package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.data.domain.Menu;
import com.zrkizzy.data.mapper.MenuMapper;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.core.IMenuService;
import com.zrkizzy.data.vo.route.MetaVO;
import com.zrkizzy.data.vo.route.RouterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.zrkizzy.common.constant.CommonConst.*;

/**
 * 菜单业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Slf4j
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单数据
     *
     * @return 当前登录用户的菜单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RouterVO> getRoutes() {
        // 根据用户角色获取对应数据
        List<Menu> menus = securityUtil.isAdmin() ?
                menuMapper.getAllRoutes() :
                menuMapper.getRoutes(securityUtil.getLoginUserRole());
        // 封装菜单数据并返回
        return buildRoutes(menus);
    }

    /**
     * 构建菜单数据
     *
     * @param menus 用户所有菜单
     * @return 菜单数据
     */
    private List<RouterVO> buildRoutes(List<Menu> menus) {
        // 封装子菜单
        List<Menu> menuList = setMenuChildren(menus, 0L);
        // 返回路由集合
        List<RouterVO> routes = new ArrayList<>();
        // 遍历用户菜单集合转为路由集合
        for (Menu menu : menuList) {
            // 根据当前菜单构建路由
            RouterVO routerVO = buildRouteVO(menu);
            routes.add(routerVO);
        }
        // 排序菜单并返回
        Collections.sort(routes);
        return routes;
    }

    /**
     * 获取当前子菜单
     *
     * @param children 子菜单集合
     * @return 子菜单集合
     */
    private List<RouterVO> getRouterChildren(List<Menu> children) {
        // 没有子菜单则返回
        if (CollectionUtils.isEmpty(children)) {
            return null;
        }
        // 返回路由集合
        List<RouterVO> childRoutes = new ArrayList<>();
        // 遍历用户菜单集合转为路由集合
        for (Menu menu : children) {
            childRoutes.add(buildRouteVO(menu));
        }
        // 封装子菜单排序后返回
        Collections.sort(childRoutes);
        return childRoutes;
    }

    /**
     * 根据菜单对象构建路由返回对象
     *
     * @param menu 菜单对象
     * @return 路由返回对象
     */
    private RouterVO buildRouteVO(Menu menu) {
        // 根据当前菜单构建路由
        return RouterVO.builder()
                // 组件名称 => path首字母转大写
                .name(StringUtils.capitalize(menu.getPath()))
                .component(getRouterComponent(menu))
                .hidden(menu.getVisible())
                // 拼接组件访问路径
                .path(getRouterPath(menu))
                .children(getRouterChildren(menu.getChildren()))
                .redirect(getRouterRedirect(menu.getChildren()))
                .order(menu.getOrder())
                .meta(new MetaVO(menu.getName(), menu.getIcon(), menu.getIsCache(), getRouterLink(menu)))
                .build();
    }

    /**
     * 配置路由返回对象是否重定向
     *
     * @param children 子菜单
     * @return String 是否重定向
     */
    private String getRouterRedirect(List<Menu> children) {
        return CollectionUtils.isEmpty(children) ? null : NO_REDIRECT;
    }

    /**
     * 递归封装子菜单
     *
     * @param menus 所有菜单集合
     * @param parentId 父ID
     */
    private List<Menu> setMenuChildren(List<Menu> menus, Long parentId) {
        // 存储所有菜单
        List<Menu> childrenList = new ArrayList<>();
        // 遍历所有路由对象集合
        for (Menu menu : menus) {
            // 菜单对象父ID与当前父ID一致
            if (menu.getParentId().equals(parentId)) {
                childrenList.add(menu);
            }
        }
        // 递归设置所有子菜单
        for (Menu menu : childrenList) {
            // 设置所有子菜单
            menu.setChildren(setMenuChildren(menus, menu.getId()));
        }
        // 返回封装好的菜单
        return childrenList;
    }

    /**
     * 获取路由外链
     *
     * @param menu 菜单对象
     * @retur 路由外链
     */
    private String getRouterLink(Menu menu) {
        // 外链则返回链接地址
        return menu.getIsLink()? menu.getPath() : null;
    }

    /**
     * 获取组件访问路径
     *
     * @param menu 菜单对象
     * @return 当前菜单对应访问路径
     */
    private String getRouterPath(Menu menu) {
        // 外链直接返回
        if (menu.getPath().startsWith(HTTP_PREFIX) || menu.getPath().startsWith(HTTPS_PREFIX)) {
            return menu.getPath();
        }
        // 父菜单拼接路径，子菜单直接返回
        return menu.getParentId().equals(PARENT_ID) ? "/" + menu.getPath() : menu.getPath();
    }

    /**
     * 获取路由组件
     *
     * @param menu 菜单对象
     * @return 当前菜单对应组件
     */
    private String getRouterComponent(Menu menu) {
        // 父菜单则直接返回Layout
        return !StringUtils.hasLength(menu.getComponent()) ? LAYOUT : menu.getComponent();
    }
}
