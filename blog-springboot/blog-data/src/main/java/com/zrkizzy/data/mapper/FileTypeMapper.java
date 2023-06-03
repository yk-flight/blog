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

    /**
     * 根据文件类型ID获取对应的分类路径
     *
     * @param fileTypeId 文件分类ID
     * @return 文件夹路径
     */
    String getPathByFileTypeId(Long fileTypeId);

    /**
     * 获取大于指定排序值条数
     *
     * @param sort 排序值
     * @return 数据条数
     */
    Integer countByGreaterSort(Integer sort);

    /**
     * 将大于指定排序值的元素的排序值进行向后移动操作
     *
     * @param sort 排序值
     * @return 受影响的行数
     */
    Integer updateBySort(Integer sort);

    /**
     * 获取修改前文件分类的文件标识
     *
     * @param id 文件分类ID
     * @return 文件标识
     */
    String selectMarkById(Long id);
}
