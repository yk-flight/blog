package com.zrkizzy.web.controller.core;

import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.AnnotationConst;
import com.zrkizzy.data.dto.AvatarDTO;
import com.zrkizzy.data.dto.core.PasswordDTO;
import com.zrkizzy.data.dto.core.UserDTO;
import com.zrkizzy.data.dto.core.UserUpdateDTO;
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
    @PutMapping("/updateLoginUser")
    @OperateLogAnnotation(type = AnnotationConst.UPDATE)
    public Result<Integer> updateLoginUser(@RequestBody @Validated UserUpdateDTO userUpdateDTO) {
        return Result.success(userService.updateLoginUser(userUpdateDTO));
    }

    @ApiOperation("更新登录用户头像")
    @PutMapping("/updateLoginUserAvatar")
    public Result<String> updateLoginUserAvatar(@RequestBody @Validated AvatarDTO avatarDTO) {
        return Result.success(userService.updateLoginUserAvatar(avatarDTO));
    }

    @ApiOperation("更新用户密码")
    @PutMapping("/updatePassword")
    public Result<Integer> updatePassword(@RequestBody @Validated PasswordDTO passwordDTO) {
        // 更新用户密码
        return Result.success(userService.updatePassword(passwordDTO));
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
    @OperateLogAnnotation(type = AnnotationConst.ADD)
    public Result<Boolean> insert(@Validated @RequestBody UserDTO userDTO) {
        // 新增用户
        return Result.success(userService.insert(userDTO));
    }

    @ApiOperation("更新指定用户")
    @PutMapping("/updateUser")
    @OperateLogAnnotation(type = AnnotationConst.UPDATE)
    public Result<Boolean> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        // 更新用户并返回结果
        return Result.success(userService.updateUser(userUpdateDTO));
    }

    @ApiOperation("更新用户状态")
    @GetMapping("/updateUserStatus/{id}")
    @OperateLogAnnotation(type = AnnotationConst.UPDATE)
    public Result<Boolean> updateUserStatus(@PathVariable Long id) {
        return Result.success(userService.updateUserStatus(id));
    }

    @ApiOperation("重置指定用户密码")
    @GetMapping("/resetPassword/{id}")
    @OperateLogAnnotation(type = AnnotationConst.UPDATE)
    public Result<Boolean> resetPassword(@PathVariable Long id) {
        return Result.success(userService.resetPassword(id));
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("/delete")
    @OperateLogAnnotation(type = AnnotationConst.DELETE)
    public Result<Boolean> delete(@RequestBody List<Long> ids) {
        return Result.success(userService.delete(ids));
    }

}
