package com.zrkizzy.server.service.common.impl;

import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.mapper.FileMapper;
import com.zrkizzy.data.mapper.FileTypeMapper;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.vo.FileTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private FileMapper fileMapper;

    @Autowired
    private FileTypeMapper fileTypeMapper;

    /**
     * 获取文件列表数据
     *
     * @return 文件列表数据
     */
    @Override
    public List<FileTypeVO> listFiles() {
        // 查询文件数据并返回
        return BeanCopyUtil.copyList(fileTypeMapper.listFiles(), FileTypeVO.class);
    }
}
