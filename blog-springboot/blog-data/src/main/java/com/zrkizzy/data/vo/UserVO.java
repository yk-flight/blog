package com.zrkizzy.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserVO extends BaseVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态，0：禁用，1：启用
     */
    private Boolean status;

    /**
     * 角色ID
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING)
    private Long roleId;

    /**
     * 用户角色
     */
    private String roles;

    /**
     * 备注
     */
    private String remark;

}
