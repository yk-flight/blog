package com.zrkizzy.data.vo.dashboard;

import lombok.Data;

import java.io.Serializable;

/**
 * 首页数据返回返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/23
 */
@Data
public class HomeVO implements Serializable {

    /**
     * 系统公告
     */
    private String notice;

}
