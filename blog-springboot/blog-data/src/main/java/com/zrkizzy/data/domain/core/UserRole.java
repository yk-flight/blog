package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联实体类
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
@Data
@ApiModel("用户角色关联对象")
@TableName("tb_user_role")
public class UserRole implements Serializable {

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 用户主键
     */
    @ApiModelProperty("用户主键")
    private Long userId;

    /**
     * 角色主键
     */
    @ApiModelProperty("角色主键")
    private Long roleId;

}
