package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.*;

/**
 * 文件数据传递对象
 *
 * @author zhangrongkang
 * @since 2023/5/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FileDTO extends BaseDTO {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件访问路径
     */
    private String src;

    /**
     * 文件访问路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件MD5哈希值
     */
    private String md5;

    /**
     * 文件分类ID（在查询时不返回前端）
     */
    private Long fileTypeId;

    /**
     * 上传用户ID
     */
    private Long userId;

    /**
     * 上传模式
     */
    private String mode;

}
