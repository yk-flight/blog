package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 操作日志数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class OperateLogDTO extends BaseDTO {


    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 操作类型 0 其他操作，1 新增，2 修改， 3 删除， 4 查询
     */
    private Byte type;

    /**
     * 操作方法名称
     */
    private String methodName;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作用户ID
     */
    private Long userId;

    /**
     * 操作IP
     */
    private String operateIp;

    /**
     * 操作地址
     */
    private String operateLocation;

    /**
     * 操作参数
     */
    private String operateParam;

    /**
     * 操作结果描述
     */
    private String operateResult;

    /**
     * 操作状态 0 失败 1 成功 
     */
    private Boolean status;

    /**
     * 操作消耗时间
     */
    private Long costTime;

}
