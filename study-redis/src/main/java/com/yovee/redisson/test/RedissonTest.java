package com.yovee.redisson.test;

import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedissonTest {
	static RedissonClient  redissonClient;
	static void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("application-redisson.xml");  
		redissonClient  = (RedissonClient ) context.getBean("redissonClient");  
		
		
	}
	
	public static void main(String[] args) {
		
		init();
		
		setMapValue();
		
		getMapValue();
		
		delMapValue();
		
		getMapValue();
		
	}
	
	private static void setMapValue() {
		RMap<String, String> map = redissonClient.getMap("mytest");

		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "6");
		map.put("6", "6");
	}
	
	private static void delMapValue(){
		RMap<String, String> map = redissonClient.getMap("mytest");

		map.clear();
		
		System.out.println("清空缓存！！");
	}
	
	private static void getMapValue(){
		RMap<String, String> map = redissonClient.getMap("mytest");
		if(map.size() == 0)
			System.out.println("获取的缓存Map size 是0");
		for(Map.Entry<String, String> entry :map.entrySet()){
			 System.out.println("获取的缓存Map" + entry.getKey() + "/" + entry.getValue());
		}
	}

}
