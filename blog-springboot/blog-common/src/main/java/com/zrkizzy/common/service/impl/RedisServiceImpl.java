package com.zrkizzy.common.service.impl;

import com.zrkizzy.common.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作接口实现类
 *
 * @author zhangrongkang
 * @since 2023/3/9
 */
@Service
@Slf4j
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置键值对
     *
     * @param key   键
     * @param value 值
     * @param time  超时时间（秒）
     */
    @Override
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 设置键值对（永不过期）
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取键值对
     *
     * @param key 键
     * @return 值
     */
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取键值对
     *
     * @param key 键
     * @return 值
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(redisTemplate.opsForValue().get(key));
    }

    /**
     * 删除键值对操作
     *
     * @param key 键
     * @return 是否删除成功
     */
    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 判断键是否存在
     *
     * @param key 键
     * @return 对应键是否存在
     */
    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * Redis执行Lua脚本
     *
     * @param redisScript Lua脚本
     * @param keyList     Redis中Key值队列
     * @param objects     多个参数
     * @return <T> Lua脚本返回值
     */
    @Override
    public <T> T execute(RedisScript<T> redisScript, List<String> keyList, Object... objects) {
        return redisTemplate.execute(redisScript, keyList, objects);
    }

    /**
     * 获取指定前缀的所有Key
     *
     * @param pattern 指定前缀
     * @return 所有指定前缀开头的key
     */
    @Override
    public Set<String> scanKeys(String pattern) {
        // 默认返回所有符合条件的Key
        return scanKeys(pattern, null);
    }

    /**
     * 获取指定前缀与数量的Key
     *
     * @param pattern 指定前缀
     * @param count 返回key的数量
     * @return 指定数量的Key
     */
    @Override
    public Set<String> scanKeys(String pattern, Integer count) {
        // 默认情况下仅定义前缀
        ScanOptions options = ScanOptions.scanOptions().match(pattern).build();
        if (null != count) {
            ScanOptions.scanOptions()
                    // 前缀
                    .match(pattern)
                    // 每次返回数量
                    .count(count).build();
        }
        // 调用execute接口来返回对应数据
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            // 定义Set集合
            Set<String> result = new HashSet<>();
            // 设置参数开始执行SCAN操作并返回结果
            Cursor<byte[]> cursor = connection.scan(options);
            // 遍历返回的结果并添加到Set集合中
            while (cursor.hasNext()) {
                result.add(new String(cursor.next()));
            }
            return result;
        });
    }

    /**
     * 递减对应Key的值
     *
     * @param key 键
     * @return 递减后的值
     */
    @Override
    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 递增对应key的值
     *
     * @param key 键
     * @return 递增后的值
     */
    @Override
    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 获取Key值的失效时间
     *
     * @param key 键
     * @return 失效时间（秒）
     */
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}