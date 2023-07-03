package com.zrkizzy.common.base.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 选项返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Data
public class OptionsVO {

    /**
     * 模块ID
     */
    @JsonFormat(shape =JsonFormat.Shape.STRING)
    private Long value;

    /**
     * 模块名称
     */
    private String label;

}
