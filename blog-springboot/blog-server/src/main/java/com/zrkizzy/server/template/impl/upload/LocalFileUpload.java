package com.zrkizzy.server.template.impl.upload;

import com.zrkizzy.server.template.AbstractFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件上传实现类
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
@Service("localFileUpload")
public class LocalFileUpload extends AbstractFileUpload {

    /**
     * 获取文件的Http访问路径
     *
     * @param typePath 文件分类路径
     * @param filePath 文件存储路径
     * @return 文件的访问路径
     */
    @Override
    public String getFileAccessPath(String typePath, String filePath) {
        return null;
    }

    /**
     * 上传文件并返回文件访问路径
     *
     * @param file       文件对象
     * @param fileTypeId 文件分类ID
     * @param fileName   文件名称
     * @return 文件访问路径
     */
    @Override
    public String upload(MultipartFile file, Long fileTypeId, String fileName) {
        return null;
    }

}
