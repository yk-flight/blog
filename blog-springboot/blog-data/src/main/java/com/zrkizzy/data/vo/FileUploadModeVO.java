package com.zrkizzy.data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传模式返回对象
 *
 * @author zhangrongkang
 * @since 2023/5/23
 */
@Data
@Builder
@AllArgsConstructor
public class FileUploadModeVO implements Serializable {

    /**
     * 上传模式
     */
    private String mode;

    /**
     * 上传标识
     */
    private String mark;

}
