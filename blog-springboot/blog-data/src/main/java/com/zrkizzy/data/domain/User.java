package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体类
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户对象")
@TableName("tb_user")
public class User extends BaseEntity {

    /**
     * 用户名（使用邮箱作为唯一替代）
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 状态，0：禁用，1：启用
     */
    @ApiModelProperty("状态，0：禁用，1：启用")
    private Boolean status;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色")
    @TableField(exist = false)
    private List<Role> roles;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 登录唯一标识
     */
    @ApiModelProperty("登录唯一标识")
    @TableField(exist = false)
    private String track;

    /**
     * 登录IP
     */
    @ApiModelProperty("登录IP")
    @TableField(exist = false)
    private String ipAddress;

    /**
     * IP属地
     */
    @ApiModelProperty("IP属地")
    @TableField(exist = false)
    private String ipLocation;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    @TableField(exist = false)
    private LocalDateTime loginTime;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    @TableField(exist = false)
    private String os;

    /**
     * 浏览器
     */
    @ApiModelProperty("浏览器")
    @TableField(exist = false)
    private String browser;

}
