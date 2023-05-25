package com.zrkizzy.common.exception;

import com.zrkizzy.common.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑错误自定义异常
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    /**
     * 服务器响应错误枚举
     */
    private HttpStatusEnum httpStatusEnum = HttpStatusEnum.INTERNAL_SERVER_ERROR;
}
