package com.yovee.redisson.test;

import java.math.BigDecimal;
import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedissonVOEfficienceTest {

	static RedissonClient  redissonClient;
	static RedissonVO[] redissonVOs = new RedissonVO[10000];;
	
	static int initnum= 10000;
	
	static void init(){
//		for (RedissonVO redissonVO : redissonVOs) {
		
		for(int i=0;i<redissonVOs.length;i++){
			RedissonVO redissonVO = redissonVOs[i];
			redissonVOs[i] = new RedissonVO();
			redissonVOs[i].setName("yoveefisher"+i);
			redissonVOs[i].setAddress("国投财富广场4号楼");
			redissonVOs[i].setAge(36);
			redissonVOs[i].setMoney(new BigDecimal(1000));
			redissonVOs[i].setNo(""+i+1);
			
		}
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application-redisson.xml");  
		redissonClient  = (RedissonClient ) context.getBean("redissonClient");  
		
		
	}
	
	public static void main(String[] args) {
		
		String mapName1 = "myVOTest1";
		String mapName2 = "myVOTest2";
		
		init();
		
		setMapValue(mapName1, 10000);
		setMapValue(mapName2, 100);
		
		getMapValue(mapName1);
		getMapValue(mapName2);
		
		delMapValue(mapName1);
        delMapValue(mapName2);
        getMapValue(mapName1);
		getMapValue(mapName2);
		
		
		
		
	}
	
	private static void setMapValue(String mapName,int num) {
		long startTime = System.currentTimeMillis();
		RMap<String, RedissonVO> map = redissonClient.getMap(mapName);
		
		for (int i = 0; i < num; i++) {
			map.put(i+"",redissonVOs[i]);
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
		RMap<String, RedissonVO> map = redissonClient.getMap(mapName);
		if(map.size() == 0)
			System.out.println(mapName + "获取的缓存Map size 是0");
//		for(Map.Entry<String, RedissonVO> entry :map.entrySet()){
//			entry.getKey();
//			entry.getValue();
//			 System.out.println(mapName + "获取的缓存Map" + entry.getKey() + "/" + entry.getValue());
//		}
		long endTime = System.currentTimeMillis();
		System.out.println(mapName + "读取缓存" +(endTime - startTime)+"ms，缓存的大小为" + map.size());
	}


}
