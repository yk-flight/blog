package com.zrkizzy.server.template.impl.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.zrkizzy.common.config.properties.OssProperties;
import com.zrkizzy.server.service.common.IFileTypeService;
import com.zrkizzy.server.template.AbstractFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private IFileTypeService fileTypeService;

    /**
     * 获取文件的Http访问路径
     *
     * @param typePath 文件分类路径
     * @param fileName 文件名称
     * @return 文件的访问路径
     */
    @Override
    public String getFileAccessPath(String typePath, String fileName) {
        return ossProperties.getDomain() + typePath + fileName;
    }

    /**
     * 获取文件的存储路径
     *
     * @param typePath 文件分类路径
     * @param fileName 文件名称
     * @return 文件的访问路径
     */
    @Override
    protected String getFileSavePath(String typePath, String fileName) {
        return typePath + fileName;
    }

    /**
     * 上传文件并返回文件访问路径
     *
     * @param file 文件对象
     * @param fileTypeId 文件分类ID
     * @param fileName 文件名称
     * @throws IOException IO异常
     * @return 文件路径集合：0: 文件存储路径 1: 文件访问路径
     */
    @Override
    public List<String> upload(MultipartFile file, Long fileTypeId, String fileName) throws IOException {
        // 存储文件访问路径与存储路径
        List<String> path = new ArrayList<>();
        // 创建OSS客户端
        OSS ossClient = getOssClient();
        // 根据文件分类ID获取到文件分类标识来定义不同的文件夹路径
        String typePath = fileTypeService.getPathByFileTypeId(fileTypeId);
        // 参数：仓库名称，文件名称，文件输入流
        ossClient.putObject(ossProperties.getBucketName(), typePath + fileName, file.getInputStream());
        // 关闭阿里云OSS客户端
        ossClient.shutdown();
        // 存储路径
        path.add(getFileSavePath(typePath, fileName));
        // 访问路径
        path.add(getFileAccessPath(typePath, fileName));
        return path;
    }

    /**
     * 删除文件
     *
     * @param keys 文件路径集合
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(List<String> keys) {
        // 获取OSS客户端
        OSS ossClient = getOssClient();
        // 创建删除请求
        DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(ossProperties.getBucketName());
        // 赋值需要删除的文件
        deleteRequest.setKeys(keys);
        // 调用删除
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteRequest);
        // 关闭OSS客户端
        ossClient.shutdown();
        // 返回删除结果
        return deleteObjectsResult.getDeletedObjects().size() == keys.size();
    }

    /**
     * 获取OSSClient操作对象
     *
     * @return OSSClient
     */
    private OSS getOssClient() {
        // 创建OSS客户端
        return new OSSClientBuilder().build(
                // 地域节点
                ossProperties.getEndpoint(),
                // 密钥ID
                ossProperties.getAccessKeyId(),
                // 密钥
                ossProperties.getAccessKeySecret());
    }


}
