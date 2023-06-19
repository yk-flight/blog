package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serial;

/**
 * 友情链接实体类
 *
 * @author zhangrongkang
 * @since 2023/6/19
 */
@Data
@TableName("tb_link")
@ApiModel(value="友情链接对象")
@EqualsAndHashCode(callSuper = false)
public class Link extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 网站名称
     */
    @ApiModelProperty(value = "网站名称")
    private String name;

    /**
     * 网站域名
     */
    @ApiModelProperty(value = "网站域名")
    private String website;

    /**
     * 网站Logo
     */
    @ApiModelProperty(value = "网站Logo")
    private String logo;

    /**
     * 网站介绍
     */
    @ApiModelProperty(value = "网站介绍")
    private String introduce;

}