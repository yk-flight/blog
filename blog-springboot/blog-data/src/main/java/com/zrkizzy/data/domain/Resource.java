package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源实体类
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("资源对象")
@TableName("tb_resource")
public class Resource extends BaseEntity {

    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String name;

    /**
     * 资源描述
     */
    @ApiModelProperty("资源描述")
    private String description;

    /**
     * 资源所属模块ID
     */
    @ApiModelProperty("资源所属模块ID")
    private Long moduleId;

    /**
     * 资源请求方式
     */
    @ApiModelProperty("资源请求方式")
    private String method;

    /**
     * 资源请求路径
     */
    @ApiModelProperty("资源请求路径")
    private String url;

}
