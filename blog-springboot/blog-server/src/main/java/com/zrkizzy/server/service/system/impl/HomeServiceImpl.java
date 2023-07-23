package com.zrkizzy.server.service.system.impl;

import com.zrkizzy.data.vo.dashboard.HomeVO;
import com.zrkizzy.server.service.system.IConfigService;
import com.zrkizzy.server.service.system.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 首页信息业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/23
 */
@Service
public class HomeServiceImpl implements IHomeService {
    @Autowired
    private IConfigService configService;

    /**
     * 获取首页数据
     *
     * @return 首页数据返回对象
     */
    @Override
    public HomeVO getHomeInfo() {
        HomeVO homeVO = new HomeVO();
        homeVO.setNotice(configService.getConfig().getNotice());
        return homeVO;
    }
}
