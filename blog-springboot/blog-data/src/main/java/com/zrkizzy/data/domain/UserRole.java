package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联实体类
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户角色关联对象")
@TableName("tb_user_role")
public class UserRole extends BaseEntity {

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
