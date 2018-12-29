/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: IReceiver.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月20日        | Aisino)Jack    | original version
 */
package com.aisino.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aisino.pojo.Receiver;
import com.aisino.pojo.ReceiverExample;

/**
 * class name:IReceiver <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月20日
 * @author Aisino)weihaohao
 */
public interface IReceiver {
	public List<Receiver> queryAllReceiver();

	public int addReceiver(Receiver receiver);
	
	List<Receiver> selectByExample(ReceiverExample example);
	
	int updateByExampleSelective(@Param("record") Receiver record, @Param("example") ReceiverExample example);
}
