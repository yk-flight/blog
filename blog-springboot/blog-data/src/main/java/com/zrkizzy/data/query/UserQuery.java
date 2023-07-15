package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 用户信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/7/15
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserQuery extends BasePage {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态，0：禁用，1：启用
     */
    private Boolean status;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}