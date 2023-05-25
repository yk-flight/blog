package com.zrkizzy.server.service.common;

import com.zrkizzy.data.domain.File;
import com.zrkizzy.server.vo.FileTypeVO;

import java.util.List;

/**
 * 文件业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/5/12
 */
public interface IFileService {

    /**
     * 获取文件列表数据
     *
     * @return 文件列表数据
     */
    List<FileTypeVO> listFiles();

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
     * @param file 文件对象
     * @return 受影响行数
     */
    Integer save(File file);
}
