package com.zrkizzy.server.service.common.impl;

import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.domain.FileType;
import com.zrkizzy.data.mapper.FileMapper;
import com.zrkizzy.data.mapper.FileTypeMapper;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.vo.FileTypeVO;
import com.zrkizzy.server.vo.FileVO;
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
        // 查询到文件数据
        List<FileType> fileTypes = fileTypeMapper.listFiles();
        // 复制查询到的文件数据
        List<FileTypeVO> fileList = BeanCopyUtil.copyList(fileTypes, FileTypeVO.class);
        // 将文件数据中的文件对象进行赋值
        for (int i = 0; i < fileList.size(); i++) {
            // 复制当前文件分类的具体文件数据
            List<FileVO> list = BeanCopyUtil.copyList(fileTypes.get(i).getFileList(), FileVO.class);
            // 赋值复制出的数据
            fileList.get(i).setFileList(list);
        }
        // 处理后的文件数据进行返回
        return fileList;
    }
}
