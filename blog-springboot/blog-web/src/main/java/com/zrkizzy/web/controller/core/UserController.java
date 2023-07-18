package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.dto.AvatarDTO;
import com.zrkizzy.data.dto.PasswordDTO;
import com.zrkizzy.data.dto.UserInfoDTO;
import com.zrkizzy.data.query.UserQuery;
import com.zrkizzy.data.vo.UserVO;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.core.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Api(tags = "用户管理控制器")
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation("获取当前登录用户")
    @GetMapping("/getLoginUser")
    public Result<UserVO> getLoginUser() {
        // 获取当前登录用户对象
        User user = securityUtil.getLoginUser();
        // 转换为用户数据返回对象
        UserVO userVO = BeanCopyUtil.copy(user, UserVO.class);
        // 单独定义用户角色（用户只有一个角色）
        userVO.setRoles(user.getRoles().get(0).getMark());
        // 返回数据
        return Result.success(userVO);
    }

    @ApiOperation("更新用户个人信息")
    @PostMapping("/updateUser")
    public Result<?> updateUser(@RequestBody @Validated UserInfoDTO userInfoDTO) {
        return userService.updateUser(userInfoDTO);
    }

    @ApiOperation("更新登录用户头像")
    @PostMapping("/updateLoginUserAvatar")
    public Result<String> updateLoginUserAvatar(@RequestBody @Validated AvatarDTO avatarDTO) {
        return userService.updateLoginUserAvatar(avatarDTO);
    }

    @ApiOperation("更新用户密码")
    @PostMapping("/updatePassword")
    public Result<?> updatePassword(@RequestBody @Validated PasswordDTO passwordDTO) {
        return userService.updatePassword(passwordDTO);
    }

    @ApiOperation("获取所有用户")
    @PostMapping("/list")
    public Result<PageResult<UserVO>> listUsers(@RequestBody UserQuery userQuery) {
        return Result.success(userService.listUsers(userQuery));
    }

    @ApiOperation("获取用户选项")
    @GetMapping("/listUserOptions")
    public Result<List<OptionsVO>> listUserOptions() {
        return Result.success(userService.listUserOptions());
    }
}
