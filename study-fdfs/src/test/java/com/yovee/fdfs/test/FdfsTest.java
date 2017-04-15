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
		//��ȡ��Ŀ·���µ������ļ�"fdfs_client.conf"��·��
	    String configPath = this.getClass()
	        .getResource("fdfs_client.conf").getFile();
	    /*��ʼ��ClientGlobal���������ԣ���ΪClientGlobal��������е�������Ϣ�����������������Ҫִ�У�
	         �����ִ�У�������Ĺ����лᱨ��ָ���쳣��*/
	    ClientGlobal.init(configPath);
	    TrackerClient trackerClient = new TrackerClient();
	    TrackerServer trackerServer = trackerClient.getConnection();
	    StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
	    StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
	   
	    //�洢��storage��Ԫ���ݣ����Դ��һЩ����ļ���ص���Ϣ��
	    NameValuePair[] metaList = new NameValuePair[]{
	            new NameValuePair("description", "����һ�ŷ羰ͼ")
	            };
	    String fileId = storageClient.upload_file1(buffer, null, null);
//	    String fileId = storageClient.upload_file1(this.getClass().getResource("/test/resources/testng.xml").getFile(), 
//	            "jpg", metaList);
	    System.out.println(fileId);
	}
	
	

}
