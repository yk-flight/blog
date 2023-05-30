package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件实体类
 *
 * @author zhangrongkang
 * @since 2023/5/11
 */
@Data
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
     * 文件存储路径
     */
    @ApiModelProperty("文件存储路径")
    private String path;

    /**
     * 文件访问路径
     */
    @ApiModelProperty("文件访问路径")
    private String src;

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
     * 文件MD5哈希值
     */
    @ApiModelProperty("文件MD5哈希值")
    private String md5;

    /**
     * 文件分类ID（在查询时不返回前端）
     */
    @ApiModelProperty("文件分类ID")
    private Long fileTypeId;

    /**
     * 上传用户ID
     */
    @ApiModelProperty("上传用户ID")
    private Long userId;

    /**
     * 上传模式
     */
    @ApiModelProperty("上传模式")
    private String mode;

    /**
     * 上传用户昵称
     */
    @ApiModelProperty("上传用户昵称")
    @TableField(exist = false)
    private String nickname;

}
