package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ModuleRole implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

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

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
