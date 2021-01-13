package com.teahel.tneed.common;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * redis工具类
 *
 * @author ltj
 * @date 2019/10/17 18:02
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    private final static Gson GSON = new Gson();

    /**
     * 保存键值对
     *
     * @param key    主键
     * @param value  值
     * @param expire 过期时间
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 保存键值对（未设置过期时间）
     *
     * @param key   主键
     * @param value 值
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 通过主键获取值
     *
     * @param key    主键
     * @param clazz  bean类型
     * @param expire 过期时间
     * @param <T>    泛型
     * @return 用bean对象接收的数据
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return fromJson(value, clazz);
    }

    /**
     * 通过主键获取值
     *
     * @param key   主键
     * @param clazz bean类型
     * @param <T>   泛型
     * @return 用bean对象接收的数据
     */
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 通过主键获取值
     *
     * @param key    主键
     * @param expire 过期时间
     * @return 用bean对象接收的数据
     */
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 通过主键获取值
     *
     * @param key 主键
     * @return json值
     */
    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 通过主键删除值
     *
     * @param key 主键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return GSON.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * list顺序插入子级
     *
     * @param key   标识
     * @param value 值
     */
    public void leftPush(String key, Object value) {
        listOperations.leftPush(key, value);
    }

    /**
     * list顺序插入子级
     *
     * @param key    标识
     * @param values 值
     */
    public void leftPushAll(String key, Object... values) {
        for (Object value : values) {
            listOperations.leftPush(key, value);
        }
    }

    /**
     * list顺序插入子级
     *
     * @param key    标识
     * @param values 值
     */
    public <T> void leftPushAll(String key, Collection<T> values) {
        for (Object value : values) {
            listOperations.leftPush(key, value);
        }
    }

    /**
     * 顺序去先插入的数据
     *
     * @param key   标识
     * @param clazz bean实体
     * @param <T>   泛型
     * @return bean接收的相关数据
     */
    public <T> T leftPop(String key, Class<T> clazz) {
        Object value = listOperations.leftPop(key);
        return GSON.fromJson(value.toString(), clazz);
    }

    /**
     * 顺序去先插入的数据
     *
     * @param key 标识
     * @return list的栈顶的数据
     */
    public Object leftPop(String key) {
        return listOperations.leftPop(key);
    }

    /**
     * 顺序去先插入的数据
     *
     * @param key 标识
     * @return list的栈顶的数据
     */
    public Object rightPop(String key) {
        return listOperations.rightPop(key);
    }

    /**
     * 顺序去先插入的数据
     *
     * @param key   标识
     * @param clazz bean实体
     * @param <T>   泛型
     * @return bean接收的相关数据
     */
    public <T> T rightPop(String key, Class<T> clazz) {
        Object value = listOperations.rightPop(key);
        if (null == value) {
            return null;
        }
        return GSON.fromJson(value.toString(), clazz);
    }

    /**
     * 简单的往数组里面添加元素
     *
     * @param key   键值
     * @param value 数据
     */
    public int lPush(String key, String value) {
        return listOperations.leftPush(key, value).intValue();
    }

    /**
     * 批量往数组里面添加元素
     *
     * @param key  键值
     * @param list 数据
     */
    public <T> int lPushAll(String key, Collection<T> list) {
        return listOperations.leftPushAll(key, list).intValue();
    }

    /**
     * 对指定下标的数组元素进行替换
     *
     * @param key    键值
     * @param offset 下标
     * @param value  值
     */
    public void set(String key, int offset, String value) {
        listOperations.set(key, offset, value);
    }

    /**
     * 对指定下标的数组进行插入数据
     *
     * @param key    键值
     * @param value1 原有值
     * @param value2 插入的值
     */
    public int insert(String key, String value1, String value2) {
        return listOperations.leftPush(key, value1, value2).intValue();
    }

    /**
     * 获取指定下标的数组元素
     *
     * @param key    键值
     * @param offset 下标
     */
    public Object getValueByIndex(String key, long offset) {
        return listOperations.index(key, offset);
    }

    /**
     * 获取数组长度
     *
     * @param key 键值
     */
    public Long size(String key) {
        return listOperations.size(key);
    }

    /**
     * 移除数组匹配到的数据元素
     *
     * @param key   键值
     * @param count 负数：从右往左 整数：从左往右
     * @param value 移除的值
     */
    public int remove(String key, long count, String value) {
        return listOperations.remove(key, count, value).intValue();
    }

    /**
     * 保留区间内的元素，区间外的全部删除
     *
     * @param key   键值
     * @param start 区间开始
     * @param end   区间结束
     */
    public void trim(String key, int start, int end) {
        listOperations.trim(key, start, end);
    }

    /**
     * 从左到右，删除第一个元素
     *
     * @param key 键值
     */
    public Object lpop(String key) {
        return listOperations.leftPop(key);
    }

    /**
     * 查询区间范围内的元素
     *
     * @param key   键值
     * @param start 开始
     * @param end   结束
     */
    public List<Object> lrange(String key, int start, int end) {
        return listOperations.range(key, start, end);
    }
}