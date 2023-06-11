package com.zrkizzy.data.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#if chainModel>
import lombok.experimental.Accessors;
</#if>
import java.io.Serial;

/**
 * ${objectName}实体类
 *
 * @author ${author}
 * @since ${date}
 */
@Data
<#if chainModel>
@Accessors(chain = true)
</#if>
@TableName("${tableName}")
@ApiModel(value="${objectName}对象")
@EqualsAndHashCode(callSuper = false)
public class ${entityName} extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list fieldList as field>

    /**
     * ${field.comment}
     */
    @ApiModelProperty(value = "${field.comment}")
    private ${field.javaType} ${field.javaName};
</#list>
<#------------  END 字段循环遍历  ---------->

}