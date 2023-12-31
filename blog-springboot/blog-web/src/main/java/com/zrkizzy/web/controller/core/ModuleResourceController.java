package com.zrkizzy.web.controller.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.query.ModuleResourceQuery;
import com.zrkizzy.data.vo.ResourceVO;
import com.zrkizzy.server.service.core.IModuleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
