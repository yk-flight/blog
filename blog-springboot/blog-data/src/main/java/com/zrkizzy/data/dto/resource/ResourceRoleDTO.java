package com.zrkizzy.data.dto.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * 资源角色数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
@Data
public class ResourceRoleDTO implements Serializable {

    /**
     * 资源路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 所属模块ID
     */
    private Long moduleId;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 当前资源请求对应角色
     */
    private String roleMark;

}
