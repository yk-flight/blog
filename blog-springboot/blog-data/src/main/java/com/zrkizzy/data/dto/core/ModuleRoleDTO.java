package com.zrkizzy.data.dto.core;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 模块角色关联数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/31
 */
@Data
public class ModuleRoleDTO implements Serializable {

    /**
     * 角色ID
     */
    @NotNull(message = "角色Id不能为空")
    private Long roleId;

    /**
     * 模块ID
     */
    @NotEmpty(message = "模块ID不能为空")
    private List<Long> moduleIds;

}
