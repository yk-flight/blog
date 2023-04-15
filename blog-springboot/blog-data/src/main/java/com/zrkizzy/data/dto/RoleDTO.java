package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/3/8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RoleDTO extends BaseDTO {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

}
