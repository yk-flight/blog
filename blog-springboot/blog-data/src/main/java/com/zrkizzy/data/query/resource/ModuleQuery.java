package com.zrkizzy.data.query.resource;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 资源模块查询对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ModuleQuery extends BasePage {

    /**
     * 模块名称
     */
    private String name;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}
