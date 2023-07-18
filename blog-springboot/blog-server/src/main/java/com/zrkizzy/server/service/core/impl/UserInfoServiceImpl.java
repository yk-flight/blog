package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.domain.UserInfo;
import com.zrkizzy.data.mapper.UserInfoMapper;
import com.zrkizzy.data.vo.UserInfoVO;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.server.service.core.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/5/4
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 获取用户个人信息
     *
     * @return 个人信息数据返回对象
     */
    @Override
    public Result<UserInfoVO> getUserInfo() {
        // 从SecurityUtil中获取到当前登录用户对象
        User user = securityUtil.getLoginUser();
        // 根据查询到的User对象复制UserInfoVO对象
        UserInfoVO userInfoVO = BeanCopyUtil.copy(user, UserInfoVO.class);
        // 查询当前用户对应UserInfo对象
        UserInfo userInfo = getUserInfoById(user.getId());
        if (null != userInfo) {
            // 手机号码
            userInfoVO.setPhone(userInfo.getPhone());
        }
        // 返回数据
        return Result.success(
                // 角色名称
                userInfoVO.setRoleName(securityUtil.getLoginUserRoleName())
                        // IP地址
                        .setIpAddress(user.getIpAddress())
                        // IP属地
                        .setIpSource(user.getIpLocation())
                        // 登录设备
                        .setDevice(securityUtil.getUserAgent())
        );
    }

    /**
     * 通过ID获取用户信息
     *
     * @param id 用户ID（同用户信息ID）
     * @return 用户信息实体
     */
    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.selectById(id);
    }
}
