/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: MyUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月26日        | Aisino)Jack    | original version
 */
package com.aisino.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class name:MyUtils <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月26日
 * @author Aisino)weihaohao
 */
public class MyUtils {
	
	private MyUtils() {
	    throw new IllegalStateException("MyUtils class");
	}

	/**
	 * Method name: getHost <BR>
	 * Description: 根据配置文件获取邮箱服务器名 <BR>
	 * Remark: <BR>
	 * @param sendEmail
	 * @return  String<BR>
	 */
	public static String getHost(String sendEmail) {
		if(sendEmail!=null && !sendEmail.equals("")) {			
			String[] se = sendEmail.split("@");
			return se[1].split("\\.")[0].trim();
		}
		return "";
	}

	/**
	 * Method name: getSenderName <BR>
	 * Description: 根据配置文件,获取发送人名称 <BR>
	 * Remark: <BR>
	 * @param sendEmail
	 * @return  String<BR>
	 */
	public static String getSenderName(String sendEmail) {
		if(sendEmail!=null && !sendEmail.equals("")) {				
			return sendEmail.split("@")[0];
		}
		return "";
	}
	
	/**
	 * Method name: nowDate <BR>
	 * Description: 返回当前日期和时间yyyy-MM-dd <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	public static String getNowDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * Method name: nowDate <BR>
	 * Description: 返回给定日期和时间yyyy-MM-dd HH:mm:ss <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	public static String getDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
