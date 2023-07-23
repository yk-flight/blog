package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.vo.dashboard.HomeVO;
import com.zrkizzy.server.service.system.IHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仪表盘控制器
 *
 * @author zhangrongkang
 * @since 2023/7/23
 */
@Api(tags = "仪表盘控制器")
@RestController
@RequestMapping("/admin/index")
public class IndexController {
    @Autowired
    private IHomeService homeService;

    @ApiOperation("获取首页信息")
    @GetMapping("/getHomeInfo")
    public Result<HomeVO> getHomeInfo() {
        return Result.success(homeService.getHomeInfo());
    }

}
