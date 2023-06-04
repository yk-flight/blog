package com.zrkizzy.security.util;

import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.data.domain.User;
import com.zrkizzy.security.entity.MyUserDetails;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.zrkizzy.common.constant.CommonConst.USER_AGENT;
import static com.zrkizzy.common.constant.RedisConst.USER_PREFIX;
import static com.zrkizzy.common.constant.SecurityConst.ADMIN;

/**
 * Security数据工具类
 *
 * @author zhangrongkang
 * @since 2023/4/17
 */
@Component
public class SecurityUtil {
    @Autowired
    private IRedisService redisService;

    /**
     * 获取当前登录的用户
     *
     * @return 登录用户对象
     */
    public User getLoginUser() {
        // 从载荷中获取用户对象
        User user = getUserDetails().getUser();
        if (null == user) {
            // 如果载荷中为空则去Redis中获取
            return redisService.get(USER_PREFIX + getLoginUsername(), User.class);
        }
        return user;
    }

    /**
     * 获取当前登录用户的用户名
     *
     * @return 用户名
     */
    public String getLoginUsername() {
        return getUserDetails().getUsername();
    }

    /**
     * 获取当前登录用户角色
     *
     * @return 用户角色
     */
    public String getLoginUserRole() {
        return getUserDetails().getRoleMark();
    }

    /**
     * 获取当前登录用户的角色标识
     *
     * @return 角色标识
     */
    public String getLoginUserRoleMark() {
        return getUserDetails().getRoleMark();
    }

    /**
     * 获取当前登录用户的角色名称
     *
     * @return 角色名称
     */
    public String getLoginUserRoleName() {
        return getUserDetails().getRoleName();
    }

    /**
     * 获取当前登录的用户昵称
     *
     * @return 用户昵称
     */
    public String getLoginUserNickname() {
        return getLoginUser().getNickname();
    }

    /**
     * 获取用户登录设备信息
     *
     * @return 用户登录设备信息（操作系统 浏览器版本）
     */
    public String getUserAgent(HttpServletRequest request) {
        // 获取用户登录设备信息对象
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader(USER_AGENT));
        return userAgent.getOperatingSystem() + " " + userAgent.getBrowser();
    }

    /**
     * 判断当前登录用户是否为管理员
     *
     * @return true：管理员；false：不是管理员
     */
    public Boolean isAdmin() {
        String role = getLoginUserRole();
        return role != null && role.equals(ADMIN);
    }

    /**
     * 获取用户信息对象
     *
     * @return 用户信息对象
     */
    private MyUserDetails getUserDetails() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
