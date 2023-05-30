package com.zrkizzy.server.template.impl.upload;

import com.zrkizzy.server.template.AbstractFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
     * 获取文件的存储路径
     *
     * @param typePath 文件分类路径
     * @param filePath 文件存储路径
     * @return 文件的访问路径
     */
    @Override
    protected String getFileSavePath(String typePath, String filePath) {
        return null;
    }

    /**
     * 上传文件并返回文件访问路径
     *
     * @param file       文件对象
     * @param fileTypeId 文件分类ID
     * @param fileName   文件名称
     * @return 文件路径集合：0: 文件存储路径 1: 文件访问路径
     * @throws IOException IO异常
     */
    @Override
    protected List<String> upload(MultipartFile file, Long fileTypeId, String fileName) throws IOException {
        return null;
    }


    /**
     * 删除文件
     *
     * @param filePath 文件路径集合
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(List<String> filePath) {
        return true;
    }


}
