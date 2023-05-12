package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.server.service.common.IFileService;
import com.zrkizzy.server.vo.FileTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取文件数据")
    @GetMapping("/list")
    public Result<List<FileTypeVO>> listFiles() {
        return Result.success(fileService.listFiles());
    }
}
