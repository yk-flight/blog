package com.zrkizzy.data.vo.route;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 路由信息数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/4/18
 */
@Data
@AllArgsConstructor
public class MetaVO implements Serializable {

    /**
     * 组件名称
     */
    private String title;

    /**
     * 组件图标
     */
    private String icon;

    /**
     * 是否缓存 false：不缓存；true：缓存
     */
    private Boolean noCache;

    /**
     * 外链地址
     */
    private String link;

}
