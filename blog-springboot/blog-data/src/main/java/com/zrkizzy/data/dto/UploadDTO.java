package com.zrkizzy.data.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文件上传数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/5/29
 */
@Data
public class UploadDTO implements Serializable {

    /**
     * 上传模式
     */
    @NotBlank(message = "上传模式不能为空")
    private String mode;

    /**
     * 文件分类ID
     */
    @NotNull(message = "文件分类不能为空")
    private Long fileTypeId;

    /**
     * 上传文件
     */
    @NotNull(message = "文件不能为空")
    private MultipartFile file;

}
