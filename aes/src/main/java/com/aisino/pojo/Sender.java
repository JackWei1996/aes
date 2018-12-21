/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: Sender.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月20日        | Aisino)Jack    | original version
 */
package com.aisino.pojo;

/**
 * class name:Sender <BR>
 * class description: 发送人信息类 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月20日
 * @author Aisino)weihaohao
 */
public class Sender {
	private String name;	//发送人名称
	private String email;	//发送人Email
	private String pass;	//发送人密码
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
