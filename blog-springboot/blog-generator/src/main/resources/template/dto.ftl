package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

<#list fieldList as field>
    <#if field.javaType == "LocalDateTime">
import java.time.LocalDateTime;
    </#if>
</#list>

/**
 * ${objectName}数据传输对象
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ${entityName}DTO extends BaseDTO {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fieldList as field>

    /**
     * ${field.comment}
     */
    private ${field.javaType} ${field.javaName};
</#list>
<#------------  END 字段循环遍历  ---------->

}
