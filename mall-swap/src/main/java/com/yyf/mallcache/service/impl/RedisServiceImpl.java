package com.yyf.mallcache.service.impl;
import com.yyf.mallcache.redis.KeyPrefix;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    StringRedisTemplate redisTemplate;
	public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
		T t=null;
        String beanStr= redisTemplate.opsForValue().get(prefix.getPrefix()+key);
        t= FastJsonUtil.stringToBean(beanStr,clazz);
		return t;
	}

	public <T> void set(KeyPrefix prefix,String key,T value) {
        String beanStr=FastJsonUtil.beanToString(value);
        int seconds=prefix.getExpireSeconds();
        if(seconds<=0) {
            redisTemplate.opsForValue().set(prefix.getPrefix()+key, beanStr);

        }else {
            //setex �������ֵ ͬʱ���ù���ʱ��
            redisTemplate.opsForValue().set(prefix.getPrefix()+key,beanStr,seconds, TimeUnit.SECONDS);
        }

	}
    public <T> Boolean sent(KeyPrefix prefix,String key,T value,long timeOut) {
        String returnStr=null;
        String beanStr=FastJsonUtil.beanToString(value);
       return redisTemplate.opsForValue().setIfAbsent(prefix.getPrefix()+key, beanStr,timeOut,TimeUnit.SECONDS);

    }
	public <T> Boolean exist(KeyPrefix prefix,String key) {
        long seconds= redisTemplate.getExpire(prefix.getPrefix()+key,TimeUnit.SECONDS);
        if(seconds == -2) return false;
        return true;

	}
	public <T> Long incr(KeyPrefix prefix,String key) {
       return redisTemplate.opsForValue().increment(prefix.getPrefix()+key);
	}
	public <T> Long decr(KeyPrefix prefix,String key) {
        return redisTemplate.opsForValue().decrement(prefix.getPrefix()+key);
	}
	//ɾ��
	@Override
	public <T> boolean del(KeyPrefix prefix, String key) {
       return redisTemplate.delete(prefix.getPrefix()+key);

	}
	
}
