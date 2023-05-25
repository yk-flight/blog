package com.zrkizzy.server.template;

import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.utils.FileUtil;
import com.zrkizzy.common.utils.TimeUtil;
import com.zrkizzy.data.domain.File;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.common.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.zrkizzy.common.enums.HttpStatusEnum.FILE_EXIST_ERROR;

/**
 * 文件上传模板类
 *
 * @author zhangrongkang
 * @since 2023/5/23
 */
@Component
public abstract class AbstractFileUpload {
    @Autowired
    private IFileService fileService;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 获取文件的Http访问路径
     *
     * @param filePath 文件存储路径
     * @return 文件的访问路径
     */
    public abstract String getFileAccessPath(String filePath);

    /**
     * 上传文件并返回文件访问路径
     *
     * @param file 文件对象
     * @param fileTypeId 文件分类ID
     * @param fileName 文件名称
     * @throws IOException IO异常
     * @return 文件访问路径
     */
    public abstract String upload(MultipartFile file, Long fileTypeId, String fileName) throws IOException;

    /**
     * 获取文件大小（动态确定）
     *
     * @param file 文件对象
     * @return 文件大小
     */
    public Long getFileSize(MultipartFile file) {
        return file.getSize();
    }

    /**
     * 查看当前文件是否已存在<br/>
     *
     * <p>检测当前文件是否已经在服务器上传过，避免重复上传</p>
     *
     * @param file 上传文件
     * @param fileTypeId 文件分类ID
     * @return true：已存在，false：不存在
     */
    public Boolean isExist(MultipartFile file, Long fileTypeId) {
        // 定义文件输入流对象
        try {
            // 计算出文件的哈希值
            String md5 = FileUtil.getFileMd5(file.getInputStream());
            // 根据MD5查询对应文件分类下是否有相同的文件
            return null != fileService.getFileByMd5(md5, fileTypeId);
        } catch (IOException e) {
            throw new BusinessException(HttpStatusEnum.FILE_UPLOAD_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public String uploadFile(MultipartFile file, Long fileTypeId) throws IOException {
        // 判断当前文件是否已经存在
        if (isExist(file, fileTypeId)) {
            // 抛出重复上传异常
            throw new BusinessException(FILE_EXIST_ERROR);
        }
        // 根据当前时间和文件扩展名生成新的文件名称
        String fileName = TimeUtil.generateNowTime() + "." + FileUtil.getFileExtName(file.getName());
        // 上传文件并返回文件的访问路径
        String accessPath = upload(file, fileTypeId, fileName);

        // 保存当前文件数据到数据库中
        fileService.save(File.builder()
                // 文件所属分类
                .fileTypeId(fileTypeId)
                // 文件名称
                .name(fileName)
                // 文件访问路径
                .path(accessPath)
                // 文件MD5哈希值，用于文件唯一标识
                .md5(FileUtil.getFileMd5(file.getInputStream()))
                // 文件类型
                .type(FileUtil.getFileExtName(file.getName()))
                // 上传用户（当前登录用户）
                .creator(securityUtil.getLoginUsername())
                // 文件大小
                .size(getFileSize(file)).build());
        // 返回文件的访问路径
        return accessPath;
    }

}
