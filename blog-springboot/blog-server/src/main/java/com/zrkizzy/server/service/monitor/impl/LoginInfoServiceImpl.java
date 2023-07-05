package com.zrkizzy.server.service.monitor.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.mapper.LoginInfoMapper;
import com.zrkizzy.data.query.LoginInfoQuery;
import com.zrkizzy.data.vo.LoginInfoVO;
import com.zrkizzy.server.service.monitor.ILoginInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户登录信息业务逻辑接口实现类
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/4
 */
@Service
@Slf4j
public class LoginInfoServiceImpl implements ILoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    /**
     * 获取所有用户登录信息
     *
     * @param loginInfoQuery 用户登录信息查询对象
     * @return 用户登录信息分页数据
     */
    @Override
    public Page<LoginInfoVO> listLoginInfos(LoginInfoQuery loginInfoQuery) {
        // 开启分页
        Page<LoginInfo> page = new Page<>(loginInfoQuery.getCurrentPage(), loginInfoQuery.getPageSize());
        // 查询分页
        return loginInfoMapper.listLoginInfo(page, loginInfoQuery);
    }
    
    /**
     * 批量删除用户登录信息数据
     *
     * @param ids 用户登录信息ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return loginInfoMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 清空登录日志
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clear() {
        loginInfoMapper.truncate();
    }

}
