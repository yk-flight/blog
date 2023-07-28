package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 资源信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ResourceQuery extends BasePage {

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源请求方式
     */
    private String method;

    /**
     * 资源请求路径
     */
    private String url;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}