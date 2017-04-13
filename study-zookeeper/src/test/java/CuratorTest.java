import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;


public class CuratorTest {

	public static void main(String[] args) {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		
		String zookeeperConnectionString = "0.0.0.0:2181";
		
		CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
		
		client.start();
		
		try {
			client.create().forPath("/my/path", "2017-04-11".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String test;
	}
}
