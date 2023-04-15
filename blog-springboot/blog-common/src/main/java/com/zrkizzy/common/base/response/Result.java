package com.zrkizzy.common.base.response;

import com.zrkizzy.common.enums.HttpStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 前端返回对象
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public Result(HttpStatusEnum httpStatusEnum) {
        this.code = httpStatusEnum.getCode();
        this.message = httpStatusEnum.getMessage();
        this.data = null;
    }

    public Result(HttpStatusEnum httpStatusEnum, T data) {
        this.code = httpStatusEnum.getCode();
        this.message = httpStatusEnum.getMessage();
        this.data = data;
    }

    public Result(HttpStatusEnum httpStatusEnum, String message) {
        this.code = httpStatusEnum.getCode();
        this.message = message;
        this.data = null;
    }

    public Result(HttpStatusEnum httpStatusEnum, String message, T data) {
        this.code = httpStatusEnum.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 响应成功
     *
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> success() {
        return new Result<>(HttpStatusEnum.SUCCESS);
    }

    /**
     * 响应成功
     *
     * @param message 响应信息
     * @param data 响应数据
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(HttpStatusEnum.SUCCESS, message, data);
    }

    /**
     * 响应成功
     *
     * @param httpStatusEnum 前端返回对象状态枚举
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> success(HttpStatusEnum httpStatusEnum) {
        return new Result<>(httpStatusEnum);
    }

    /**
     * 响应成功
     *
     * @param data 传递数据
     * @return 返回对应对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(HttpStatusEnum.SUCCESS, data);
    }

    /**
     * 响应成功
     *
     * @param httpStatusEnum 前端返回对象状态枚举
     * @param message 响应信息
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> success(HttpStatusEnum httpStatusEnum, String message) {
        return new Result<>(httpStatusEnum, message);
    }

    /**
     * 响应成功
     *
     * @param httpStatusEnum 前端返回对象状态枚举
     * @param data 传递数据
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> success(HttpStatusEnum httpStatusEnum, T data) {
        return new Result<>(httpStatusEnum, data);
    }

    /**
     * 响应失败
     *
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> failure() {
        return new Result<>(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 响应失败
     *
     * @param httpStatusEnum 前端返回对象状态枚举
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> failure(HttpStatusEnum httpStatusEnum) {
        return new Result<>(httpStatusEnum);
    }

    /**
     * 响应失败
     *
     * @param httpStatusEnum 前端返回对象状态枚举
     * @param message 响应信息
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> failure(HttpStatusEnum httpStatusEnum, String message) {
        return new Result<>(httpStatusEnum, message);
    }

    /**
     * 响应失败
     *
     * @param httpStatusEnum 前端返回对象状态枚举
     * @param data 传递数据
     * @return 对应的前端返回对象
     * @param <T> 返回参数类型
     */
    public static <T> Result<T> failure(HttpStatusEnum httpStatusEnum, T data) {
        return new Result<>(httpStatusEnum, data);
    }

}
