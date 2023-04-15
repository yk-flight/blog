package com.zrkizzy.common.exception;

import com.zrkizzy.common.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Token过期自定义异常
 *
 * @author zhangrongkang
 * @since 2023/4/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TokenExpiredException extends RuntimeException {

    private HttpStatusEnum httpStatusEnum = HttpStatusEnum.TOKEN_EXPIRED;
}
