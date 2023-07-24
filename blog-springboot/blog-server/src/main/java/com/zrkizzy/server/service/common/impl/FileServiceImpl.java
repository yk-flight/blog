package com.zrkizzy.server.service.common.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.File;
import com.zrkizzy.data.dto.FileDTO;
import com.zrkizzy.data.dto.UploadDTO;
import com.zrkizzy.data.mapper.FileMapper;
import com.zrkizzy.server.factory.FileUploadFactory;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.service.system.IConfigService;
import com.zrkizzy.server.template.AbstractFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.zrkizzy.common.constant.FileUploadConst.LOCAL_UPLOAD;
import static com.zrkizzy.common.constant.FileUploadConst.OSS_UPLOAD;

/**
 * 文件业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/5/12
 */
@Slf4j
@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private IConfigService configService;
    @Autowired
    private FileUploadFactory fileUploadFactory;
    @Autowired
    private SnowFlakeUtil snowFlakeUtil;
    @Autowired
    private FileMapper fileMapper;

    /**
     * 通过MD5查询文件分类对应文件
     *
     * @param md5        MD5哈希值
     * @param fileTypeId 文件分类ID
     * @return 对应文件对象
     */
    @Override
    public File getFileByMd5(String md5, Long fileTypeId) {
        // 查询并返回查询结果
        return fileMapper.selectOne(new QueryWrapper<File>()
                .eq("file_type_id", fileTypeId).eq("md5", md5));
    }

    /**
     * 保存文件数据
     *
     * @param fileDTO 文件数据传输对象
     * @return 受影响行数
     */
    @Override
    public Integer save(FileDTO fileDTO) {
        // 定义返回结果
        Integer result = null;
        // 复制文件对象
        File file = BeanCopyUtil.copy(fileDTO, File.class);
        // 根据ID判断更新或新增
        if (null == file.getId()) {
            // 新增
            result = insert(file);
        } else {
            // 更新
            result = update(file);
        }
        // 受影响的行数
        return result;
    }

    /**
     * 批量删除文件信息
     *
     * @param ids 文件ID集合
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        // 返回受影响行数与文件ID集合数量是否相同
        return fileMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 文本编辑器上传图片
     *
     * @param uploadDTO 文件数据传输对象
     * @return 图片访问路径
     */
    @Override
    public String addImage(UploadDTO uploadDTO) {
        // 获取文件上传策略
        String strategy = configService.getConfig().getUpload();
        // 根据上传策略获取对应上传方式
        return null;
    }

    /**
     * 批量删除文件
     *
     * @param fileList 文件集合
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(List<FileDTO> fileList) {
        if (CollectionUtil.isEmpty(fileList)) {
            throw new BusinessException(HttpStatusEnum.FILE_SELECT_ERROR);
        }
        // 将本地上传与OSS上传的文件进行区分
        List<FileDTO> ossFiles = new ArrayList<>();
        List<FileDTO> localFiles = new ArrayList<>();

        for (FileDTO fileDTO : fileList) {
            switch (fileDTO.getMode()) {
                // OSS上传
                case OSS_UPLOAD -> ossFiles.add(fileDTO);
                // 本地上传
                case LOCAL_UPLOAD ->  localFiles.add(fileDTO);
                // 默认
                default -> {}
            }
        }
        // 根据上传模式的不同调用不同的文件删除方法
        AbstractFileUpload ossFileUpload = fileUploadFactory.getInstance(OSS_UPLOAD);
        AbstractFileUpload localFileUpload = fileUploadFactory.getInstance(LOCAL_UPLOAD);
        // 返回删除结果
        return ossFileUpload.deleteFile(ossFiles) && localFileUpload.deleteFile(localFiles);
    }

    @Override
    public String upload(UploadDTO uploadDTO) throws IOException {
        // 获取文件上传策略
        String uploadStrategy = configService.getConfig().getUpload();
        // 根据上传模式获取对应的上传内容
        AbstractFileUpload fileUpload = fileUploadFactory.getInstance(uploadStrategy);
        // 上传文件并返回文件的访问域名
        return fileUpload.uploadFile(uploadDTO.getFile(), uploadDTO.getFileTypeId(), uploadStrategy);
    }

    /**
     * 更新文件信息
     *
     * @param file 文件对象
     * @return 受影响的行数
     */
    private Integer update(File file) {
        return fileMapper.updateById(file);
    }

    /**
     * 新增文件信息
     *
     * @param file 文件对象
     * @return 受影响的行数
     */
    private Integer insert(File file) {
        // ID
        file.setId(snowFlakeUtil.nextId());
        // 创建时间
        file.setCreateTime(LocalDateTime.now());
        // 更新时间
        file.setUpdateTime(LocalDateTime.now());
        return fileMapper.insert(file);
    }

}
