package com.zrkizzy.data.vo.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * 模块树形数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/31
 */
@Data
public class ModuleTreeVO implements Serializable {

    /**
     * 模块ID
     */
    private Long id;

    /**
     * 模块名称
     */
    private String label;

}
