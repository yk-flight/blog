package com.zrkizzy.server.aspect;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.context.SystemContext;
import com.zrkizzy.common.utils.IpUtil;
import com.zrkizzy.common.utils.JsonUtil;
import com.zrkizzy.common.utils.ServletUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.mapper.OperateLogMapper;
import com.zrkizzy.security.util.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 操作日志AOP切面
 *
 * @author zhangrongkang
 * @since 2023/6/25
 */
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private OperateLogMapper operateLogLogMapper;

    /**
     * 声明切入点位置
     */
    @Pointcut("@annotation(com.zrkizzy.common.annotation.OperateLogAnnotation)")
    public void operateLogCut() {
    }

    /**
     * 操作日志AOP切入点
     *
     * @param joinPoint 切入点
     */
    @Before(value = "operateLogCut()")
    public void handleBefore(JoinPoint joinPoint) {
        // 记录操作时间
        SystemContext.setOperateStartTime(System.currentTimeMillis());
    }

    @AfterReturning(value = "operateLogCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        // 记录操作日志信息
        handleOperateInfo(joinPoint, null, jsonResult);
    }

    @AfterThrowing(value = "operateLogCut()", throwing = "e")
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
        OperateLog operateLog = new OperateLog();
        // 当前方法消耗时间
        operateLog.setCostTime(System.currentTimeMillis() - SystemContext.getOperateStartTime());
        try {
            // 设置本次操作日志信息
            setOperateInfo(operateLog, joinPoint);
            // 转换结果对象
            Result<?> result = JsonUtil.jsonToObject(JSONUtil.parse(jsonResult).toString(), Result.class);
            // 设置操作结果
            if (null != result) {
                // 操作结果
                operateLog.setStatus(Boolean.TRUE);
                // 设置操作结果
                operateLog.setOperateResult(result.getMessage());
            }
            // 判断本次请求是否成功
            if (null != e) {
                // 设置请求状态为失败
                operateLog.setStatus(Boolean.FALSE);
                // 设置返回消息
                operateLog.setOperateResult(e.getMessage());
            }
            // 操作用户
            operateLog.setUserId(securityUtil.getLoginUser().getId());

            // 获取当前线程的请求
            HttpServletRequest request = ServletUtil.getRequest();

            // 请求方式
            operateLog.setRequestMethod(request.getMethod());
            // 当前请求IP地址
            String ip = IpUtil.getIpAddress(request);
            // 操作IP
            operateLog.setOperateIp(ip);
            // IP属地
            operateLog.setOperateLocation(IpUtil.getIpLocation(ip));
            // ID
            operateLog.setId(snowFlakeUtil.nextId());
            // 请求所属模块ID
            operateLog.setModuleId(SystemContext.getModuleId());

            // 添加当前操作信息到数据库中
            operateLogLogMapper.insert(operateLog);

        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            // 清除系统变量中的数据
            SystemContext.remove();
        }

    }

    /**
     * 设置当前操作对象信息
     *
     * @param operateLog 操作对象
     * @param joinPoint 连接点
     */
    private void setOperateInfo(OperateLog operateLog, JoinPoint joinPoint) {
        // 操作参数
        operateLog.setOperateParam(JSON.toJSONString(joinPoint.getArgs()));
        // 获取连接点的方法签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取操作的方法
        Method method = signature.getMethod();
        // 通过执行的方法得到自定义注解
        OperateLogAnnotation operateLogAnnotation = method.getAnnotation(OperateLogAnnotation.class);
        // 操作类型
        operateLog.setType(Byte.valueOf(operateLogAnnotation.type()));

        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 操作方法名称
        operateLog.setMethodName(className + "." + signature.getName());

    }

}
