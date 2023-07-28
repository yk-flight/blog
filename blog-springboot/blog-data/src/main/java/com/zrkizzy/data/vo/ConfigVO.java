package com.zrkizzy.data.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 系统配置数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ConfigVO extends BaseVO {

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
