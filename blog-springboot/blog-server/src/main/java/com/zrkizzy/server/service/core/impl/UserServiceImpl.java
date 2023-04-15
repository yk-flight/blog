package com.zrkizzy.server.service.core.impl;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.common.utils.JwtTokenUtil;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.data.mapper.UserMapper;
import com.zrkizzy.security.util.UserDetailUtil;
import com.zrkizzy.server.service.core.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.zrkizzy.common.constant.RedisConst.CAPTCHA_PREFIX;
import static com.zrkizzy.common.constant.RedisConst.USER_PREFIX;
import static com.zrkizzy.common.constant.TimeConst.TWO_HOUR;
import static com.zrkizzy.common.enums.HttpStatusEnum.CODE_ERROR;
import static com.zrkizzy.common.enums.HttpStatusEnum.CODE_EXPIRED;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private IRedisService redisService;

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
        String redisCode = redisService.get(CAPTCHA_PREFIX, String.class);
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
        // 存在则将获取到的用户信息存储到Redis中，过期时间为两小时
        redisService.set(USER_PREFIX + username, user, TWO_HOUR);
        // 根据用户详细信息生成Token
        return Result.success("登录成功",
                jwtTokenUtil.generateToken(userDetailUtil.convertUserDetails(user)));
    }

}
