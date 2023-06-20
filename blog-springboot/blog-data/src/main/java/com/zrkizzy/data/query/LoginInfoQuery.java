package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 用户登录信息信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LoginInfoQuery extends BasePage {

    /**
     * 登录用户名称
     */
    private String username;

    /**
     * 登录状态：0 失败; 1 成功
     */
    private Boolean status;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}