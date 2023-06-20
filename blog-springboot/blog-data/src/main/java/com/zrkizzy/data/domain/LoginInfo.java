package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登录信息实体类
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Data
@TableName("tb_login_info")
@ApiModel(value="用户登录信息对象")
@EqualsAndHashCode(callSuper = false)
public class LoginInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 登录用户名称
     */
    @ApiModelProperty(value = "登录用户名称")
    private String username;

    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    /**
     * 登录位置
     */
    @ApiModelProperty(value = "登录位置")
    private String loginLocation;

    /**
     * 浏览器版本
     */
    @ApiModelProperty(value = "浏览器版本")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String os;

    /**
     * 登录状态：0 失败; 1 成功
     */
    @ApiModelProperty(value = "登录状态：0 失败; 1 成功")
    private Boolean status;

    /**
     * 登录消息提示
     */
    @ApiModelProperty(value = "登录消息提示")
    private String message;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

}