package com.zrkizzy.server.factory;

import com.zrkizzy.server.template.AbstractFileUpload;
import com.zrkizzy.server.template.impl.upload.LocalFileUpload;
import com.zrkizzy.server.template.impl.upload.OssFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zrkizzy.common.constant.FileUploadConst.*;

/**
 * 文件上传工厂
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
@Component
public class FileUploadFactory {
    @Autowired
    private OssFileUpload ossFileUpload;
    @Autowired
    private LocalFileUpload localFileUpload;

    /**
     * 根据不同的上传模式返回不同的类型的上传实例
     *
     * @param mode 上传模式
     * @return 文件上传对象
     */
    public AbstractFileUpload getInstance(String mode) {
        AbstractFileUpload fileUpload = null;
        // 根据类型判断注入哪种实例
        fileUpload = switch (mode) {
            // OSS对象存储
            case OSS_UPLOAD -> ossFileUpload;
            // 本地上传
            case LOCAL_UPLOAD -> localFileUpload;
            // 默认
            default -> null;
        };
        // 返回上传实例
        return fileUpload;
    }
}
