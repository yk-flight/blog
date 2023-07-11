package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.common.utils.ServletUtil;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.common.utils.IpUtil;
import com.zrkizzy.common.utils.security.JwtTokenUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.domain.UserInfo;
import com.zrkizzy.data.dto.AvatarDTO;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.data.dto.PasswordDTO;
import com.zrkizzy.data.dto.UserInfoDTO;
import com.zrkizzy.data.mapper.UserMapper;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.security.context.SecurityContext;
import com.zrkizzy.security.util.SecurityUtil;
import com.zrkizzy.security.util.UserDetailUtil;
import com.zrkizzy.server.service.core.IUserInfoService;
import com.zrkizzy.server.service.core.IUserService;
import com.zrkizzy.data.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static com.zrkizzy.common.constant.RedisConst.*;
import static com.zrkizzy.common.constant.TimeConst.TWO_HOUR;
import static com.zrkizzy.common.enums.HttpStatusEnum.*;

/**
 * 用户业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/3/7
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailUtil userDetailUtil;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRedisService redisService;
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有用户
     *
     * @return 所有用户集合
     */
    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    /**
     * 用户登录返回Token
     *
     * @param loginDTO 用户登录数据传输对象
     * @return 公共返回对象
     */
    @Override
    public Result<String> login(LoginDTO loginDTO) {
        // Redis中获取验证码
        String redisCode = redisService.get(CAPTCHA_PREFIX + loginDTO.getTrack(), String.class);
        // 验证码是否过期
        if (!StringUtils.hasLength(redisCode)) {
            return Result.failure(CODE_EXPIRED);
        }
        // 判断验证码是否一致
        if (!redisCode.equals(loginDTO.getCode())) {
            return Result.failure(CODE_ERROR);
        }
        // 获取用户名和密码
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 数据库中查询对应的User对象
        User user = userMapper.getUserByUsername(username);
        // 判断当前用户是否存在
        if (null == user) {
            return Result.failure(HttpStatusEnum.USER_NOT_EXIST);
        }
        // 判断用户是否禁用
        if (!user.getStatus()) {
            return Result.failure(HttpStatusEnum.USER_ENABLE);
        }
        // 校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Result.failure(HttpStatusEnum.PASSWORD_ERROR);
        }
        // Redis中不显示密码
        user.setPassword(null);
        // 设置当前用户登录时间
        user.setLoginTime(LocalDateTime.now());

        String ipAddress = IpUtil.getIpAddress(ServletUtil.getRequest());
        // 登录IP
        user.setIpAddress(ipAddress);
        // 登录位置
        user.setIpLocation(IpUtil.getIpLocation(ipAddress));
        // 存在则将获取到的用户信息存储到Redis中，过期时间为两小时
        redisService.set(USER_PREFIX + loginDTO.getTrack(), user, TWO_HOUR);
        // 根据用户详细信息生成Token
        return Result.success(jwtTokenUtil.generateToken(loginDTO.getTrack()));
    }

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
        UserInfo userInfo = userInfoService.getUserInfoById(user.getId());
        if (null != userInfo) {
            // 手机号码
            userInfoVO.setPhone(userInfo.getPhone());
        }
        // 获取 request 请求
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        // 返回数据
        return Result.success(
                // 角色名称
                userInfoVO.setRoleName(securityUtil.getLoginUserRoleName())
                        // IP地址
                        .setIpAddress(user.getIpAddress())
                        // IP属地
                        .setIpSource(user.getIpLocation())
                        // 登录设备
                        .setDevice(securityUtil.getUserAgent(request))
        );
    }

    /**
     * 更新用户个人信息
     *
     * @param userInfoDTO 用户个人信息数据传输对象
     * @return 公告返回对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateUserInfo(UserInfoDTO userInfoDTO) {
        // 根据ID查询个人信息
        User user = userMapper.getUserByUserId(userInfoDTO.getId());
        // 如果修改用户名
        if (!user.getUsername().equals(userInfoDTO.getUsername())) {
            // 校验修改后用户名的合法性
            Long count = userMapper.selectCount(new QueryWrapper<User>().eq("username", userInfoDTO.getUsername()));
            // 校验修改内容的合法性，如果没有修改用户名则能查出1条
            if (null != count && count > 0) {
                // 确保用户名是唯一的
                return Result.failure(HttpStatusEnum.USERNAME_REPEAT);
            }
        }
        // 将用户密码提前保存下来，在Redis中不缓存密码
        String password = user.getPassword();
        // 获取用户全局唯一标识
        String track = SecurityContext.getTrack();
        // 获取当前更新个人信息用户缓存的失效时间
        Long expire = redisService.getExpire(USER_PREFIX + track);
        // 删除Redis中缓存的用户个人信息
        redisService.del(USER_PREFIX + track);

        // 设置用户信息
        user.setNickname(userInfoDTO.getNickname());
        user.setUsername(userInfoDTO.getUsername());
        user.setRemark(userInfoDTO.getRemark());
        user.setUpdateTime(LocalDateTime.now());
        // Redis中不展示密码
        user.setPassword(null);
        // 更新缓存中的用户个人信息，缓存失效时间不改变
        redisService.set(USER_PREFIX + track, user, expire);
        user.setPassword(password);

        // 更新数据库
        return userMapper.updateById(user) > 0 ?
                Result.success() : Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 更新用户密码
     *
     * @param passwordDTO 用户更新密码数据传递对象
     * @return 公共返回对象
     */
    @Override
    public Result<?> updatePassword(PasswordDTO passwordDTO) {
        // TODO 短信验证码Key
        // Redis中邮件验证码Key
        String emailKey = CAPTCHA_EMAIL_PREFIX + passwordDTO.getUsername();
        // 判断Redis中的验证码是否过期
        if (!redisService.hasKey(emailKey)) {
            return Result.failure(CODE_EXPIRED);
        }
        // 验证码存在则获取到验证码
        String code = redisService.get(emailKey, String.class);
        // 对比当前验证码与用户输入验证码是否一致
        if (!code.equals(passwordDTO.getCode())) {
            return Result.failure(CODE_ERROR);
        }
        // 获取数据库中用户
        User user = userMapper.selectById(passwordDTO.getId());
        // 与原密码对比是否一致
        if (passwordEncoder.matches(passwordDTO.getPassword(), user.getPassword())) {
            // 原密码和新密码不能相同
            return Result.failure(HttpStatusEnum.PASSWORD_SAME);
        }
        // 获取Redis中存储的Key
        String userKey = USER_PREFIX + SecurityContext.getTrack();
        // 更新密码
        user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
        // 更新用户更新时间
        user.setUpdateTime(LocalDateTime.now());
        if (userMapper.updateById(user) > 0) {
            // 清除Redis重新登录
            redisService.del(userKey);
            return Result.success();
        }
        return Result.failure(PASSWORD_UPDATE_ERROR);
    }

    /**
     * 更新登录用户头像
     *
     * @param avatarDTO 用户头像数据传输对象
     * @return 用户头像访问路径
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateLoginUserAvatar(AvatarDTO avatarDTO) {
        // 获取当前用户对象
        User user = userMapper.getUserByUserId(avatarDTO.getUserId());
        // 用户头像
        user.setAvatar(avatarDTO.getSrc());
        // 更新时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新数据库中用户信息
        userMapper.updateUserAvatar(avatarDTO.getUserId(), avatarDTO.getSrc());
        // 获取用户唯一标识
        String track = SecurityContext.getTrack();
        // 获取当前用户缓存的失效时间
        Long expire = redisService.getExpire(USER_PREFIX + track);
        // 删除Redis中缓存的用户个人信息
        redisService.del(USER_PREFIX + track);
        // Redis中不展示密码
        user.setPassword(null);
        // 更新缓存中的用户个人信息，缓存失效时间不改变
        redisService.set(USER_PREFIX + track, user, expire);
        // 将头像返回
        return Result.success(avatarDTO.getSrc());
    }

    /**
     * 获取用户选项集合
     *
     * @return 获取用户选项集合
     */
    @Override
    public List<OptionsVO> listUserOptions() {
        return userMapper.listUserOptions();
    }

}
