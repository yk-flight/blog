package com.zrkizzy.server.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RoleVO extends BaseVO {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

}
