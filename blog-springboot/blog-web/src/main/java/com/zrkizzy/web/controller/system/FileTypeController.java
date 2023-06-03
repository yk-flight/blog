package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.dto.FileTypeDTO;
import com.zrkizzy.data.vo.FileTypeVO;
import com.zrkizzy.server.service.common.IFileTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件分类控制器
 *
 * @author zhangrongkang
 * @since 2023/6/1
 */
@RestController
@RequestMapping("/admin/file-type")
public class FileTypeController {
    @Autowired
    private IFileTypeService fileTypeService;

    @ApiOperation("获取文件数据")
    @GetMapping("/list")
    public Result<List<FileTypeVO>> listFiles() {
        return Result.success(fileTypeService.listFileTypes());
    }

    @ApiOperation("添加-更新文件分类")
    @PostMapping("/save")
    public Result<?> save(@Validated @RequestBody FileTypeDTO fileTypeDTO) {
        return fileTypeService.save(fileTypeDTO) > 0 ? Result.success() : Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("获取指定文件分类")
    @GetMapping("/getFileTypeById/{id}")
    public Result<FileTypeVO> getFileTypeById(@PathVariable Long id) {
        return Result.success(BeanCopyUtil.copy(fileTypeService.getById(id), FileTypeVO.class));
    }

    @ApiOperation("删除指定文件分类")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return fileTypeService.delete(id);
    }

}
