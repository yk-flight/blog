package com.zrkizzy.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.query.LoginInfoQuery;
import com.zrkizzy.data.vo.LoginInfoVO;
import com.zrkizzy.server.service.system.ILoginInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户登录信息数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Api(tags = "用户登录信息控制器")
@RestController
@RequestMapping("/admin/login-info")
public class LoginInfoController {

     @Autowired
     private ILoginInfoService loginInfoService;

    @ApiOperation("获取所有用户登录信息")
    @PostMapping("/list")
    public Result<PageResult<LoginInfoVO>> listLoginInfos(@RequestBody LoginInfoQuery loginInfoQuery) {
        // 查询到对应的用户登录信息集合
        Page<LoginInfo> loginInfoPage = loginInfoService.listLoginInfos(loginInfoQuery);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<LoginInfoVO>builder().total(loginInfoPage.getTotal())
            // 用户登录信息集合
            .list(BeanCopyUtil.copyList(loginInfoPage.getRecords(), LoginInfoVO.class)).build());
    }

    @ApiOperation("批量删除用户登录信息数据")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (loginInfoService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "用户登录信息数据删除失败");
    }

}
