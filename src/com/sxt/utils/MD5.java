package com.sxt.utils;


import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5 {

	public static void main(String[] args) {
		
		String source="123456";
		String salt="熊兵"+source;
		Integer hashIterations=10;
		Md5Hash   md5Hash=new Md5Hash(source, salt, hashIterations);
		System.out.print(md5Hash.toString());

	}

}
