JedisPool是线程安全的，可以使用静态方式使用
JedisPoolConfig包含Redis-specific连接池的默认配置。
JedisPool是基于Commons Pool 2
#设置后进先出的池策略 
lifo=true 
#允许最大活动对象数 
maxActive=24 
#允许最大空闲对象数 
maxIdle=6 
#允许最大等待时间毫秒数 
maxWait=150000 
#被空闲对象回收器回收前在池中保持空闲状态的最小时间毫秒数 
minEvictableIdleTimeMillis=100000 
#允许最小空闲对象数 
minIdle=0 
#设定在进行后台对象清理时，每次检查对象数 
numTestsPerEvictionRun=1 
#指明是否在从池中取出对象前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个. 
testOnBorrow =false 
#指明是否在归还到池中前进行检验 
testOnReturn =false 
#指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除. 
testWhileIdle=false 
#在空闲连接回收器线程运行期间休眠的时间毫秒数. 如果设置为非正数,则不运行空闲连接回收器线程 
timeBetweenEvictionRunsMillis=120000 
#当池中对象用完时，请求新的对象所要执行的动作 
whenExhaustedAction=1

/// Jedis implements Closeable. Hence, the jedis instance will be auto-closed after the last statement.
try (Jedis jedis = pool.getResource()) {
  /// ... do stuff here ... for example
  jedis.set("foo", "bar");
  String foobar = jedis.get("foo");
  jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike"); 
  Set<String> sose = jedis.zrange("sose", 0, -1);
}
/// ... when closing your application:
pool.destroy();

如果Jedis 从池中借走，使用适当的方式还回到连接池，或者已经发生了连接异常。如果没有借走，将会端口连接或关闭连接。

###设置主从方式 ####开启复制Redis主要用于构建主从模式。这样写的请求明确的被分发给master节点，并将变化复制到从节点。读请求分发给从节点来减轻主节点的压力。
为了开启复制，有两种方式通知从节点从哪个主节点复制
1.可以在各自Redis服务器的配置文件定义。
2.通过Jedis实例，调用 slaveOf并将IP和端口进行传送。
jedis.slaveOf("localhost", 6379);  //  if the master is on the same PC which runs your code
jedis.slaveOf("192.168.1.35", 6379); 
注意：2.6以后从节点默认为只读，写的请求会导致错误。修改配置可以按照正常的Redis服务器使用，但是变化不会被复制，并且会导致这些变化存在被覆盖的风险。
如果master宕机，希望利用一台从机作为主节点。首先需要禁用离线master的复制功能，然后在有多台slave的情况下，选择一台开启复制功能的slave作为master。
slave1jedis.slaveofNoOne();
slave2jedis.slaveof("192.168.1.36", 6379); 






























