package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 资源实体类
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@TableName("tb_resource")
@ApiModel(value="资源对象")
@EqualsAndHashCode(callSuper = false)
public class Resource extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 资源描述
     */
    @ApiModelProperty(value = "资源描述")
    private String description;

    /**
     * 资源请求方式
     */
    @ApiModelProperty(value = "资源请求方式")
    private String method;

    /**
     * 资源请求路径
     */
    @ApiModelProperty(value = "资源请求路径")
    private String url;

}