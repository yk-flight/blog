package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模块资源实体类
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("模块资源对象")
@TableName("tb_module_role")
public class ModuleRole extends BaseEntity {

    /**
     * 模块ID
     */
    @ApiModelProperty("模块ID")
    private Long moduleId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long roleId;

}
