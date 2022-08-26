package com.saki.work.common.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lzh
 * @date 2020/1/27 - 17:52
 */
@Component
public class RedisUtil {
    public static final long willNotTimeOut = -1;

    public static final long defaultExpireTime = 60 * 60 * 24 * 1;

    public static final long oneDayExpireTime = 60 * 60 * 24 * 1;

    public static final long twoMinutesExpireTime = 60 * 2;

    public static final long fiveMinutesExpireTime = 60 * 5;


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {

        Boolean ret = redisTemplate.expire(key, timeout, unit);
        return ret != null && ret;
    }

    /**
     * 删除单个key
     *
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    public boolean delKey(final String key) {

        Boolean ret = redisTemplate.delete(key);
        return ret != null && ret;
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    public long delKeys(final Collection<String> keys) {

        Long ret = redisTemplate.delete(keys);
        return ret == null ? 0 : ret;
    }

    /**
     * 存入普通对象
     *
     * @param key   Redis键
     * @param value 值
     */
    public void setValue(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, value, 1, TimeUnit.MINUTES);
    }

    // 存储普通对象操作

    /**
     * 存入普通对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效期，单位秒
     */
    public void setValueTimeout(final String key, final Object value, final long timeout) {

        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 增量
     *
     * @param key   键
     * @param value 值
     */
    public long increment(final String key, final long value) {

        Long increment = redisTemplate.opsForValue().increment(key, value);
        if (increment == null) {
            increment = 1L;
        }
        return increment;
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    public Object getValue(final String key) {

        return redisTemplate.opsForValue().get(key);
    }

    // 存储Hash操作

    /**
     * 确定哈希hashKey是否存在
     *
     * @param key  键
     * @param hkey hash键
     * @return true=存在；false=不存在
     */
    public boolean hasHashKey(final String key, String hkey) {

        Boolean ret = redisTemplate.opsForHash().hasKey(key, hkey);
        return ret != null && ret;
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public void hashPut(final String key, final String hKey, final Object value) {

        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key    Redis键
     * @param values Hash键值对
     */
    public void hashPutAll(final String key, final Map<String, Object> values) {

        redisTemplate.opsForHash().putAll(key, values);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public Object hashGet(final String key, final String hKey) {

        return redisTemplate.opsForHash().get(key, hKey);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @return Hash对象
     */
    public Map<Object, Object> hashGetAll(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public List<Object> hashMultiGet(final String key, final Collection<Object> hKeys) {

        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public long hashDeleteKeys(final String key, final String... hKeys) {
        return redisTemplate.opsForHash().delete(key, hKeys);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param increment
     * @return
     */
    public Long hashIncrement(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, String.valueOf(field), increment);
    }

    // 存储Set相关操作

    /**
     * 往Set中存入数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 存入的个数
     */
    public long setSet(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 获取ZSet元素索引
     *
     * @param key
     * @param value
     * @return
     */
    public Long zsetRank(String key, Object value) {
        Long rank = redisTemplate.opsForZSet().rank(key, value);
        return rank == null ? -1 : rank;
    }

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    public long setDel(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 获取set中的所有对象
     *
     * @param key Redis键
     * @return set集合
     */
    public Set<Object> setGetAll(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获取set size
     *
     * @param key Redis键
     * @return set size
     */
    public Long setGetSize(final String key) {
        return redisTemplate.opsForSet().size(key);
    }
    // 存储ZSet相关操作

    /**
     * 往ZSet中存入数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 存入的个数
     */
    public long zsetSet(final String key, final Set<ZSetOperations.TypedTuple<Object>> values) {
        Long count = redisTemplate.opsForZSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往ZSet中存入数据
     *
     * @param key   Redis键
     * @param value 值
     * @return 存入的个数
     */
    public boolean zsetSet(final String key, final Object value, final double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 增加分数
     *
     * @param key   Redis键
     * @param value 值
     * @return 存入的个数
     */
    public Double zsetIncrementScore(final String key, final Object value, final double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }


    /**
     * 删除ZSet中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    public long zsetDel(final String key, final Set<ZSetOperations.TypedTuple<Object>> values) {
        Long count = redisTemplate.opsForZSet().remove(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除ZSet中的数据
     *
     * @param key   Redis键
     * @param value 值
     * @return 移除的个数
     */
    public long zsetDel(final String key, final Object value) {
        Long count = redisTemplate.opsForZSet().remove(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 获取zset size
     *
     * @param key Redis键
     * @return set size
     */
    public Long zsetGetSize(final String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 指定范围内元素排序
     *
     * @param key
     * @param minValue
     * @param maxValue
     * @return
     */
    public Set<Object> zsetRangeByScore(String key, double minValue, double maxValue, long offset, long count) {

        return redisTemplate.opsForZSet().rangeByScore(key, minValue, maxValue, offset, count);
    }
    /**
     * 指定范围内元素排序
     *
     * @param key
     *
     * @return
     */
    public Set<Object> zsetReverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key,start,end);
    }

    /**
     * 获取元素分数
     *
     * @param key
     * @return
     */
    public Double zsetElementScore(String key, Object value) {
        Double score = redisTemplate.opsForZSet().score(key, value);
        return score == null ? 0 : score;
    }
    // 存储List相关操作

    /**
     * 往List中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public long listPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public long listPushAll(final String key, final Collection<Object> values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public long listPushAll(final String key, final Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public List<Object> listGet(final String key, final int start, final int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


}
