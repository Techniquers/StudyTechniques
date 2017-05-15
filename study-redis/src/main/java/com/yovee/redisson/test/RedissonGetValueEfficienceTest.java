package com.yovee.redisson.test;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedissonGetValueEfficienceTest {
	static RedissonClient  redissonClient;
	static void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("application-redisson.xml");  
		redissonClient  = (RedissonClient ) context.getBean("redissonClient");  
		
	}
	
	public static void main(String[] args) {
		int value1 = 10000;
		int value2 = 100;
		String mapName1 = "myTest1";
		String mapName2 = "myTest2";
		init();
		
		setMapValue(mapName1,value1);
		setMapValue(mapName2,value2);
		
		getMapValue(mapName1);
		getMapValue(mapName2);
		
		delMapValue(mapName1);
        delMapValue(mapName2);
		
		getMapValue(mapName1);
		getMapValue(mapName2);
		
	}
	
	private static void setMapValue(String mapName,int num) {
		long startTime = System.currentTimeMillis();
		RMap<String, String> map = redissonClient.getMap(mapName);
		
		for (int i = 0; i < num; i++) {
			map.put(i+"",i+"");
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(mapName + "设置值完成用时：" +(endTime - startTime)+"ms");
		
		
	}
	
	private static void delMapValue(String mapName){
		long startTime = System.currentTimeMillis();
		RMap<String, String> map = redissonClient.getMap(mapName);

		map.clear();
		
		System.out.println(mapName+"清空缓存！！");
		long endTime = System.currentTimeMillis();
		System.out.println(mapName + "清空缓存：" +(endTime - startTime)+"ms");
	}
	
	private static void getMapValue(String mapName){
		long startTime = System.currentTimeMillis();
		RMap<String, String> map = redissonClient.getMap(mapName);
		if(map.size() == 0)
			System.out.println(mapName + "获取的缓存Map size 是0");
//		for(Map.Entry<String, String> entry :map.entrySet()){
//			 System.out.println(mapName + "获取的缓存Map" + entry.getKey() + "/" + entry.getValue());
//		}
		long endTime = System.currentTimeMillis();
		System.out.println(mapName + "读取缓存" +(endTime - startTime)+"ms，缓存的大小为" + map.size());
	}

}
