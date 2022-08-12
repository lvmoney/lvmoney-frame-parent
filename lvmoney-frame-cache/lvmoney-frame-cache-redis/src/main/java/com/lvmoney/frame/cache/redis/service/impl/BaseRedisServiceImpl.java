/**
 * 描述:
 * 包名:com.lvmoney.redis.service
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:18:00
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.cache.redis.service.impl;

import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cache.common.annotations.CacheService;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.exception.CacheException;
import com.lvmoney.frame.base.core.vo.CorePage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月7日 下午5:18:00
 */

@SuppressWarnings("rawtypes")
@CacheService(CacheConstant.CACHE_REDIS)
public class BaseRedisServiceImpl extends BaseRedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRedisServiceImpl.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;

    @Override
    public void setString(String key, Object object, Long time) {
        setString(key, object);
        // 设置有效期
        if (time != null && time > 0L) {
            this.setExpire(key, time);
        }
    }

    @Override
    public void setString(String key, Object object) {
        if (object instanceof String) {
            stringRedisTemplate.opsForValue().set(key, object.toString());
        } else {
            String value = JsonUtil.t2JsonString(object);
            // 存放string类型
            stringRedisTemplate.opsForValue().set(key, value);
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public void setSet(String key, Object object) {
        Set<String> valueSet = (Set<String>) object;
        for (String string : valueSet) {
            stringRedisTemplate.opsForSet().add(key, string);
        }
    }

    @Override
    public void setExpire(String key, Long time) {
        stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Object getByKey(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            LOGGER.error("从redis中获取数据报错:{}", e);
            throw new BusinessException(CacheException.Proxy.REDIS_NOT_EXIST);
        }
    }

    @Override
    public void deleteByKey(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void deleteByWildcardKey(String key) {
        Set<Object> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
    }

    @Override
    public CorePage getListPage(CorePage corePage, String key) {
        if (corePage.isAll()) {
            List list = this.getListAll(key);
            Long total = this.getListSize(key);
            corePage.setData(list);
            corePage.setTotal(total);
            return corePage;
        }
        if (StringUtils.isBlank(key)) {
            throw new BusinessException(CacheException.Proxy.REDIS_KEY_IS_REQUIRED);
        }
        int pageNo = corePage.getPageNo();
        int pageSize = corePage.getPageSize();
        int start = (pageNo - 1) * pageSize;
        int end = pageSize * pageNo - 1;
        List list = redisTemplate.opsForList().range(key, start, end);
        Long total = this.getListSize(key);
        corePage.setData(list);
        corePage.setTotal(total);
        return corePage;
    }


    @Override
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public void rmValueByList(String key, Long count, Object obj) {
        redisTemplate.opsForList().remove(key, count, obj);
    }

    @Override
    public Long getMapSize(String key) {
        return Long.parseLong(String.valueOf(redisTemplate.opsForHash().values(key).size()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addList(String key, List obj, Long time) {
        redisTemplate.opsForList().rightPushAll(key, obj);
        if (time != null && time > 0L) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }

    }

    @Override
    public void addList(String key, List obj) {
        redisTemplate.opsForList().rightPushAll(key, obj);
    }

    @Override
    public void addMap(String key, Map obj, Long time) {
        redisTemplate.opsForHash().putAll(key, obj);
        if (time != null && time > 0L) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }


    @Override
    public Object getByMapKey(String key, String mapKey) {
        return redisTemplate.opsForHash().get(key, mapKey);
    }

    @Override
    public Set<String> getKeysByWildcard(String wildcard) {
        Set<String> keys = new HashSet<>();
        this.scan(wildcard, item -> {
            //符合条件的key
            String key = new String(item, StandardCharsets.UTF_8);
            keys.add(key);
        });
        return keys;
    }

    @Override
    public Map<Object, Object> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public boolean isExist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    private void scan(String pattern, Consumer<byte[]> consumer) {
        this.stringRedisTemplate.execute((RedisConnection connection) -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(pattern).build())) {
                cursor.forEachRemaining(consumer);
                return null;
            } catch (IOException e) {
                LOGGER.error("通过scan命令获得keys报错:{}", e);
                throw new BusinessException(CacheException.Proxy.SCAN_COMMAND_ERROR);
            }
        });
    }


    @Override
    public Object getMapByKey(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Override
    public CorePage getValueByKey(CorePage corePage, String key) {
        if (corePage.isAll()) {
            corePage.setTotal(this.getMapSize(key));
            corePage.setData(redisTemplate.opsForHash().values(key));
            return corePage;
        } else {
            Long total = this.getMapSize(key);
            corePage.setTotal(total);
            int pageNo = corePage.getPageNo();
            int pageSize = corePage.getPageSize();
            int start = (pageNo - 1) * corePage.getPageSize();
            int end = pageNo * pageSize;
            if (start > total) {
                corePage.setData(null);
                return corePage;
            }
            if (end > total) {
                end = Integer.parseInt(String.valueOf(total));
            }
            corePage.setData(redisTemplate.opsForHash().values(key).subList(start, end));
            return corePage;
        }

    }

    @Override
    public boolean isExistMapKey(String key, String mapKey) {
        return redisTemplate.opsForHash().hasKey(key, mapKey);
    }

    @Override
    public Long deleteByMapKey(String key, String... mapKey) {
        return redisTemplate.opsForHash().delete(key, mapKey);
    }

    @Override
    public List getListAll(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public void renameKey(String key, String newKey) {
        redisTemplate.rename(key, newKey);
    }

    @Override
    public void flushdb() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "success";
            }
        });
    }

    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * @describe: redis事务测试
     * @Transactional哪怕加了这个注解spring的配置文件里redistemplate配置也要开启事务支持
     * @param: []
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:00
     */
    public void mutli() {
        flushdb();
        ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.multi();
        vo.set("b", "1");
        vo.increment("b", 2);
        vo.get("b");
        redisTemplate.discard();

        redisTemplate.multi();
        vo.set("a", "1");
        vo.increment("a", 2);
        vo.get("a");
        redisTemplate.exec();

        redisTemplate.setEnableTransactionSupport(false);
        List<Object> rs = null;
        do {
            //多重检测，直到执行成功。
            redisTemplate.watch("a");
            redisTemplate.multi();
            vo.increment("a", 2);
            vo.increment("a", 2);
            rs = redisTemplate.exec();
        } while (rs == null);
    }

}
