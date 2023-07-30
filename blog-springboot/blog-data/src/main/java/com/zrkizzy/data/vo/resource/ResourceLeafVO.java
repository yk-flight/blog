package com.zrkizzy.data.vo.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求资源叶子数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/30
 */
@Data
public class ResourceLeafVO implements Serializable {

    /**
     * 请求资源ID
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 请求资源名称
     */
    private String name;

    /**
     * 请求资源路径
     */
    private String url;

}
