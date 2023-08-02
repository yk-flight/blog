package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单实体类
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("菜单对象")
@TableName("tb_menu")
public class Menu extends BaseEntity {

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 父菜单ID
     */
    @ApiModelProperty("父菜单ID")
    private Long parentId;

    /**
     * 访问路径
     */
    @ApiModelProperty("访问路径")
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty("组件")
    private String component;

    /**
     * 是否缓存：0: 不缓存，1: 缓存
     */
    @ApiModelProperty("是否缓存")
    private Boolean isCache;

    /**
     * 是否外链：0: 不是外链， 1: 外链
     */
    @ApiModelProperty("是否外链")
    private Boolean isLink;

    /**
     * 是否隐藏：0: 不隐藏， 1: 隐藏
     */
    @ApiModelProperty("是否隐藏")
    private Boolean visible;

    /**
     * 状态，0：禁用，1：正常
     */
    @ApiModelProperty("菜单状态")
    private Boolean status;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 菜单顺序
     */
    @ApiModelProperty("菜单顺序")
    private Integer order;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    @ApiModelProperty("子菜单")
    private List<Menu> children;

}
