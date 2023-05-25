package com.zrkizzy.server.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 文件上传模式返回对象
 *
 * @author zhangrongkang
 * @since 2023/5/23
 */
@Data
@Builder
@AllArgsConstructor
public class FileUploadModeVO {

    /**
     * 上传模式
     */
    private String mode;

    /**
     * 上传标识
     */
    private String mark;

}
