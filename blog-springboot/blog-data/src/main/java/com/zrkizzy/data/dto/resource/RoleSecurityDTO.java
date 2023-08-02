package com.zrkizzy.data.dto.resource;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/8/1
 */
@Data
public class RoleSecurityDTO implements Serializable {

    /**
     * 资源路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 当前资源请求对应角色
     */
    private List<String> roles;

}
