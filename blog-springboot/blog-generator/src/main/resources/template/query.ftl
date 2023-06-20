package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * ${objectName}信息查询对象
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ${entityName}Query extends BasePage {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fieldList as field>

    <#if !field.isNull>
    /**
     * ${field.comment}
     */
    private ${field.javaType} ${field.javaName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

    /**
     * 时间范围
     */
    <#-- 如果直接写<String>会被Freemarker认为是HTML标签 -->
    <#assign T = "String">
    private List<${T}> dataRange;

}