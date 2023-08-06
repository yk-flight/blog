package com.zrkizzy.data.dto.system;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/8/6
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MenuDTO extends BaseDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否缓存
     */
    private Boolean isCache;

    /**
     * 是否外链
     */
    private Boolean isLink;

    /**
     * 菜单排序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    /**
     * 访问路径
     */
    @NotBlank(message = "路由地址不能为空")
    private String path;

    /**
     * 菜单状态
     */
    private Boolean status;

    /**
     * 菜单类型：D：目录，P：页面
     */
    private Character type;

    /**
     * 显示状态
     */
    private Boolean visible;

}
