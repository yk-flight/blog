package com.zrkizzy.server.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.File;
import com.zrkizzy.data.dto.FileDTO;
import com.zrkizzy.data.mapper.FileMapper;
import com.zrkizzy.server.service.common.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
     * 更新文件信息
     *
     * @param file 文件对象
     * @return 受影响的行数
     */
    private Integer update(File file) {
        // 更新时间
        file.setUpdateTime(LocalDateTime.now());
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
