package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 资源数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ResourceDTO extends BaseDTO {


    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源请求方式
     */
    private String method;

    /**
     * 资源请求路径
     */
    private String url;

}
