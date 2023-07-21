package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 系统配置实体类
 *
 * @author zhangrongkang
 * @since 2023/7/21
 */
@Data
@TableName("tb_config")
@ApiModel(value="系统配置对象")
@EqualsAndHashCode(callSuper = false)
public class Config extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置名称
     */
    @ApiModelProperty(value = "配置名称")
    private String configName;

    /**
     * 配置描述
     */
    @ApiModelProperty(value = "配置描述")
    private String configDesc;

    /**
     * 配置值
     */
    @ApiModelProperty(value = "配置值")
    private String configValue;

    /**
     * 是否为系统内置：1 是，0 否
     */
    @ApiModelProperty(value = "是否为系统内置：1 是，0 否")
    private Boolean configBuild;

    /**
     * 配置类型： IMAGE 图片，TEXT 文本，RADIO 单选
     */
    @ApiModelProperty(value = "配置类型： IMAGE 图片，TEXT 文本，RADIO 单选")
    private String configType;

}