package com.zrkizzy.web.controller.system;

import cn.hutool.core.collection.CollectionUtil;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.FileUploadModeEnum;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.data.dto.FileDTO;
import com.zrkizzy.data.dto.UploadDTO;
import com.zrkizzy.data.vo.FileUploadModeVO;
import com.zrkizzy.server.factory.FileUploadFactory;
import com.zrkizzy.server.template.AbstractFileUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.zrkizzy.common.constant.FileUploadConst.LOCAL_UPLOAD;
import static com.zrkizzy.common.constant.FileUploadConst.OSS_UPLOAD;

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
    private FileUploadFactory fileUploadFactory;

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
        String accessPath = fileUpload.uploadFile(uploadDTO.getFile(), uploadDTO.getFileTypeId(), uploadDTO.getMode());
        // 返回结果
        return Result.success(accessPath);
    }

    @ApiOperation("批量删除文件")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<FileDTO> fileList) {
        if (CollectionUtil.isEmpty(fileList)) {
            return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "请选择要删除的文件");
        }
        // 将本地上传与OSS上传的文件进行区分
        List<FileDTO> ossFiles = new ArrayList<>();
        List<FileDTO> localFiles = new ArrayList<>();

        for (FileDTO fileDTO : fileList) {
            switch (fileDTO.getMode()) {
                // OSS上传
                case OSS_UPLOAD -> ossFiles.add(fileDTO);
                // 本地上传
                case LOCAL_UPLOAD ->  localFiles.add(fileDTO);
                // 默认
                default -> {}
            }
        }
        // 根据上传模式的不同调用不同的文件删除方法
        AbstractFileUpload ossFileUpload = fileUploadFactory.getInstance(OSS_UPLOAD);
        AbstractFileUpload localFileUpload = fileUploadFactory.getInstance(LOCAL_UPLOAD);
        // 返回删除结果
        Boolean ossResult = ossFileUpload.deleteFile(ossFiles);
        Boolean localResult = localFileUpload.deleteFile(localFiles);
        if (ossResult && localResult) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "文件删除失败");
    }
}
