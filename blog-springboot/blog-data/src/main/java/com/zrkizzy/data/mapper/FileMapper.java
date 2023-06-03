package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrkizzy.data.domain.File;

/**
 * 文件数据持久化接口
 *
 * @author zhangrongkang
 * @since 2023/5/12
 */
public interface FileMapper extends BaseMapper<File> {
    /**
     * 查询指定文件分类中的文件数量
     *
     * @param fileTypeId 文件分类ID
     * @return 文件数量
     */
    Integer countByFileTypeId(Long fileTypeId);
}
