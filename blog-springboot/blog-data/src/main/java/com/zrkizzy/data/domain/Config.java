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
 * @since 2023/7/22
 */
@Data
@TableName("tb_config")
@ApiModel(value="系统配置对象")
@EqualsAndHashCode(callSuper = false)
public class Config extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户默认头像
     */
    @ApiModelProperty(value = "用户默认头像")
    private String avatar;

}