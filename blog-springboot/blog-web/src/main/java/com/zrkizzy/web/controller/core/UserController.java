package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.dto.AvatarDTO;
import com.zrkizzy.data.dto.PasswordDTO;
import com.zrkizzy.data.dto.UserDTO;
import com.zrkizzy.data.dto.UserUpdateDTO;
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
    private SecurityUtil securityUtil;

    @Autowired
    private IUserService userService;

    @ApiOperation("获取当前登录用户")
    @GetMapping("/getLoginUser")
    public Result<UserVO> getLoginUser() {
        // 获取用户ID
        Long userId = securityUtil.getLoginUser().getId();
        // 返回数据
        return Result.success(userService.getUserById(userId));
    }

    @ApiOperation("更新用户个人信息")
    @PostMapping("/updateUser")
    public Result<?> updateUser(@RequestBody @Validated UserUpdateDTO userUpdateDTO) {
        userService.updateUser(userUpdateDTO);
        return Result.success();
    }

    @ApiOperation("更新登录用户头像")
    @PostMapping("/updateLoginUserAvatar")
    public Result<String> updateLoginUserAvatar(@RequestBody @Validated AvatarDTO avatarDTO) {
        return Result.success(userService.updateLoginUserAvatar(avatarDTO));
    }

    @ApiOperation("更新用户密码")
    @PostMapping("/updatePassword")
    public Result<?> updatePassword(@RequestBody @Validated PasswordDTO passwordDTO) {
        // 更新用户密码
        userService.updatePassword(passwordDTO);
        return Result.success();
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

    @ApiOperation("新增用户")
    @PostMapping("/insert")
    public Result<Integer> insert(@Validated @RequestBody UserDTO userDTO) {
        // 新增用户
        userService.insert(userDTO);
        return Result.success();
    }
}
