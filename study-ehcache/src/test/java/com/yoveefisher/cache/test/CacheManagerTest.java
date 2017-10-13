package com.yoveefisher.cache.test;

import org.ehcache.CacheManager;
import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:TestContext.xml")
public class CacheManagerTest {
//	@Resource
//	CacheManager  cacheManager;
	CacheManager myCacheManager;
	@Before
	public void init(){
//		URL myUrl = getClass().getResource("/config/EhcacheConfig.xml"); 
//		Configuration xmlConfig = new XmlConfiguration(myUrl); 
//		myCacheManager = (CacheManager) CacheManagerBuilder.newCacheManager(xmlConfig);
//		ResourcePoolsImpl resourceTool = new ResourcePoolsImpl(pools)
//		
//		CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, GeneralDO.class, resourcePoolsBuilder); 
//		CacheManagerBuilder.newCacheManagerBuilder().
		
		net.sf.ehcache.CacheManager.create().getCache("");
		
	}
	
	
	@Test
	public void TestCache(){}
	
	private void test(){

//		String name = "test";
//		Cache cache = cacheManager.getCache(name);
//		if(cache == null){
//			cacheManager.getCacheNames().add(name);
//			cache = cacheManager.getCache(name);
//		}
//		
//		cache.put("0", "你好");
//		Object nativeCache = cache.getNativeCache();
//		
//		System.out.println("");
		        
	
	}
	
}
