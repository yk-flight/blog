package com.zrkizzy.data.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 系统配置数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ConfigVO extends BaseVO {

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 配置描述
     */
    private String configDesc;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 是否为系统内置：1 是，0 否
     */
    private Boolean configBuild;

    /**
     * 配置类型： IMAGE 图片，TEXT 文本，RADIO 单选
     */
    private String configType;

}
