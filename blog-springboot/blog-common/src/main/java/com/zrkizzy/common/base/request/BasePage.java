package com.zrkizzy.common.base.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 基本分页对象
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Data
public class BasePage implements Serializable {

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 页面大小
     */
    private Integer pageSize;

}
