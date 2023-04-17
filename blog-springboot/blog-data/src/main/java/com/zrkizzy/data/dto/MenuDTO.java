package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 菜单数据传输对象
 * 
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MenuDTO extends BaseDTO {

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
     * 图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<MenuDTO> children;

}
