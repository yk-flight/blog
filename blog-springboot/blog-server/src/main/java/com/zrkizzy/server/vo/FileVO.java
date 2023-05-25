package com.zrkizzy.server.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/5/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FileVO extends BaseVO {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * MD5哈希值（根据文件内容计算出，用于文件唯一标识）
     */
    private String md5;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 上传人
     */
    private String creator;

}
