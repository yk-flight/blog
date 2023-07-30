package com.zrkizzy.web.controller.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Module;
import com.zrkizzy.data.dto.resource.ModuleDTO;
import com.zrkizzy.data.query.ModuleQuery;
import com.zrkizzy.data.vo.ModuleVO;
import com.zrkizzy.server.service.core.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请求模块控制器
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Api(tags = "请求模块控制器")
@RestController
@RequestMapping("/admin/module")
public class ModuleController {
    @Autowired
    private IModuleService moduleService;

    @ApiOperation("获取请求模块选项")
    @GetMapping("/listModuleOptions")
    public Result<List<OptionsVO>> listModuleOptions() {
        return Result.success(moduleService.listModuleOptions());
    }

    @ApiOperation("分页查询资源模块")
    @PostMapping("/list")
    public Result<PageResult<ModuleVO>> listModules(@RequestBody ModuleQuery moduleQuery) {
        Page<Module> modulePage = moduleService.listModules(moduleQuery);
        return Result.success(PageResult.<ModuleVO>builder()
                .list(BeanCopyUtil.copyList(modulePage.getRecords(), ModuleVO.class))
                .total(modulePage.getTotal())
                .build());
    }

    @ApiOperation("添加-更新资源模块")
    @PostMapping("/save")
    public Result<Boolean> saveModule(@RequestBody ModuleDTO moduleDTO) {
        // 保存资源模块数据
        return Result.success(moduleService.saveModule(moduleDTO));
    }

    @ApiOperation("获取指定资源模块信息")
    @GetMapping("/getModuleById/{moduleId}")
    public Result<ModuleVO> getModuleById (@PathVariable Long moduleId) {
        return Result.success(BeanCopyUtil.copy(moduleService.getModuleById(moduleId), ModuleVO.class));
    }

    @ApiOperation("批量删除资源模块数据")
    @DeleteMapping("/delete")
    @OperateLogAnnotation(type = AnnotationConst.DELETE)
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (moduleService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "资源模块数据删除失败");
    }

}
