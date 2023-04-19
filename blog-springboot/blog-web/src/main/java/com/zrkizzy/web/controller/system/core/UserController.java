package com.zrkizzy.web.controller.system.core;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.core.IUserService;
import com.zrkizzy.server.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理控制器
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Api(tags = "用户管理模块")
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
        userVO.setRoles(user.getRoles().get(0).getName());
        // 返回数据
        return Result.success(userVO);
    }

    @ApiOperation("获取所有用户")
    @PostMapping("/list")
    public List<UserVO> listUsers() {
        return BeanCopyUtil.copyList(userService.listUsers(), UserVO.class);
    }
}
