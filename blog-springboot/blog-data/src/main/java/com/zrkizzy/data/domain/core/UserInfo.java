package com.zrkizzy.data.domain.core;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zrkizzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息实体类
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户信息对象")
@TableName("tb_user_info")
public class UserInfo extends BaseEntity {

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    public String phone;

}
