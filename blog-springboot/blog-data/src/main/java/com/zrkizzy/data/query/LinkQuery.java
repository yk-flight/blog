package com.zrkizzy.data.query;

import com.zrkizzy.common.base.request.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 友情链接信息查询对象
 *
 * @author zhangrongkang
 * @since 2023/6/13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LinkQuery extends BasePage {

    /**
     * 网站名称
     */
    private String name;

    /**
     * 时间范围
     */
    private List<String> dataRange;

}