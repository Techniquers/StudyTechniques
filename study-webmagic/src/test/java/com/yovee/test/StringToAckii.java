package com.yovee.test;

import java.io.UnsupportedEncodingException;

public class StringToAckii {
	public static String stringToAscii(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1)  
	        {  
	            sbu.append((int)chars[i]).append(",");  
	        }  
	        else {  
	            sbu.append((int)chars[i]);  
	        }  
	    }  
	    return sbu.toString();  
	} 
	
	public static void main(String[] args) {
		fun1();
	}
	
	public static void fun2(){
		String s1 = "新年快乐";
		char[] chars = s1.toCharArray(); // 把字符中转换为字符数组  
		   
		System.out.println("\n\n汉字 ASCII\n----------------------");  
		for (int i = 0; i < chars.length; i++) {// 输出结果  
		System.out.println(" " + chars[i] + " " + (int) chars[i]);  
		        }  

	}
	
	public static void fun1(){
		try {
			String s1 = java.net.URLEncoder.encode("北京","unicode");
			System.out.println(s1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
