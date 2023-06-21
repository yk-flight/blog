package com.zrkizzy.server.aspect;

import cn.hutool.json.JSONUtil;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.CommonConst;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.utils.IpUtil;
import com.zrkizzy.common.utils.JsonUtil;
import com.zrkizzy.common.utils.ServletUtils;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.data.mapper.LoginInfoMapper;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.zrkizzy.common.constant.CommonConst.USER_AGENT;

/**
 * 登录日志AOP切面
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
@Aspect
@Component
public class LoginAspect {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    /**
     * 用于记录登录时间
     */
    private static final ThreadLocal<LocalDateTime> TIME_THREAD_LOCAL = new NamedThreadLocal<>("LoginTime");

    /**
     * 用于记录登录用户名称
     */
    private static final ThreadLocal<String> USERNAME_THREAD_LOCAL = new NamedThreadLocal<>("username");

    /**
     * 申明切入点位置
     */
    @Pointcut("@annotation(com.zrkizzy.common.annotation.LoginAnnotation)")
    public void controllerLog() {}

    /**
     * 处理登录请求前执行
     *
     * @param joinPoint 切入点
     */
    @Before("controllerLog()")
    public void beforeLogin(JoinPoint joinPoint) {
        // 用户输入参数仅有用户登录数据传输对象，因此直接获取即可
        LoginDTO loginDTO = JsonUtil.jsonToObject(JSONUtil.parse(joinPoint.getArgs()[0]).toString(), LoginDTO.class);
        // 设置用户登录名称
        if (null != loginDTO) {
            USERNAME_THREAD_LOCAL.set(loginDTO.getUsername());
        } else {
            USERNAME_THREAD_LOCAL.set(CommonConst.UNKNOWN_USER);
        }

        // 设置当前登录时间
        TIME_THREAD_LOCAL.set(LocalDateTime.now());
    }

    /**
     * 处理请求结束后执行方法
     *
     * @param joinPoint 切入点
     * @param jsonResult 返回结果
     */
    @AfterReturning(value = "controllerLog()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        // 记录登录日志信息
        handleLoginInfo(joinPoint, null, jsonResult);
    }

    @AfterThrowing(value = "controllerLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        // 记录登录日志信息
        handleLoginInfo(joinPoint, e, null);
    }

    /**
     * 添加登录日志到数据库中
     *
     * @param joinPoint 切入点
     * @param e 抛出异常
     * @param jsonResult 方法返回结果
     */
    protected void handleLoginInfo(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        // 定义登录日志对象
        LoginInfo loginInfo = new LoginInfo();
        try {
            // 转换结果对象
            Result<?> result = JsonUtil.jsonToObject(JSONUtil.parse(jsonResult).toString(), Result.class);
            // 如果没有登录成功
            if (null != result) {
                // 登录消息提示
                loginInfo.setMessage(String.valueOf(result.getMessage()));
                // 登录状态
                loginInfo.setStatus(result.getCode().equals(HttpStatusEnum.SUCCESS.getCode()) ? Boolean.TRUE : Boolean.FALSE);
            }
            // 判断是否出现异常
            if (null != e) {
                loginInfo.setStatus(Boolean.FALSE);
            }
            // 获取请求和响应
            HttpServletRequest request = ServletUtils.getRequest();
            // 登录IP
            String ip = IpUtil.getIpAddress(request);
            loginInfo.setLoginIp(ip);
            // IP属地
            loginInfo.setLoginLocation(IpUtil.getIpLocation(ip));
            // 获取用户登录设备信息对象
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader(USER_AGENT));
            // 浏览器版本
            loginInfo.setBrowser(userAgent.getBrowser().getName());
            // 操作系统
            loginInfo.setOs(userAgent.getOperatingSystem().getName());
            // 登录时间
            loginInfo.setLoginTime(TIME_THREAD_LOCAL.get());
            // ID
            loginInfo.setId(snowFlakeUtil.nextId());
            // 用户名
            loginInfo.setUsername(USERNAME_THREAD_LOCAL.get());

            // 存储当前登录日志到数据库中
            loginInfoMapper.insert(loginInfo);
        } catch (Exception exp) {
            exp.printStackTrace();
            // 抛出业务逻辑异常
            throw new BusinessException();
        } finally {
            // 移除当前线程中的变量
            TIME_THREAD_LOCAL.remove();
            USERNAME_THREAD_LOCAL.remove();
        }
    }

}
