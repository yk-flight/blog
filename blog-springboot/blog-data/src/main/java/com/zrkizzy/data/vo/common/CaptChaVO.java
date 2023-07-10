package com.zrkizzy.data.vo.common;

import lombok.Builder;
import lombok.Data;

/**
 * 验证码返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/10
 */
@Data
@Builder
public class CaptChaVO {

    /**
     * 验证码图片（Base64编码）
     */
    private String codeImage;

    /**
     * 验证码跟踪值
     */
    private String track;

}
