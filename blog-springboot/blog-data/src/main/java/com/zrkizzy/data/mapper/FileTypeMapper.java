package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.FileType;

import java.util.List;

/**
 * 文件分类数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/5/12
 */
public interface FileTypeMapper extends BaseMapper<FileType> {
    /**
     * 查询文件数据树
     *
     * @return 文件数据
     */
    List<FileType> listFiles();
}