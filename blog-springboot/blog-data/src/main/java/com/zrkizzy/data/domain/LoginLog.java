package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录日志实体类
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Data
@TableName("tb_login_log")
@ApiModel(value="登录日志对象")
public class LoginLog implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 登录IP
     */
    @ApiModelProperty("登录IP")
    private String loginIp;

    /**
     * 登录位置
     */
    @ApiModelProperty("登录位置")
    private String loginLocation;

    /**
     * 浏览器版本
     */
    @ApiModelProperty("浏览器版本")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统版本")
    private String os;

    /**
     * 登录状态
     */
    @ApiModelProperty("登录状态")
    private Boolean status;

    /**
     * 登录消息提示
     */
    @ApiModelProperty("登录消息提示")
    private String message;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    private LocalDateTime loginTime;

}
