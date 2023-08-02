package com.zrkizzy.data.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/8/2
 */
@Data
public class MenuQuery implements Serializable {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 状态，0：禁用，1：正常
     */
    private Boolean status;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}
