package com.zrkizzy.server.aspect;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.zrkizzy.common.annotation.OperateAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.exception.BusinessException;
import com.zrkizzy.common.utils.JsonUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.Operate;
import com.zrkizzy.data.mapper.OperateMapper;
import com.zrkizzy.security.util.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new NamedThreadLocal<>("OperateTime");

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
    @Before(value = "operateCut()")
    public void handleBefore(JoinPoint joinPoint) {
        System.out.println("进入操作日志AOP切面");
        // 记录操作时间
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
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
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取操作的方法
            Method method = signature.getMethod();
            // 通过执行的方法得到自定义注解
            OperateAnnotation operateAnnotation = method.getAnnotation(OperateAnnotation.class);
            // 操作模块
            operate.setModuleName(operateAnnotation.module());
            // 操作类型
            operate.setType(Byte.valueOf(operateAnnotation.type()));

            // 转换结果对象
            Result<?> result = JsonUtil.jsonToObject(JSONUtil.parse(jsonResult).toString(), Result.class);

            if (null != result) {
                // 操作结果
                operate.setStatus(Boolean.TRUE);
                // 设置操作结果
                operate.setOperateResult(result.getMessage());
            }

            if (null != e) {
                // 设置请求状态为失败
                operate.setStatus(Boolean.FALSE);
                // 设置返回消息
                operate.setOperateResult(e.getMessage());
            }

            // 获取操作参数
            operate.setOperateParam(JSON.toJSONString(joinPoint.getArgs()));

            System.out.println(operate);

        } catch (Exception exp) {
            exp.printStackTrace();
            // 抛出业务逻辑异常
            throw new BusinessException();
        } finally {
            TIME_THREAD_LOCAL.remove();
        }

    }

}
