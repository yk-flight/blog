package com.zrkizzy.web.controller.core;

import com.zrkizzy.server.service.core.IModuleResourceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

}
