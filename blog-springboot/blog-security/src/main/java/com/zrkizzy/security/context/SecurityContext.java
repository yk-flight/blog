package com.zrkizzy.security.context;

/**
 * 系统权限上下文
 * 
 * @author zhangrongkang
 * @since 2023/7/10
 */
public class SecurityContext {
    
    /**
     * 存储当前请求用户的唯一值
     */
    private static final ThreadLocal<String> TRACK_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前用户唯一标识
     *
     * @param track 用户唯一标识
     */
    public static void setTrack(String track) {
        TRACK_THREAD_LOCAL.set(track);
    }

    /**
     * 获取当前用户唯一标识
     *
     * @return 用户唯一标识
     */
    public static String getTrack() {
        return TRACK_THREAD_LOCAL.get();
    }

    /**
     * 清空当前线程中所有变量
     */
    public static void remove() {
        // 清除用户唯一标识
        TRACK_THREAD_LOCAL.remove();
    }
    
}
