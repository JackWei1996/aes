/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: Constant.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月26日        | Aisino)Jack    | original version
 */
package com.aisino.common;

/**
 * class name:Constant <BR>
 * class description: 存放配置文件里面的常量,以及其他固定常量<BR>
 * Remark: <BR>
 * @version 1.00 2018年12月26日
 * @author Aisino)weihaohao
 */
public class Constant {
	
	private Constant() {
		throw new IllegalStateException("Utility class");
	}

	//发送人名称
	public static final String SENDER_NAME = "name";
	//发送人邮箱
	public static final String SENDER_EMAIL = "email";
	//发送人密码
	public static final String SENDER_PASSWORD = "pass";
	//发送时间
	public static final String SEND_TIME = "sendTime";
	//配置延迟扫描邮件时间(毫秒)
	public static final String SCAN_DELAY_TIME = "delay";
	//配置最大附件文件数，超过就以压缩包形式发送附件
	public static final String MAX_ADJUNCT_COUNT = "maxAdjunctCount";
	//配置以压缩包形式发送附件后,是否删除压缩包
	public static final String ZIP_ADJUNCT_DELETE = "zipAdjunctDelete";
	//配置发送附件后,是否删除该文件或文件夹下所有的内容
	public static final String ALL_ADJUNCT_DELETE = "allAdjunctDelete";
}
