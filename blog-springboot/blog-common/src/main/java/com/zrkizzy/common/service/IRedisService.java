package com.zrkizzy.common.service;

import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;

/**
 * Redis操作接口
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
public interface IRedisService {

    /**
     * 设置键值对
     *
     * @param key 键
     * @param value 值
     * @param time 超时时间
     */
    void set(String key, Object value, long time);

    /**
     * 设置键值对（永不过期）
     *
     * @param key 键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 获取键值对
     *
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 获取指定类型的键值对
     *
     * @param key 键
     * @param clazz 指定类型
     * @return 值
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 删除键值对操作
     *
     * @param key 键
     * @return 是否删除成功
     */
    Boolean del(String key);

    /**
     * 递减对应Key的值
     *
     * @param key 键
     * @return 递减后的值
     */
    Long decrement(String key);

    /**
     * 递增对应key的值
     *
     * @param key 键
     * @return 递增后的值
     */
    Long increment(String key);

    /**
     * 判断Redis中是否存在指定键
     *
     * @param key 键
     * @return 对应键是否存在
     */
    Boolean hasKey(String key);

    /**
     * Redis执行Lua脚本
     *
     * @param redisScript Lua脚本
     * @param keyList Redis中Key值队列
     * @param objects 多个参数
     * @return <T> Lua脚本返回值
     */
    <T> T execute(RedisScript<T> redisScript, List<String> keyList, Object... objects);
}