package com.zrkizzy.server.aspect;

import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.Operate;
import com.zrkizzy.data.mapper.OperateMapper;
import com.zrkizzy.security.util.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 操作日志AOP切面
 *
 * @author zhangrongkang
 * @since 2023/6/25
 */
@Aspect
@Component
public class OperateAspect {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private OperateMapper operateMapper;

    /**
     * 记录用户操作时间
     */
    private static final ThreadLocal<LocalDateTime> TIME_THREAD_LOCAL = new NamedThreadLocal<>("OperateTime");

    /**
     * 记录操作用户
     */
    private static final ThreadLocal<Long> USER_THREAD_LOCAL = new NamedThreadLocal<>("OperateUser");

    /**
     * 声明切入点位置
     */
    @Pointcut("@annotation(com.zrkizzy.common.annotation.OperateAnnotation)")
    public void operateCut() {

    }

    /**
     * 操作日志AOP切入点
     *
     * @param joinPoint 切入点
     */
    @Before("operateCut()")
    public void handleBefore(JoinPoint joinPoint) {
        System.out.println("进入操作日志AOP切面");
        // 记录操作时间
        TIME_THREAD_LOCAL.set(LocalDateTime.now());
        // 记录操作用户ID
        USER_THREAD_LOCAL.set(securityUtil.getLoginUser().getId());
        // 操作参数
    }

    @AfterReturning(value = "operateCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        // 记录操作日志信息
        handleOperateInfo(joinPoint, null, jsonResult);
    }

    @AfterThrowing(value = "operateCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        // 记录操作日志信息
        handleOperateInfo(joinPoint, e, null);
    }

    /**
     * 添加操作日志信息到数据库中
     *
     * @param joinPoint 切入点
     * @param e 操作异常
     * @param jsonResult 返回结果
     */
    protected void handleOperateInfo(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        // 定义操作日志对象
        Operate operate = new Operate();
        System.out.println("AOP切面结束处理方法");
    }

}
