package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.constant.SecurityConst;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.common.utils.IpUtil;
import com.zrkizzy.common.utils.ServletUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.common.utils.security.JwtTokenUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.dto.*;
import com.zrkizzy.data.mapper.UserMapper;
import com.zrkizzy.data.query.UserQuery;
import com.zrkizzy.data.vo.UserVO;
import com.zrkizzy.security.context.SecurityContext;
import com.zrkizzy.server.service.core.IUserRoleService;
import com.zrkizzy.server.service.core.IUserService;
import com.zrkizzy.server.service.system.IConfigService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static com.zrkizzy.common.constant.CommonConst.USER_AGENT;
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
    private IConfigService configService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取所有用户
     *
     * @param userQuery 用户查询对象
     * @return 所有用户集合
     */
    @Override
    public PageResult<UserVO> listUsers(UserQuery userQuery) {
        // 开启分页
        Page<User> page = new Page<>(userQuery.getCurrentPage(), userQuery.getPageSize());
        Page<UserVO> result = userMapper.listUsers(page, userQuery);
        return PageResult.<UserVO>builder().list(result.getRecords()).total(result.getTotal()).build();
    }

    /**
     * 用户登录返回Token
     *
     * @param loginDTO 用户登录数据传输对象
     * @return Token
     */
    @Override
    public String login(LoginDTO loginDTO) {
        // Redis中获取验证码
        String redisCode = redisService.get(CAPTCHA_PREFIX + loginDTO.getTrack(), String.class);
        // 验证码是否过期
        if (!StringUtils.hasLength(redisCode)) {
            // 抛出验证码过期异常
            throw new BusinessException(CODE_EXPIRED);
        }
        // 判断验证码是否一致
        if (!redisCode.equals(loginDTO.getCode())) {
            // 抛出验证码错误异常
            throw new BusinessException(CODE_ERROR);
        }
        // 获取用户名和密码
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 数据库中查询对应的User对象
        User user = userMapper.getUserByUsername(username);
        // 判断当前用户是否存在
        if (null == user) {
            // 用户不存在
            throw new BusinessException(USER_NOT_EXIST);
        }
        // 判断用户是否禁用
        if (!user.getStatus()) {
            // 抛出用户已禁用异常
            throw new BusinessException(USER_ENABLE);
        }
        // 校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            // 抛出密码错误异常
            throw new BusinessException(PASSWORD_ERROR);
        }
        // 设置用户值
        setUserAttributeValue(user, loginDTO.getTrack());
        // 存在则将获取到的用户信息存储到Redis中，过期时间为两小时
        redisService.set(USER_PREFIX + loginDTO.getTrack(), user, TWO_HOUR);
        // 根据用户登录唯一标识生成Token
        return jwtTokenUtil.generateToken(loginDTO.getTrack());
    }

    /**
     * 设置用户属性值
     *
     * @param user 用户对象
     * @param track 用户登录唯一标识
     */
    private void setUserAttributeValue(User user, String track) {
        HttpServletRequest request = ServletUtil.getRequest();
        // 获取用户登录设备信息对象
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader(USER_AGENT));
        // 获取IP地址
        String ipAddress = IpUtil.getIpAddress(request);
        // Redis中不显示密码
        user.setPassword(null);
        // 设置当前用户登录时间
        user.setLoginTime(LocalDateTime.now());
        // 登录IP
        user.setIpAddress(ipAddress);
        // 登录位置
        user.setIpLocation(IpUtil.getIpLocation(ipAddress));
        // 用户唯一标识
        user.setTrack(track);
        // 操作系统
        user.setOs(userAgent.getOperatingSystem().getName());
        // 浏览器
        user.setBrowser(userAgent.getBrowser().getName());
    }

    /**
     * 更新用户个人信息
     *
     * @param userUpdateDTO 用户个人信息数据传输对象
     * @return 受影响的行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateLoginUser(UserUpdateDTO userUpdateDTO) {
        // 校验当期更新用户数据
        User user = checkUser(userUpdateDTO);
        // 更新用户对象
        int row = userMapper.updateById(user);
        if (row != 1) {
            // 抛出更新失败异常
            throw new BusinessException(UPDATE_ERROR);
        }
        // 刷新Redis中缓存的用户信息
        refreshUserCache(user);
        // 数据并返回受影响的行数
        return row;
    }

    /**
     * 刷新Redis中缓存的用户信息
     *
     * @param user 用户对象
     */
    private void refreshUserCache(User user) {
        // Redis中不展示密码
        user.setPassword(null);
        // 获取用户全局唯一标识
        String track = SecurityContext.getTrack();
        // 获取当前更新个人信息用户缓存的失效时间
        Long expire = redisService.getExpire(USER_PREFIX + track);
        // 删除Redis中缓存的用户个人信息
        redisService.del(USER_PREFIX + track);
        // 更新缓存中的用户个人信息，缓存失效时间不改变
        redisService.set(USER_PREFIX + track, user, expire);
    }

    /**
     * 更新用户密码
     *
     * @param passwordDTO 用户更新密码数据传递对象
     * @return 受影响行数
     */
    @Override
    public Integer updatePassword(PasswordDTO passwordDTO) {
        // TODO 短信验证码Key
        // Redis中邮件验证码Key
        String emailKey = CAPTCHA_EMAIL_PREFIX + passwordDTO.getUsername();
        // 判断Redis中的验证码是否过期
        if (!redisService.hasKey(emailKey)) {
            // 抛出验证码过期业务异常
            throw new BusinessException(CODE_EXPIRED);
        }
        // 验证码存在则获取到验证码
        String code = redisService.get(emailKey, String.class);
        // 对比当前验证码与用户输入验证码是否一致
        if (!code.equals(passwordDTO.getCode())) {
            // 抛出验证码错误业务异常
            throw new BusinessException(CODE_ERROR);
        }
        // 获取数据库中用户
        User user = userMapper.selectById(passwordDTO.getId());
        // 与原密码对比是否一致
        if (passwordEncoder.matches(passwordDTO.getPassword(), user.getPassword())) {
            // 抛出原密码和新密码不能相同业务异常被全局异常管理器捕获并返回前端
            throw new BusinessException(PASSWORD_SAME);
        }
        // 获取Redis中存储的Key
        String userKey = USER_PREFIX + SecurityContext.getTrack();
        // 更新密码
        user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
        // 更新用户更新时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新用户并返回受影响的行数
        int row = userMapper.updateById(user);
        // 根据受影响的行数判断是否更新成功
        if (row > 0) {
            // 清除Redis重新登录
            redisService.del(userKey);
            return row;
        }
        // 抛出业务异常被全局异常管理器捕获并返回前端
        throw new BusinessException(PASSWORD_UPDATE_ERROR);
    }

    /**
     * 更新登录用户头像
     *
     * @param avatarDTO 用户头像数据传输对象
     * @return 用户头像访问路径
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateLoginUserAvatar(AvatarDTO avatarDTO) {
        // 获取当前用户对象
        User user = userMapper.getUserByUserId(avatarDTO.getUserId());
        // 用户头像
        user.setAvatar(avatarDTO.getSrc());
        // 更新时间
        user.setUpdateTime(LocalDateTime.now());
        // 更新数据库中用户信息
        userMapper.updateUserAvatar(avatarDTO.getUserId(), avatarDTO.getSrc());
        // 刷新缓存中的用户
        refreshUserCache(user);
        // 将头像返回
        return avatarDTO.getSrc();
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

    /**
     * 获取指定用户
     *
     * @param id 用户ID
     * @return 用户信息返回对象
     */
    @Override
    public UserVO getUserById(Long id) {
        // 获取当前登录用户对象
        User user = userMapper.getUserByUserId(id);
        // 转换为用户数据返回对象
        UserVO userVO = BeanCopyUtil.copy(user, UserVO.class);
        // 单独定义用户角色（用户只有一个角色）
        userVO.setRoles(user.getRoles().get(0).getMark());
        // 返回用户登录对象
        return userVO;
    }

    /**
     * 新增用户
     *
     * @param userDTO 用户数据传输对象
     * @return 是否新增成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(UserDTO userDTO) {
        // 校验用户名合法性
        checkUsername(userDTO.getUsername());
        // 获取用户默认头像
        String avatar = configService.getConfig().getAvatar();
        User user = BeanCopyUtil.copy(userDTO, User.class);
        // ID
        user.setId(snowFlakeUtil.nextId());
        // 默认头像
        user.setAvatar(avatar);
        // 默认密码123456
        user.setPassword(passwordEncoder.encode(SecurityConst.DEFAULT_PASSWORD));
        // 用户默认角色，如果没有成功添加用户角色关联信息则抛出异常
        if (!userRoleService.setDefaultRole(user.getId())) {
            throw new BusinessException(INSERT_ERROR);
        }
        // TODO 后期添加手机号码时校验手机

        // 添加新用户到数据库，受影响行数是否为1
        return userMapper.insert(user) == 1;
    }

    /**
     * 更新指定用户信息
     *
     * @param userUpdateDTO 用户更新数据对象
     * @return 是否跟新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserUpdateDTO userUpdateDTO) {
        // 校验用户并返回用户信息
        User user = checkUser(userUpdateDTO);
        // 更新用户信息
        return userMapper.updateById(user) == 1;
    }

    /**
     * 校验并返回User对象
     *
     * @param userUpdateDTO 用户数据更新对象
     * @return 受影响的行数
     */
    private User checkUser(UserUpdateDTO userUpdateDTO) {
        // 先更新数据库，再更新Redis
        User user = userMapper.getUserByUserId(userUpdateDTO.getId());
        String username = userUpdateDTO.getUsername();
        // 如果修改用户名
        if (!user.getUsername().equals(username)) {
            // 校验修改后用户名的合法性
            checkUsername(username);
        }
        // 设置用户信息
        user.setNickname(userUpdateDTO.getNickname());
        user.setUsername(username);
        user.setRemark(userUpdateDTO.getRemark());
        user.setUpdateTime(LocalDateTime.now());
        // 更新并返回最新的User对象
        return user;
    }

    /**
     * 校验用户名合法性
     *
     * @param username 用户名
     */
    private void checkUsername(String username) {
        // 校验修改后用户名的合法性
        Long count = userMapper.selectCount(new QueryWrapper<User>().eq("username", username));
        // 校验修改内容的合法性，如果没有修改用户名则能查出1条
        if (null != count && count > 0) {
            // 确保用户名是唯一的
            throw new BusinessException(USERNAME_REPEAT);
        }
    }

}
