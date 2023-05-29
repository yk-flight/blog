package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.FileUploadModeEnum;
import com.zrkizzy.data.dto.UploadDTO;
import com.zrkizzy.data.vo.FileTypeVO;
import com.zrkizzy.data.vo.FileUploadModeVO;
import com.zrkizzy.server.factory.FileUploadFactory;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.template.AbstractFileUpload;
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
    @Autowired
    private FileUploadFactory fileUploadFactory;

    @ApiOperation("获取文件数据")
    @GetMapping("/list")
    public Result<List<FileTypeVO>> listFiles() {
        return Result.success(fileService.listFileTypes());
    }

    @ApiOperation("获取文件上传模式")
    @GetMapping("/listModes")
    public Result<List<FileUploadModeVO>> listModes() {
        List<FileUploadModeVO> list = new ArrayList<>();
        // 遍历所有文件上传类型枚举
        for (FileUploadModeEnum fileUploadModeEnum : FileUploadModeEnum.listFileUploadMode()) {
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
        // 根据上传模式获取对应的上传内容
        AbstractFileUpload fileUpload = fileUploadFactory.getInstance(uploadDTO.getMode());
        // 上传文件并返回文件的访问域名
        String accessPath = fileUpload.uploadFile(uploadDTO.getFile(), uploadDTO.getFileTypeId());
        // 返回结果
        return Result.success(accessPath);
    }
}
