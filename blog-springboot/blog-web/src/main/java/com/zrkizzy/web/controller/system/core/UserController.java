package com.zrkizzy.web.controller.system.core;

import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.server.service.core.IUserService;
import com.zrkizzy.server.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("获取所有用户")
    @PostMapping("/list")
    public List<UserVO> listUsers() {
        return BeanCopyUtil.copyList(userService.listUsers(), UserVO.class);
    }
}
