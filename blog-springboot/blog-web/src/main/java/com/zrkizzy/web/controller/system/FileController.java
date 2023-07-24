package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.enums.file.FileUploadModeEnum;
import com.zrkizzy.data.dto.FileDTO;
import com.zrkizzy.data.dto.UploadDTO;
import com.zrkizzy.data.vo.FileUploadModeVO;
import com.zrkizzy.server.service.common.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件控制器
 *
 * @author zhangrongkang
 * @since 2023/5/11
 */
@Api(tags = "文件控制器")
@RestController
@RequestMapping("/admin/file")
public class FileController {
    @Autowired
    private IFileService fileService;

    @ApiOperation("获取文件上传模式")
    @GetMapping("/listModes")
    public Result<List<FileUploadModeVO>> listModes() {
        List<FileUploadModeVO> list = new ArrayList<>();
        // 遍历所有文件上传类型枚举
        for (FileUploadModeEnum fileUploadModeEnum : FileUploadModeEnum.values()) {
            // 文件上传模式返回对象
            FileUploadModeVO fileUploadModeVO = FileUploadModeVO.builder()
                    // 上传模式
                    .mode(fileUploadModeEnum.getMode())
                    // 模式标识
                    .mark(fileUploadModeEnum.getMark()).build();
            // 添加到集合
            list.add(fileUploadModeVO);
        }
        return Result.success(list);
    }

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result<String> upload(@Validated @ModelAttribute UploadDTO uploadDTO) throws IOException {
        // 上传文件并返回结果
        return Result.success(fileService.upload(uploadDTO));
    }

    @ApiOperation("批量删除文件")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<FileDTO> fileList) {
        // 批量删除文件并返回删除结果
        return fileService.delete(fileList) ? Result.success() : Result.failure(HttpStatusEnum.FILE_DELETE_ERROR);
    }

    @ApiOperation("编辑器上传图片")
    @PostMapping("/addImage")
    public Result<String> addImage(@Validated @ModelAttribute UploadDTO uploadDTO) throws IOException {
        String imageUrl = fileService.addImage(uploadDTO);
        return Result.success(imageUrl);
    }
}
