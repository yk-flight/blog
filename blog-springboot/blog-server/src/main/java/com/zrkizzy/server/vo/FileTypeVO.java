package com.zrkizzy.server.vo;

import com.zrkizzy.common.base.response.BaseVO;
import com.zrkizzy.data.domain.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 文件类型数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/5/11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FileTypeVO extends BaseVO {

    /**
     * 文件分类名称
     */
    private String name;

    /**
     * 文件分类描述
     */
    private String description;

    /**
     * 文件分类标识
     */
    private String mark;

    /**
     * 文件分类排序
     */
    private Integer sort;

    /**
     * 当前文件分类对应文件
     */
    private List<File> fileList;

}
