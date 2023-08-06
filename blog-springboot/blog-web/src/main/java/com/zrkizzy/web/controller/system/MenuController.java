package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.data.dto.system.MenuDTO;
import com.zrkizzy.data.query.MenuQuery;
import com.zrkizzy.data.vo.MenuVO;
import com.zrkizzy.data.vo.route.RouterVO;
import com.zrkizzy.server.service.core.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @ApiOperation("获取指定菜单数据")
    @GetMapping("/getMenuById/{id}")
    public Result<MenuVO> getMenuById(@PathVariable Long id) {
        MenuVO menuVO = menuService.getMenuById(id);
        return Result.success(menuVO);
    }

    @ApiOperation("获取菜单选项")
    @GetMapping("/listMenuOptions")
    public Result<List<OptionsVO>> listMenuOptions() {
        return Result.success(menuService.listMenuOptions());
    }

    @ApiOperation("保存菜单信息")
    @PostMapping("/save")
    public Result<Boolean> save(@Validated @RequestBody MenuDTO menuDTO) {
        Boolean result = menuService.save(menuDTO);
        return Result.success(result);
    }

    @ApiOperation("删除指定菜单")
    @DeleteMapping("/delete/{id}")
    @OperateLogAnnotation(type = AnnotationConst.DELETE)
    public Result<Boolean> delete(@PathVariable Long id) {
        // 删除指定菜单
        Boolean result = menuService.delete(id);
        return Result.success(result);
    }

}
