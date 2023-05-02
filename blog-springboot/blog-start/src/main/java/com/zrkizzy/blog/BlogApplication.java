package com.zrkizzy.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 博客启动类
 *
 * @author zhangrongkang
 * @since 2023/3/6
 */
@MapperScan("com.zrkizzy.data.mapper")
@ComponentScan(basePackages = {"com.zrkizzy.web.controller", "com.zrkizzy.server", "com.zrkizzy.common", "com.zrkizzy.security"})
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class BlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogApplication.class, args);
    }

}
