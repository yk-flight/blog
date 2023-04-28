package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单角色实体类
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Data
@ApiModel("菜单角色关联对象")
@TableName("tb_menu_role")
public class MenuRole implements Serializable {

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty("主键")
    private Long id;

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
