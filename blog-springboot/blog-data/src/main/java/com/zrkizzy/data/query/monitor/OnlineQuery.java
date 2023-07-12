package com.zrkizzy.data.query.monitor;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 在线用户信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/7/11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class OnlineQuery extends BasePage {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

}
