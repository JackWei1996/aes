/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: ScanNDRMail.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月28日        | Aisino)Jack    | original version
 */
package com.aisino.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisino.common.ErrCode;
import com.aisino.pojo.Log;
import com.aisino.pojo.LogExample;
import com.aisino.pojo.Receiver;
import com.aisino.pojo.ReceiverExample;
import com.aisino.service.ILog;
import com.aisino.service.IReceiver;
import com.aisino.service.IScanNDRMail;
import com.aisino.task.MyTask;
import com.aisino.utils.MyUtils;
import com.aisino.utils.NDRMailUtil;

/**
 * class name:ScanNDRMail <BR>
 * class description: 扫描退信,如果有退信改写日志 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月28日
 * @author Aisino)weihaohao
 */
@Service
public class ScanNDRMail implements IScanNDRMail{
	@Autowired
	private IReceiver iReceiver;
	@Autowired
	private ILog iLog;
	//打印日志
	private static Logger logger = Logger.getLogger(MyTask.class);
	/**
	 * Method name: scanNDR <BR>
	 * Description: 扫描发送人邮箱,查看是否有退信.如果有则修改日志 <BR>
	 * Remark: <BR>  void<BR>
	 */
	public void scanNDR(Date startDateTime) {
		try {
			String err = NDRMailUtil.receive();
			if(err!=null && !err.equals("")) {
				logger.error("@.@被退回的邮件有："+err);
				String[] emails = err.split(",");
				//得到的所有错误Email
				for (String email : emails) {
					//错误日志
					Log record = new Log();
					//设置日志错误代码为接收人邮箱错误
					record.setStatu(ErrCode.RECEIVER_NDR.getCode());
					//设置日志错误信息为XXX邮箱有误+无法接收邮件，已退回！
					ReceiverExample receiverExample = new ReceiverExample();
					ReceiverExample ajExample = new ReceiverExample();
					//查询发送人或者抄送人邮箱是否错误
					receiverExample.createCriteria().andEMailLike("%"+email+"%");
					ajExample.createCriteria().andCcLike("%"+email+"%");
					//查询结果
					List<Receiver> res = iReceiver.selectByExample(receiverExample);
					List<Receiver> resAj = iReceiver.selectByExample(ajExample);
					//日志查询
					LogExample example = new LogExample();
					for (Receiver r : res) {
						example.createCriteria().andReceiverIdEqualTo(r.getId()).andSeTimeGreaterThanOrEqualTo(MyUtils.getDateTime(startDateTime));
						record.setDetail(ErrCode.RECEIVER_NDR.getMsg());
						iLog.updateByExampleSelective(record, example);
						logger.error(email+"此邮箱无法发送=[发送人]");
					}
					
					for (Receiver r : resAj) {
						example.createCriteria().andReceiverIdEqualTo(r.getId()).andSeTimeGreaterThanOrEqualTo(MyUtils.getDateTime(startDateTime));
						record.setDetail(ErrCode.RECEIVER_NDR.getMsg());
						iLog.updateByExampleSelective(record, example);
						logger.error(email+"此邮箱无法发送=[抄送人]");
					}
				}
			}else {
				logger.info("扫描邮箱,没有退回邮件~");
			}
		} catch (Exception e) {
			if(e.toString().contains("SocketConnectException")) {//网络超时无法扫描
				logger.error(ErrCode.SYSTEM_SCANTIME_OUT.getMsg());
			}else {				
				logger.error(ErrCode.SYSTEM_UNKNOWN_ERR.getMsg(), e);
			}
		}
	}
}
