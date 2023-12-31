package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.server.service.core.IMenuService;
import com.zrkizzy.data.vo.route.RouterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单控制器
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Api(tags = "菜单控制器")
@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("获取菜单")
    @GetMapping("/getRoutes")
    public Result<?> getRoutes() {
        List<RouterVO> menus = menuService.getRoutes();
        return Result.success(menus);
    }
}
