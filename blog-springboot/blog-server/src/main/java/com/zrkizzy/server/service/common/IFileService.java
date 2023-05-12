package com.zrkizzy.server.service.common;

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
}
