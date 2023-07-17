package com.zrkizzy.data.vo.export;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * API接口Excel导出数据对象
 *
 * @author zhangrongkang
 * @since 2023/7/17
 */
@Data
@Builder
@AllArgsConstructor
public class ApiScanVO implements Serializable {

    /**
     * 所属类
     */
    @ExcelProperty("请求类")
    private String className;

    /**
     * 所属类描述
     */
    @ExcelProperty("请求类描述")
    private String classDesc;

    /**
     * 请求路径
     */
    @ExcelProperty("请求路径")
    private String url;

    /**
     * 方法名称
     */
    @ExcelProperty("方法名称")
    private String method;

    /**
     * 方法描述
     */
    @ExcelProperty("方法描述")
    private String methodDesc;

    /**
     * 方法类型
     */
    @ExcelProperty("方法类型")
    private String methodType;

    /**
     * 传入参数
     */
    @ExcelProperty("传入参数")
    private String inParam;

    /**
     * 传入参数描述
     */
    @ExcelProperty("入参描述")
    private String inDesc;

    /**
     * 传入参数类型
     */
    @ExcelProperty("入参类型")
    private String inParamType;

    /**
     * 返回参数类型
     */
    @ExcelProperty("返回参数类型")
    private String outType;

    /**
     * 返回参数描述
     */
    @ExcelProperty("返回描述")
    private String outDesc;

}
