/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: ISendMail.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月28日        | Aisino)Jack    | original version
 */
package com.aisino.service;

import java.util.Date;
import java.util.List;

import com.aisino.pojo.Receiver;
import com.aisino.pojo.Sender;

/**
 * class name:ISendMail <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月28日
 * @author Aisino)weihaohao
 */
public interface ISendMail {
	public Date sendMail(Sender sender, List<Receiver> receivers);
}
