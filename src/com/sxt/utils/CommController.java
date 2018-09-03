package com.sxt.utils;

import java.text.SimpleDateFormat;

import java.util.Date;

import com.google.gson.Gson;

public class CommController {
	
	public String toJson(Object obj){
		return new Gson().toJson(obj);
	}
	
	public String formatTime(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		return sdf.format(date);
	}
	
}
