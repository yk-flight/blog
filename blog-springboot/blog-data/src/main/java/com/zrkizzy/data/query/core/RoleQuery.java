package com.zrkizzy.data.query.core;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 角色信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/6/5
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RoleQuery extends BasePage {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String mark;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}
