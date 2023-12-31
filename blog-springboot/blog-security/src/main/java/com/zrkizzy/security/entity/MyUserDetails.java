package com.zrkizzy.security.entity;

import com.zrkizzy.data.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 自定义用户身份验证逻辑
 *
 * @author zhangrongkang
 * @since 2023/3/10
 */
@Component
public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails() {
    }

    /**
     * 将User对象转为UserDetails对象
     *
     * @param user 用户自定义User
     */
    public void convertUser(User user) {
        this.user = user;
    }

    /**
     * 获取用户权限
     *
     * @return 用户具有的权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getMark()))
                .collect(Collectors.toList());
    }

    /**
     * 获取登录的用户对象
     *
     * @return 用户对象
     */
    public User getUser() {
        return user;
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public Long getUserId() {
        return user.getId();
    }

    /**
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getRoleName() {
        return user.getRoles().get(0).getName();
    }

    /**
     * 获取角色标识
     *
     * @return 角色标识
     */
    public String getRoleMark() {
        return user.getRoles().get(0).getMark();
    }

    /**
     * 返回用户密码
     *
     * @return 密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 返回用户的用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 用户的账户是否未过期
     *
     * @return true：未过期，false：已过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户的账户是否未被锁定
     *
     * @return true：未锁定，false：已锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户的凭证是否未过期
     *
     * @return true：未过期，false：已过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否启用
     *
     * @return true：启用，false：未启用
     */
    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }

}
