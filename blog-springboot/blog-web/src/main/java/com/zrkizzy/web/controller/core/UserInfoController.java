package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.core.UserInfo;
import com.zrkizzy.data.vo.UserInfoVO;
import com.zrkizzy.data.vo.UserVO;
import com.zrkizzy.server.service.core.IUserInfoService;
import com.zrkizzy.server.service.core.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息控制器
 *
 * @author zhangrongkang
 * @since 2023/7/18
 */
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/admin/user-info")
public class UserInfoController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserInfoService userInfoService;

    @ApiOperation("用户个人信息")
    @GetMapping("/getUserInfo")
    public Result<UserInfoVO> getUserInfo () {
        // 获取用户信息
        return Result.success(userInfoService.getUserInfo());
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping("/getUserInfoById/{id}")
    public Result<UserInfoVO> getUserInfoById(@PathVariable Long id) {
        // 拿到用户的具体信息
        UserVO userVO = userService.getUserById(id);
        UserInfoVO userInfoVO = BeanCopyUtil.copy(userVO, UserInfoVO.class);
        // 获取当前用户信息对象
        UserInfo userInfo = userInfoService.getUserInfoById(id);
        BeanCopyUtil.copy(userInfo, userInfoVO);
        return Result.success(userInfoVO);
    }

}
