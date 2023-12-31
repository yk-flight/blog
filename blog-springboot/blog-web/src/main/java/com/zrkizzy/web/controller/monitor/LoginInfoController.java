package com.zrkizzy.web.controller.monitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.annotation.ParamMean;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.data.query.monitor.LoginInfoQuery;
import com.zrkizzy.data.vo.LoginInfoVO;
import com.zrkizzy.server.service.monitor.ILoginInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户登录日志控制器
 *
 * @author zhangrongkang
 * @since 2023/7/4
 */
@Api(tags = "用户登录日志控制器")
@RestController
@RequestMapping("/admin/login-info")
public class LoginInfoController {

     @Autowired
     private ILoginInfoService loginInfoService;

    @ApiOperation("获取所有用户登录信息")
    @ParamMean(in = "用户查询对象", out = "用户登录信息分页数据")
    @PostMapping("/list")
    public Result<PageResult<LoginInfoVO>> listLoginInfos(@RequestBody LoginInfoQuery loginInfoQuery) {
        // 查询到对应的用户登录信息集合
        Page<LoginInfoVO> loginInfoPage = loginInfoService.listLoginInfos(loginInfoQuery);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<LoginInfoVO>builder().total(loginInfoPage.getTotal())
            // 用户登录信息集合
            .list(loginInfoPage.getRecords()).build());
    }

    @ApiOperation("批量删除用户登录信息数据")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (loginInfoService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "用户登录信息数据删除失败");
    }

    @ApiOperation("清空所有登录日志")
    @GetMapping("/clear")
    public Result<?> clear() {
        // 清空登录日志
        loginInfoService.clear();
        return Result.success();
    }

}
