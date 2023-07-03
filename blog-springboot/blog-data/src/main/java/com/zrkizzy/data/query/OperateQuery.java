package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 操作日志信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/6/26
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class OperateQuery extends BasePage {

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 操作类型 0 其他操作，1 新增，2 修改， 3 删除
     */
    private Byte type;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作用户ID
     */
    private String userId;

    /**
     * 操作状态 0 失败 1 成功
     */
    private Boolean status;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}