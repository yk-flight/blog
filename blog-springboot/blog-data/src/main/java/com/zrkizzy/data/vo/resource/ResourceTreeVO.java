package com.zrkizzy.data.vo.resource;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 请求资源树形数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/30
 */
@Data
public class ResourceTreeVO implements Serializable {

    /**
     * 节点描述
     */
    private String label;

    /**
     * 当前节点下的数据
     */
    private List<ResourceLeafVO> children;

}
