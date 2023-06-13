package com.zrkizzy.data.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ${objectName}数据返回对象
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ${entityName}VO extends BaseVO {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fieldList as field>

    /**
    * ${field.comment}
    */
    private ${field.javaType} ${field.javaName};
</#list>
<#------------  END 字段循环遍历  ---------->

}
