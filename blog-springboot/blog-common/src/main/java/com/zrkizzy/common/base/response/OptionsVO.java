package com.zrkizzy.common.base.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选项返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
