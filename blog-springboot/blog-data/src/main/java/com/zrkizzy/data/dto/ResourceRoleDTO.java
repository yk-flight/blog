package com.zrkizzy.data.dto;

import lombok.Data;

import java.util.List;

/**
 * 资源角色数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/3/16
 */
@Data
public class ResourceRoleDTO {

    /**
     * 资源路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 当前资源请求对应角色
     */
    private List<String> roles;

}
