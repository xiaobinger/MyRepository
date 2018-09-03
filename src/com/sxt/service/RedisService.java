package com.sxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Controller;

import redis.clients.jedis.Jedis;

@Controller("redisService")
public class RedisService {
	public static Jedis jedis;
	
	@Autowired
	@Qualifier("jedisConnectionFactory")
	private JedisConnectionFactory jedisConnectionFactory;
	
	/**
     * 获取一个jedis 客户端
     * @return
     */
    private Jedis getJedis(){
     //从工厂里面获取jedis 
    	if(jedis == null){
            return jedisConnectionFactory.getShardInfo().createResource();
        }
        return jedis;
    }
    
    /**
     * 往redis里存放key-value键值对
     * @param key
     * @param value
     */
    public void set(String key,String value){
    	this.getJedis().set(key, value);
    }
    
    /**
     * 从redis里获取key对应的value
     * @param key
     * @return
     */
    public String get(String key){
    	return this.getJedis().get(key);
    }
}
