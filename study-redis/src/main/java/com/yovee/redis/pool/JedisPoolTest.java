package com.yovee.redis.pool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		JedisPool pool = (JedisPool) context.getBean("jedisPool");  
		Jedis jedis = pool.getResource();  
		pool.destroy();
	}
}
