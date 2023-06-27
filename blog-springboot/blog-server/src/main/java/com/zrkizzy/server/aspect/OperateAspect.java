package com.zrkizzy.server.aspect;

import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.mapper.OperateMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
    private static final ThreadLocal<String> NAME_THREAD_LOCAL = new NamedThreadLocal<>("OperateUser");

    /**
     * 声明切入点位置
     */
    @Pointcut("@annotation(com.zrkizzy.common.annotation.OperateAnnotation)")
    public void operateCut() {

    }



}
