package com.zrkizzy.server.template.impl.upload;

import com.zrkizzy.common.config.properties.LocalProperties;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.template.AbstractFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 本地文件上传实现类
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
@Service("localFileUpload")
public class LocalFileUpload extends AbstractFileUpload {
    @Autowired
    private IFileService fileService;
    @Autowired
    private LocalProperties localProperties;

    /**
     * 获取文件的Http访问路径
     *
     * @param typePath 文件分类路径
     * @param fileName 文件名称
     * @return 文件的访问路径
     */
    @Override
    public String getFileAccessPath(String typePath, String fileName) {
        return localProperties.getDomain() + typePath + fileName;
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
        return localProperties.getPath() + typePath + fileName;
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
        // 文件分类路径
        String typePath = fileService.getPathByFileTypeId(fileTypeId);
        // 文件存储全路径
        String fileSavePath = getFileSavePath(typePath, fileName);
        // 文件的访问路径
        String fileAccessPath = getFileAccessPath(typePath, fileName);
        // 目录路径
        Path directory = Paths.get(localProperties.getPath() + typePath);
        // 判断当前文件目录是否存在，不存在则创建
        if (Files.notExists(directory)) {
            // 创建文件目录
            Files.createDirectory(directory);
        }
        // 转存当前文件
        file.transferTo(new File(fileSavePath));
        // 返回文件存储路径和文件访问路径
        return Arrays.asList(fileSavePath, fileAccessPath);
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径集合
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(List<String> filePath) {
        // 文件删除结果
        int result = 0;
        // 遍历文件路径集合
        for (String path : filePath) {
            File file = new File(path);
            // 删除结果
            if (file.delete()) {
                result++;
            }
        }
        // 删除成功数量与集合大小相同则认为删除成功
        return result == filePath.size();
    }


}
