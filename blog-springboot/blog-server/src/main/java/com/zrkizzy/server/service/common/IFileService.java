package com.zrkizzy.server.service.common;

import com.zrkizzy.data.domain.File;
import com.zrkizzy.data.dto.FileDTO;

import java.util.List;

/**
 * 文件业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/5/12
 */
public interface IFileService {

    /**
     * 通过MD5查询文件分类对应文件
     *
     * @param md5 MD5哈希值
     * @param fileTypeId 文件分类ID
     * @return 对应文件对象
     */
    File getFileByMd5(String md5, Long fileTypeId);

    /**
     * 保存文件数据
     *
     * @param fileDTO 文件数据传输对象
     * @return 受影响行数
     */
    Integer save(FileDTO fileDTO);

    /**
     * 批量删除文件信息
     *
     * @param ids 文件ID集合
     * @return 是否删除成功
     */
    Boolean deleteBatch(List<Long> ids);
}
