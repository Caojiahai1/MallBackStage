package com.mailbackstage.common.redis;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
public interface RedisService {
    void set(String key, String value);

    String get(String key);

    boolean remove(String key);

    //设置超时时间
    boolean expire(String key, long expire);

    //自增操作
    Long increment(String key, long delta);
}