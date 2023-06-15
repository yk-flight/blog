package com.zrkizzy.data.vo;

import com.zrkizzy.common.base.response.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 友情链接数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/6/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LinkVO extends BaseVO {


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
