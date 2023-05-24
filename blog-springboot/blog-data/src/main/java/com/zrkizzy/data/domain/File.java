package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件实体类
 *
 * @author zhangrongkang
 * @since 2023/5/11
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("文件对象")
@TableName("tb_file")
public class File extends BaseEntity {

    /**
     * 文件名称
     */
    @ApiModelProperty("文件名称")
    private String name;

    /**
     * 文件路径
     */
    @ApiModelProperty("文件路径")
    private String path;

    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Long size;

    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String type;

    /**
     * 文件分类ID（在查询时不返回前端）
     */
    @ApiModelProperty("文件分类ID")
    private Long fileTypeId;

    /**
     * 上传人
     */
    @ApiModelProperty("上传人")
    private String creator;

}
