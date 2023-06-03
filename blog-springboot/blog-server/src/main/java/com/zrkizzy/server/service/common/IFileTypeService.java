package com.zrkizzy.server.service.common;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.FileType;
import com.zrkizzy.data.dto.FileTypeDTO;
import com.zrkizzy.data.vo.FileTypeVO;

import java.util.List;

/**
 * 文件分类业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/6/1
 */
public interface IFileTypeService {

    /**
     * 获取文件列表数据
     *
     * @return 文件列表数据
     */
    List<FileTypeVO> listFileTypes();

    /**
     * 根据文件类型ID获取对应的分类路径
     *
     * @param fileTypeId 文件分类ID
     * @return 文件夹路径
     */
    String getPathByFileTypeId(Long fileTypeId);

    /**
     * 新增-更新文件分类
     *
     * @param fileTypeDTO 文件分类数据传输对象
     * @return 受影响的行数
     */
    Integer save(FileTypeDTO fileTypeDTO);

    /**
     * 根据ID获取文件分类对象
     *
     * @param id ID
     * @return 文件分类对象
     */
    FileType getById(Long id);

    /**
     * 删除指定文件分类
     *
     * @param id 文件分类ID
     * @return 前端响应对象
     */
    Result<?> delete(Long id);
}
