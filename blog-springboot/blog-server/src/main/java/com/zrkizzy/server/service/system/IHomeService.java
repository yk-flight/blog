package com.zrkizzy.server.service.system;

import com.zrkizzy.data.vo.dashboard.HomeVO;

/**
 * 首页信息业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/23
 */
public interface IHomeService {

    /**
     * 获取首页数据
     *
     * @return 首页数据返回对象
     */
    HomeVO getHomeInfo();

}
