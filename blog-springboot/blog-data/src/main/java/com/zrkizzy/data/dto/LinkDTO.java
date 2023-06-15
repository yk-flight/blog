package com.zrkizzy.data.dto;

import com.zrkizzy.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 友情链接数据传输对象
 *
 * @author zhangrongkang
 * @since 2023/6/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LinkDTO extends BaseDTO {


    /**
     * 网站名称
     */
    private String name;

    /**
     * 网站域名
     */
    private String website;

    /**
     * 网站Logo
     */
    private String logo;

    /**
     * 网站介绍
     */
    private String introduce;

}
