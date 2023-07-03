package com.zrkizzy.common.context;

/**
 * 系统变量上下文
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
public class SystemContext {

    /**
     * 存储当前请求模块ID
     */
    private static final ThreadLocal<Long> MODULE_ID_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 记录用户操作时间
     */
    private static final ThreadLocal<Long> OPERATE_TIME_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前请求开始时间戳
     *
     * @param startTime 开始操作时间戳
     */
    public static void setOperateStartTime(Long startTime) {
        OPERATE_TIME_THREAD_LOCAL.set(startTime);
    }

    /**
     * 获取当前请求开始执行时间戳
     *
     * @return 请求开始执行时间戳
     */
    public static Long getOperateStartTime() {
        return OPERATE_TIME_THREAD_LOCAL.get();
    }

    /**
     * 设置当前请求对应的模块ID
     *
     * @param moduleId 模块ID
     */
    public static void setModuleId(Long moduleId) {
        MODULE_ID_THREAD_LOCAL.set(moduleId);
    }

    /**
     * 获取当前请求的模块ID
     *
     * @return 模块ID
     */
    public static Long getModuleId() {
        return MODULE_ID_THREAD_LOCAL.get();
    }

    /**
     * 清空当前线程中所有变量
     */
    public static void remove() {
        // 清除请求模块变量
        MODULE_ID_THREAD_LOCAL.remove();
        // 清除方法操作时间变量
        OPERATE_TIME_THREAD_LOCAL.remove();
    }
}
