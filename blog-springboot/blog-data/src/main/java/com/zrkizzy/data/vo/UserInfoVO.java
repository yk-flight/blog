package com.zrkizzy.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 个人信息数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/5/1
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserInfoVO extends BaseVO {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * IP属地
     */
    private String ipSource;

    /**
     * 登录设备
     */
    private String device;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态，0：禁用，1：启用
     */
    private Boolean status;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 角色ID
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING)
    private Long roleId;

    /**
     * 用户角色
     */
    private String roles;

}
