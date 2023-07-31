package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模块实体类
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("模块对象")
@TableName("tb_module")
public class Module extends BaseEntity {

    /**
     * 模块名称
     */
    @ApiModelProperty("模块名称")
    private String name;

    /**
     * 模块描述
     */
    @ApiModelProperty("模块描述")
    private String description;

}
