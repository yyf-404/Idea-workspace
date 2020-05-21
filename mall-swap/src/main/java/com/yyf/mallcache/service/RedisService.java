package com.yyf.mallcache.service;

import com.yyf.mallcache.redis.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;



public interface RedisService {
		public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) ;
		public <T> void set(KeyPrefix prefix, String key, T value) ;
		
		public <T> boolean del(KeyPrefix prefix, String key) ;
		public <T> Boolean exist(KeyPrefix prefix, String key);
		public <T> Long incr(KeyPrefix prefix, String key) ;
			
		public <T> Long decr(KeyPrefix prefix, String key) ;
	 public <T> Boolean sent(KeyPrefix prefix, String key, T value, long timeOut);

}
