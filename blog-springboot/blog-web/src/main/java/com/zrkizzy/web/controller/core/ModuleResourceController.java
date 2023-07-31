package com.zrkizzy.web.controller.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.data.dto.resource.ModuleResourceDTO;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.resource.ResourceVO;
import com.zrkizzy.data.vo.resource.ModuleResourceVO;
import com.zrkizzy.data.vo.resource.ResourceTreeVO;
import com.zrkizzy.server.service.core.IModuleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源模块关联数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Api(tags = "资源模块关联控制器")
@RestController
@RequestMapping("/admin/module-resource")
public class ModuleResourceController {

     @Autowired
     private IModuleResourceService moduleResourceService;

     @ApiOperation("分页获取指定模块请求资源")
     @PostMapping ("/list")
     public Result<PageResult<ResourceVO>> list(@Validated @RequestBody ModuleResourceQuery moduleResourceQuery) {
          Page<ResourceVO> resourcePage = moduleResourceService.listByModuleId(moduleResourceQuery);
          return Result.success(PageResult.<ResourceVO>builder()
                  .total(resourcePage.getTotal())
                  .list(resourcePage.getRecords()).build());
     }

     @ApiOperation("获取指定模块可以添加的接口")
     @GetMapping("/listResourceById/{id}")
     public Result<ModuleResourceVO> listById(@PathVariable Long id) {
          // 获取当前模块中可以选择的资源
          List<ResourceTreeVO> resourceTree = moduleResourceService.listResourceById(id);
          // 获取当前模块中已有的资源ID集合
          List<Long> ids = moduleResourceService.listCheckById(id);
          List<String> checkIds = new ArrayList<>();
          for (Long resourceId : ids) {
               checkIds.add(String.valueOf(resourceId));
          }
          return Result.success(new ModuleResourceVO(resourceTree, checkIds));
     }

     @ApiOperation("为指定模块分配资源请求")
     @PostMapping("/save")
     @OperateLogAnnotation(type = AnnotationConst.ADD)
     public Result<Boolean> save(@Validated @RequestBody ModuleResourceDTO moduleResourceDTO) {
          return Result.success(moduleResourceService.save(moduleResourceDTO));
     }

     @ApiOperation("批量删除模块对应请求资源")
     @DeleteMapping("/delete")
     @OperateLogAnnotation(type = AnnotationConst.DELETE)
     public Result<Boolean> delete(@RequestBody List<Long> ids) {
          // 删除对应数据并返回结果
          return Result.success(moduleResourceService.delete(ids));
     }

}
