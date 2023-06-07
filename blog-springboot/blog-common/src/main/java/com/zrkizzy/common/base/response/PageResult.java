package com.zrkizzy.common.base.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 前端分页返回对象
 *
 * @author zhangrongkang
 * @since 2023/4/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页返回对象")
public class PageResult<T> {

    /**
     * 数据集合
     */
    @ApiModelProperty("数据集合")
    private List<T> list;

    /**
     * 数据总数
     */
    @ApiModelProperty("数据总数")
    private Long total;

}
