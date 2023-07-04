package com.zrkizzy.server.service.monitor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.mapper.LoginInfoMapper;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.dto.LoginInfoDTO;
import com.zrkizzy.data.query.LoginInfoQuery;
import com.zrkizzy.data.vo.LoginInfoVO;
import com.zrkizzy.server.service.monitor.ILoginInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public Page<LoginInfo> listLoginInfos(LoginInfoQuery loginInfoQuery) {
        // 开启分页
        Page<LoginInfo> page = new Page<>(loginInfoQuery.getCurrentPage(), loginInfoQuery.getPageSize());
        // 定义查询条件
        QueryWrapper<LoginInfo> queryWrapper = new QueryWrapper<>();
        // 登录用户名称
        if (StringUtils.hasLength(loginInfoQuery.getUsername())) {
            queryWrapper.eq("username", loginInfoQuery.getUsername());
        }
        // 登录状态：0 失败; 1 成功
        queryWrapper.eq("status", loginInfoQuery.getStatus());
        // 登录时间
        // 获取时间范围
        List<String> dataRange = loginInfoQuery.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        // 查询分页
        return loginInfoMapper.selectPage(page, queryWrapper);
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

}
