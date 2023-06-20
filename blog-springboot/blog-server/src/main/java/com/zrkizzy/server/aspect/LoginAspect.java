package com.zrkizzy.server.aspect;

import cn.hutool.json.JSONUtil;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.service.IRedisService;
import com.zrkizzy.common.utils.JsonUtil;
import com.zrkizzy.common.utils.ServletUtils;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.dto.LoginDTO;
import com.zrkizzy.data.mapper.LoginInfoMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    private LoginInfoMapper loginLogMapper;

    @Autowired
    private IRedisService redisService;

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
        System.out.println("获取参数: ----------------------------------");
        for (Object arg : joinPoint.getArgs()) {
            System.out.println(arg);
        }
        // 获取用户登录时的输入对象
        LoginDTO loginDTO = JsonUtil.jsonToObject(JSONUtil.parse(joinPoint).toString(), LoginDTO.class);
        // 设置当前用户登录名称
        USERNAME_THREAD_LOCAL.set(loginDTO.getUsername());
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

    /**
     * 添加登录日志到数据库中
     *
     * @param joinPoint 切入点
     * @param e 抛出异常
     * @param jsonResult 方法返回结果
     */
    protected void handleLoginInfo(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        // 定义登录日志对象
        LoginInfo loginLog = new LoginInfo();
        try {
            // 转换结果对象
            Result<?> result = JsonUtil.jsonToObject(JSONUtil.parse(jsonResult).toString(), Result.class);
            System.out.println(result);
            // 如果没有登录成功
            if (null != result && !result.getCode().equals(HttpStatusEnum.SUCCESS.getCode())) {
                System.out.println(ServletUtils.getRequest());
                // 登录消息提示
                loginLog.setMessage(String.valueOf(result.getMessage()));
                // 登录状态
                loginLog.setStatus(Boolean.FALSE);
            }
            // 登录成功后用户ID已经存储到了Redis中
//            loginLog.setUserId()

            // 登录IP
            // IP属地
            // 浏览器版本
            // 操作系统
            // 登录时间
            loginLog.setLoginTime(TIME_THREAD_LOCAL.get());
            System.out.println(loginLog);
        } catch (Exception exp) {

        } finally {
            // 移除当前线程中的变量
            TIME_THREAD_LOCAL.remove();
        }
    }

}
