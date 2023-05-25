package com.zrkizzy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 前端返回对象状态枚举
 * <table>
 *     <tr>
 *         <td><b>状态码</b></td>
 *         <td><b>作用范围</b></td>
 *     </tr>
 *     <tr>
 *         <td>200</td>
 *         <td>成功状态</td>
 *     </tr>
 *     <tr>
 *         <td>400</td>
 *         <td>客户端错误</td>
 *     </tr>
 *     <tr>
 *         <td>500</td>
 *         <td>服务端错误</td>
 *     </tr>
 *     <tr>
 *        <td>1000/2000</td>
 *         <td>自定义错误</td>
 *     </tr>
 * </table>
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum {

    /**
     * 成功状态码
     */
    SUCCESS(200, "请求成功"),

    /**
     * 客户端错误状态码
     */
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "当前请求未授权访问"),
    FORBIDDEN(403, "权限不足，服务器拒绝访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    METHOD_NOT_ALLOWED(405, "不支持的请求方法"),
    CONFLICT(409, "请求的资源已经存在"),

    /**
     * 服务器错误状态码
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    TOKEN_EXPIRED(506, "当前Token已过期，请重新登录"),
    TOKEN_ERROR(507, "Token不合法"),
    CODE_EXPIRED(508, "验证码已过期，请重新获取验证码"),
    CODE_ERROR(509, "验证码错误，请重新输入"),

    /**
     * 其他状态码
     */
    ROLE_EXIST(1001, "角色已存在"),
    USER_NOT_FOUND(2000, "用户未登录或登录已过期，请重新登录"),
    USER_EXIST(2001, "用户已存在"),
    USER_NOT_EXIST(2002, "用户不存在"),
    USER_ENABLE(2003, "当前用户已禁用，请联系管理员"),
    PASSWORD_ERROR(2004, "密码错误"),
    USERNAME_REPEAT(2005, "用户名重复，当前用户名已存在"),
    PASSWORD_SAME(2006, "新密码不能与旧密码相同"),
    PASSWORD_UPDATE_ERROR(2007, "密码更新失败"),
    FILE_EXIST_ERROR(3000, "当前文件已经存在，请勿重复上传"),
    FILE_UPLOAD_ERROR(3001, "文件上传失败");

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态码描述
     */
    private final String message;

}
