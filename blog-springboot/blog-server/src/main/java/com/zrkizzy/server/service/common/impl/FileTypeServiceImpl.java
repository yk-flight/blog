package com.zrkizzy.server.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.CommonConst;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.FileType;
import com.zrkizzy.data.dto.FileTypeDTO;
import com.zrkizzy.data.mapper.FileMapper;
import com.zrkizzy.data.mapper.FileTypeMapper;
import com.zrkizzy.data.vo.FileTypeVO;
import com.zrkizzy.data.vo.FileVO;
import com.zrkizzy.server.service.common.IFileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.zrkizzy.common.constant.FileUploadConst.FILE_SEPARATOR;

/**
 * 文件分类业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/6/1
 */
@Service
public class FileTypeServiceImpl implements IFileTypeService {
    @Autowired
    private FileTypeMapper fileTypeMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    /**
     * 获取文件列表数据
     *
     * @return 文件列表数据
     */
    @Override
    public List<FileTypeVO> listFileTypes() {
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

    /**
     * 根据文件类型ID获取对应的分类路径
     *
     * @param fileTypeId 文件分类ID
     * @return 文件夹路径
     */
    @Override
    public String getPathByFileTypeId(Long fileTypeId) {
        return fileTypeMapper.getPathByFileTypeId(fileTypeId) + FILE_SEPARATOR;
    }

    /**
     * 新增-更新文件分类
     *
     * @param fileTypeDTO 文件分类数据传输对象
     * @return 受影响的行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(FileTypeDTO fileTypeDTO) {
        // 根据ID情况判断是否
        if (null != fileTypeDTO.getId()) {
            // 更新文件分类操作
            return update(fileTypeDTO);
        } else {
            // 新增文件分类操作
            return insert(fileTypeDTO);
        }
    }

    /**
     * 根据ID获取文件分类对象
     *
     * @param id ID
     * @return 文件分类对象
     */
    @Override
    public FileType getById(Long id) {
        return fileTypeMapper.selectById(id);
    }

    /**
     * 删除指定文件分类
     *
     * @param id 文件分类ID
     * @return 前端响应对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> delete(Long id) {
        // 判断当前文件分类下是否含有文件
        if (isNotEmpty(id)) {
            return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "当前文件分类中存在文件");
        }
        // 删除对应文件分类并返回响应结果
        return fileTypeMapper.deleteById(id) > 0 ? Result.success() : Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 新增文件分类
     *
     * @param fileTypeDTO 文件分类数据传输对象
     * @return 受影响的行数
     */
    private Integer insert(FileTypeDTO fileTypeDTO) {
        // 查找sort值大于当前对象的集合
        Integer count = fileTypeMapper.countByGreaterSort(fileTypeDTO.getSort());
        // 如果不是插入最后一位
        if (count > 0) {
            // 将当前位置的元素以及之后位置的元素全部向后移动
            fileTypeMapper.updateBySort(fileTypeDTO.getSort());
        }
        // ID
        fileTypeDTO.setId(snowFlakeUtil.nextId());
        // 创建时间和更新时间
        fileTypeDTO.setCreateTime(LocalDateTime.now());
        fileTypeDTO.setUpdateTime(LocalDateTime.now());
        // 插入数据
        return fileTypeMapper.insert(BeanCopyUtil.copy(fileTypeDTO, FileType.class));
    }

    /**
     * 更新文件分类<br />
     * <p>如果当前分类下存在文件则不允许更新</p>
     *
     * @param fileTypeDTO 文件分类数据传输对象
     * @return 受影响的行数
     */
    private Integer update(FileTypeDTO fileTypeDTO) {
        // 获取未更新的文件分类对象
        FileType fileType = fileTypeMapper.selectById(fileTypeDTO.getId());
        // 如果修改文件分类标识并且文件分类包含文件
        if (!fileTypeDTO.getMark().equals(fileType.getMark()) && isNotEmpty(fileTypeDTO.getId())) {
            // 直接返回受影响行数为0
            return CommonConst.FALSE;
        }
        // 如果要更新文件排序
        if (!fileType.getSort().equals(fileTypeDTO.getSort())) {
            // 根据新的排序值查询原本对应的文件对象
            FileType sortFileType = fileTypeMapper.selectOne(new QueryWrapper<FileType>()
                    .eq("sort", fileTypeDTO.getSort()));
            // 前提已经存在对应位置排序位置的文件分类，否则直接更新文件分类排序的位置
            if (null != sortFileType) {
                // 为文件分类对象赋值排序
                sortFileType.setSort(fileType.getSort());
                fileTypeMapper.updateById(sortFileType);
            }
        }
        // 更新文件分类对象
        return fileTypeMapper.updateById(BeanCopyUtil.copy(fileTypeDTO, FileType.class));
    }

    /**
     * 校验当前文件目录下是不否包含文件
     *
     * @param fileTypeId 文件分类对象
     * @return true：包含文件；false：不包含文件
     */
    private Boolean isNotEmpty(Long fileTypeId) {
        // 根据文件分类ID去查询文件表中是否含有数据
        return fileMapper.countByFileTypeId(fileTypeId) > 0;
    }

}
