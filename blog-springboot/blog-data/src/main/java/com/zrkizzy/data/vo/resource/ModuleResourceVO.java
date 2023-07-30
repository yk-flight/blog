package com.zrkizzy.data.vo.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模块资源关联对象
 *
 * @author zhangrongkang
 * @since 2023/7/30
 */
@Data
@AllArgsConstructor
public class ModuleResourceVO implements Serializable {

    /**
     * 请求资源树形数据
     */
    private List<ResourceTreeVO> resourceTree;

    /**
     * 当前模块已有的请求资源
     */
    private List<String> checkIds;

}
