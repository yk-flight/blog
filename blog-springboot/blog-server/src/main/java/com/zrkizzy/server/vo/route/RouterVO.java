package com.zrkizzy.server.vo.route;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 菜单数据返回对象
 * 
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Data
@Builder
@AllArgsConstructor
public class RouterVO implements Comparable<RouterVO> {

    /**
     * 组件名称
     */
    private String name;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 是否隐藏：0: 不隐藏， 1: 隐藏
     */
    private Boolean hidden;

    /**
     * 路由信息：路由名称、图标等
     */
    private MetaVO meta;

    /**
     * 是否可以跳转，定义了noRedirect后在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 子菜单
     */
    private List<RouterVO> children;

    /**
     * 菜单序号
     */
    @JsonIgnore
    private Integer order;

    /**
     * 自定义排序规则
     *
     * @return 排序结果 升序：> 0，降序: < 0
     */
    @Override
    public int compareTo(RouterVO routerVO) {
        return this.order - routerVO.getOrder();
    }
}
