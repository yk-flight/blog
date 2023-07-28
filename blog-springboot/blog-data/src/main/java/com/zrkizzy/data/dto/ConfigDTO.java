package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 系统配置数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/7/22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ConfigDTO extends BaseDTO {

    /**
     * 用户默认头像
     */
    private String avatar;

    /**
     * 系统通知
     */
    private String notice;

    /**
     * 上传策略
     */
    private String upload;

}
