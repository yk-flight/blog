package com.zrkizzy.data.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 资源数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ResourceVO extends BaseVO {

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
