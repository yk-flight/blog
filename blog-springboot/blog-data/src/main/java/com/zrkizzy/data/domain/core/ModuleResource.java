package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源模块关联实体类
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Data
@TableName("tb_module_resource")
@ApiModel(value="资源模块关联对象")
public class ModuleResource implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 模块主键
     */
    @ApiModelProperty(value = "模块主键")
    private Long moduleId;

    /**
     * 资源主键
     */
    @ApiModelProperty(value = "资源主键")
    private Long resourceId;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

}