package com.zrkizzy.server.template.impl.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zrkizzy.common.config.OssProperties;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.template.AbstractFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * OSS文件上传实现类
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
@Service("ossFileUpload")
public class OssFileUpload extends AbstractFileUpload {
    @Autowired
    private OssProperties ossProperties;
    @Autowired
    private IFileService fileService;

    /**
     * 获取文件的Http访问路径
     *
     * @param filePath 文件存储路径
     * @return 文件的访问路径
     */
    @Override
    public String getFileAccessPath(String filePath) {
        return ossProperties.getDomain() + filePath;
    }

    /**
     * 上传文件并返回文件访问路径
     *
     * @param file       文件对象
     * @param fileTypeId 文件分类ID
     * @param fileName 文件名称
     * @return 文件访问路径
     */
    @Override
    public String upload(MultipartFile file, Long fileTypeId, String fileName) throws IOException {
        // 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(
                // 地域节点
                ossProperties.getEndpoint(),
                // 密钥ID
                ossProperties.getAccessKeyId(),
                // 密钥
                ossProperties.getAccessKeySecret());
        // TODO 根据文件分类ID获取到文件分类标识来定义不同的文件夹路径
        String typePath = fileService.getPathByFileTypeId(fileTypeId);
        // 参数：仓库名称，文件名称，文件输入流
        ossClient.putObject(ossProperties.getBucketName(), fileName, file.getInputStream());
        // 关闭阿里云OSS客户端
        ossClient.shutdown();
        // 返回文件的访问路径
        return getFileAccessPath(fileName);
    }


}
