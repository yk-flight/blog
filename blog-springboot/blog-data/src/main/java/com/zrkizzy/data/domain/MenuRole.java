package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单角色实体类
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("菜单角色关联对象")
@TableName("tb_menu_role")
public class MenuRole extends BaseEntity {

    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Long menuId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long roleId;

}
