package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 文件分类数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/6/1
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FileTypeDTO extends BaseDTO {

    /**
     * 文件分类名称
     */
    @NotBlank(message = "文件分类名称不能为空")
    private String name;

    /**
     * 文件分类标识
     */
    @NotBlank(message = "文件分类标识不能为空")
    private String mark;

    /**
     * 文件分类排序
     */
    private Integer sort;

    /**
     * 文件分类描述
     */
    private String description;

}
