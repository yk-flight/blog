package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 操作日志实体类
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Data
@TableName("tb_operate_log")
@ApiModel(value="操作日志对象")
@EqualsAndHashCode(callSuper = false)
public class OperateLog extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模块ID
     */
    @ApiModelProperty(value = "模块ID")
    private Long moduleId;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String operateContent;

    /**
     * 操作类型 0 其他操作，1 新增，2 修改， 3 删除， 4 查询
     */
    @ApiModelProperty(value = "操作类型 0 其他操作， 1 新增， 2 修改， 3 删除， 4 查询")
    private Byte type;

    /**
     * 操作方法名称
     */
    @ApiModelProperty(value = "操作方法名称")
    private String methodName;

    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID")
    private Long userId;

    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    private String operateIp;

    /**
     * 操作地址
     */
    @ApiModelProperty(value = "操作地址")
    private String operateLocation;

    /**
     * 操作参数
     */
    @ApiModelProperty(value = "操作参数")
    private String operateParam;

    /**
     * 操作结果描述
     */
    @ApiModelProperty(value = "操作结果描述")
    private String operateResult;

    /**
     * 操作状态 0 失败 1 成功 
     */
    @ApiModelProperty(value = "操作状态 0 失败 1 成功 ")
    private Boolean status;

    /**
     * 操作消耗时间
     */
    @ApiModelProperty(value = "操作消耗时间")
    private Long costTime;

}