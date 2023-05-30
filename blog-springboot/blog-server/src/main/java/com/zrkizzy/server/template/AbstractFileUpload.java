package com.zrkizzy.server.template;

import cn.hutool.core.collection.CollectionUtil;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.utils.FileUtil;
import com.zrkizzy.common.utils.TimeUtil;
import com.zrkizzy.data.dto.FileDTO;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.common.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * @param typePath 文件分类路径
     * @param filePath 文件存储路径
     * @return 文件的访问路径
     */
    protected abstract String getFileAccessPath(String typePath, String filePath);


    /**
     * 获取文件的存储路径
     *
     * @param typePath 文件分类路径
     * @param filePath 文件存储路径
     * @return 文件的访问路径
     */
    protected abstract String getFileSavePath(String typePath, String filePath);

    /**
     * 上传文件并返回文件访问路径
     *
     * @param file 文件对象
     * @param fileTypeId 文件分类ID
     * @param fileName 文件名称
     * @throws IOException IO异常
     * @return 文件路径集合：0: 文件存储路径 1: 文件访问路径
     */
    protected abstract List<String> upload(MultipartFile file, Long fileTypeId, String fileName) throws IOException;

    /**
     * 删除文件
     *
     * @param filePath 文件路径集合
     * @return 是否删除成功
     */
    protected abstract Boolean delete(List<String> filePath);

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
    public String uploadFile(MultipartFile file, Long fileTypeId, String mode) throws IOException {
        // 判断当前文件是否已经存在
        if (isExist(file, fileTypeId)) {
            // 抛出重复上传异常
            throw new BusinessException(FILE_EXIST_ERROR);
        }
        // 根据当前时间和文件扩展名生成新的文件名称
        String fileName = TimeUtil.generateNowTime() + FileUtil.getFileExtName(file.getOriginalFilename());
        // 上传文件并返回文件的访问路径
        List<String> paths = upload(file, fileTypeId, fileName);

        // 保存当前文件数据到数据库中
        fileService.save(FileDTO.builder()
                // 文件所属分类
                .fileTypeId(fileTypeId)
                // 文件名称
                .name(fileName)
                // 文件存储路径
                .path(paths.get(0))
                // 文件访问路径
                .src(paths.get(1))
                // 文件MD5哈希值，用于文件唯一标识
                .md5(FileUtil.getFileMd5(file.getInputStream()))
                // 文件类型
                .type(FileUtil.getFileExtName(file.getOriginalFilename()))
                // 上传用户ID
                .userId(securityUtil.getLoginUser().getId())
                // 上传模式
                .mode(mode)
                // 文件大小
                .size(getFileSize(file)).build());

        // 返回文件的访问路径
        return paths.get(1);
    }

    /**
     * 批量删除文件
     *
     * @param fileList 文件数据传输对象
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteFile(List<FileDTO> fileList) {
        // 如果传来的文件列表为空则直接返回true
        if (CollectionUtil.isEmpty(fileList)) {
            return Boolean.TRUE;
        }
        // 图片ID和图片路径集合
        List<Long> ids = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        // 获取到传输的图片ID和图片存储路径集合
        for (FileDTO fileDTO : fileList) {
            ids.add(fileDTO.getId());
            paths.add(fileDTO.getPath());
        }
        // 删除数据库文件数据以及删除文件
        return fileService.deleteBatch(ids) && delete(paths);
    }

}
