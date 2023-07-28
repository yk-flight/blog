package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 模块资源关联查询对象
 *
 * @author zhangrongkang
 * @since 2023/7/28
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ModuleResourceQuery extends BasePage {

    /**
     * 模块ID
     */
    @NotNull(message = "模块ID不能为空")
    private Long moduleId;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}
