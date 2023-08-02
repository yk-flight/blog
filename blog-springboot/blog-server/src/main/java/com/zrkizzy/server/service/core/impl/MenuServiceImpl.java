package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.core.Menu;
import com.zrkizzy.data.mapper.MenuMapper;
import com.zrkizzy.data.query.MenuQuery;
import com.zrkizzy.data.vo.MenuVO;
import com.zrkizzy.data.vo.route.MetaVO;
import com.zrkizzy.data.vo.route.RouterVO;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.core.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
     * @return 路由外链
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

    /**
     * 获取菜单列表
     *
     * @param menuQuery 菜单信息查询对象
     * @return 菜单列表
     */
    @Override
    public List<MenuVO> listMenu(MenuQuery menuQuery) {
        List<Menu> menuList = menuMapper.listMenus(menuQuery);
        // 复制集合
        List<Menu> menus = setMenuChildren(menuList, 0L);
        if (StringUtils.hasLength(menuQuery.getName()) ||
                null != menuQuery.getStatus() ||
                (!CollectionUtils.isEmpty(menuQuery.getDataRange()) && menuQuery.getDataRange().size() == 2)) {
            // 如果封装后的对象为空
            if (CollectionUtils.isEmpty(menus)) {
                // 直接返回查询的全部数据
                return BeanCopyUtil.copyList(menuList, MenuVO.class);
            }
            // 判断是否有没有被封装的子菜单
            int count = 0;
            Map<Long, Menu> map = new HashedMap<>();
            // 获取所有菜单的数量
            for (Menu menu : menus) {
                // 计算所有子菜单数量
                count += menu.getChildren().size();
                // 添加当前菜单信息到Map集合中
                map.put(menu.getId(), menu);
            }
            // 如果封装子菜单后的所有菜单数量与查询出的不一致说明有子菜单被过滤
            if (count + menus.size() != menuList.size()) {
                // 添加子菜单到集合中
                for (Menu menu : menuList) {
                    // 如果集合中不存在当前菜单对象的父菜单ID、当前菜单对象的ID，则说明在设置子菜单时将子菜单剔除出了集合
                    if (!map.containsKey(menu.getParentId()) && !map.containsKey(menu.getId())) {
                        // 添加被剔除的子菜单
                        menus.add(menu);
                    }
                }
            }
        }
        // 定义返回结果
        List<MenuVO> result = new ArrayList<>();
        // 封装当前菜单列表
        for (Menu menu : menus) {
            // 根据具体的Menu对象来构建MenuVO返回对象
            MenuVO menuVO = buildMenuVO(menu);
            result.add(menuVO);
        }
        Collections.sort(result);
        return result;
    }

    /**
     * 构建菜单返回对象
     *
     * @param menu 菜单数据对象
     * @return 菜单数据返回对象
     */
    private MenuVO buildMenuVO(Menu menu) {
        // 复制菜单返回对象
        MenuVO menuVO = BeanCopyUtil.copy(menu, MenuVO.class);
        // 设置子菜单数据
        menuVO.setChildren(getMenuChildren(menu.getChildren()));
        return menuVO;
    }

    /**
     * 设置子菜单数据
     *
     * @param menus 菜单集合
     * @return 菜单数据返回对象集合
     */
    private List<MenuVO> getMenuChildren(List<Menu> menus) {
        // 如果子菜单为空则返回
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
        // 转集合
        List<MenuVO> result = BeanCopyUtil.copyList(menus, MenuVO.class);
        // 给排序子菜单
        Collections.sort(result);
        return result;
    }

}
