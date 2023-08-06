package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.query.MenuQuery;
import com.zrkizzy.data.vo.MenuVO;
import com.zrkizzy.data.vo.route.RouterVO;
import com.zrkizzy.server.service.core.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<RouterVO>> getRoutes() {
        List<RouterVO> menus = menuService.getRoutes();
        return Result.success(menus);
    }

    @ApiOperation("获取菜单数据")
    @PostMapping("/list")
    public Result<List<MenuVO>> list(@RequestBody MenuQuery menuQuery) {
        return Result.success(menuService.listMenu(menuQuery));
    }

    @ApiOperation("获取菜单选项")
    @GetMapping("/listMenuOptions")
    public Result<List<OptionsVO>> listMenuOptions() {
        return Result.success(menuService.listMenuOptions());
    }
}
