package com.zrkizzy.data.dto.resource;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 模块资源关联对象
 *
 * @author zhangrongkang
 * @since 2023/7/30
 */
@Data
public class ModuleResourceDTO implements Serializable {

    /**
     * 模块ID
     */
    @NotNull(message = "模块ID不能为空")
    private Long moduleId;

    /**
     * 请求资源ID集合
     */
    @NotEmpty(message = "资源ID不能为空")
    private List<Long> resourceIds;

}
