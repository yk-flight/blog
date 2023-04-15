package com.zrkizzy.web.controller.tool;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统创建测试类
 *
 * @author zhangrongkang
 * @since 2023/3/6
 */
@Api(tags = "系统创建测试类")
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

}
