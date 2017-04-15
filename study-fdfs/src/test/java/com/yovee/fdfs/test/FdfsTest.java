package com.yovee.fdfs.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FdfsTest {
	
	byte[] buffer;
	
	@BeforeTest
	public void readFile() throws Exception {
		File file = new File("D:/master.diff");

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		int buf_size = 1024;
		buffer = new byte[buf_size];
		int len = 0;
		while (-1 != (len = in.read(buffer, 0, buf_size))) {
			bos.write(buffer, 0, len);
		}
		
		in.close();
		bos.close();

	}
	
    
	@Test
	public void testUpload() throws Exception{
		//获取项目路径下的配置文件"fdfs_client.conf"的路径
	    String configPath = this.getClass()
	        .getResource("fdfs_client.conf").getFile();
	    /*初始化ClientGlobal的配置属性，因为ClientGlobal存放着所有的配置信息，所以这个方法必须要执行，
	         如果不执行，在允许的过程中会报空指针异常。*/
	    ClientGlobal.init(configPath);
	    TrackerClient trackerClient = new TrackerClient();
	    TrackerServer trackerServer = trackerClient.getConnection();
	    StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
	    StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
	   
	    //存储在storage的元数据，可以存放一些与改文件相关的信息。
	    NameValuePair[] metaList = new NameValuePair[]{
	            new NameValuePair("description", "这是一张风景图")
	            };
	    String fileId = storageClient.upload_file1(buffer, null, null);
//	    String fileId = storageClient.upload_file1(this.getClass().getResource("/test/resources/testng.xml").getFile(), 
//	            "jpg", metaList);
	    System.out.println(fileId);
	}
	
	

}
