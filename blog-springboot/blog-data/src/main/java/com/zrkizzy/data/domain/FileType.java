package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件分类实体类
 *
 * @author zhangrongkang
 * @since 2023/5/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("文件分类对象")
@TableName("tb_file_type")
public class FileType extends BaseEntity {

    /**
     * 文件分类名称
     */
    @ApiModelProperty("文件分类名称")
    private String name;

    /**
     * 文件分类描述
     */
    @ApiModelProperty("文件分类描述")
    private String description;

    /**
     * 文件分类排序
     */
    @ApiModelProperty("文件分类排序")
    private Integer sort;

}
