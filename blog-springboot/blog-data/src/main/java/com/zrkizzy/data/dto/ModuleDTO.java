package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源模块数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModuleDTO extends BaseDTO {

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块描述
     */
    private String description;

}
