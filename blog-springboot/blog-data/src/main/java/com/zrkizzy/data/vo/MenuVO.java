package com.zrkizzy.data.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhangrongkang
 * @since 2023/8/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuVO extends BaseVO implements Comparable<MenuVO> {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 是否缓存：0: 不缓存，1: 缓存
     */
    private Boolean isCache;

    /**
     * 是否外链：0: 不是外链， 1: 外链
     */
    private Boolean isLink;

    /**
     * 是否隐藏：0: 不隐藏， 1: 隐藏
     */
    private Boolean visible;

    /**
     * 菜单状态：0: 禁用，1: 正常
     */
    private Boolean status;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单顺序
     */
    private Integer order;

    /**
     * 子菜单
     */
    private List<MenuVO> children;

    /**
     * 自定义排序规则
     *
     * @return 排序结果 升序：> 0，降序: < 0
     */
    @Override
    public int compareTo(MenuVO menuVO) {
        return this.order - menuVO.getOrder();
    }

}
