/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: SendMailUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月28日        | Aisino)Jack    | original version
 */
package com.aisino.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisino.common.Constant;
import com.aisino.common.ErrCode;
import com.aisino.pojo.Log;
import com.aisino.pojo.Receiver;
import com.aisino.pojo.Sender;
import com.aisino.service.ILog;
import com.aisino.service.ISendMail;
import com.aisino.utils.MyUtils;
import com.aisino.utils.PropertyUtil;
import com.aisino.utils.ZipUtil;

/**
 * class name:SendMail <BR>
 * class description: 发送邮件 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月28日
 * @author Aisino)weihaohao
 */
@Service
public class SendMail implements ISendMail{
	@Autowired
	private ILog iLog;
	//打印日志
	private static Logger logger = Logger.getLogger(SendMail.class);
	//文件夹下的附件列表
	private List<File> folderFiles = new ArrayList<>();
	//zip列表
	private static List<File> zipFiles = new ArrayList<>();
	//从何时开始修改日志
	private Date startDateTime = null;
	//配置最大附件文件数，超过就以压缩包形式发送附件
	private Integer maxAdjunctCount = 10;
	//配置以压缩包形式发送附件后,是否删除压缩包
	private Boolean zipAdjunctDelete = false;
	//配置发送附件后,是否删除该文件或文件夹下所有的内容
	private Boolean allAdjunctDelete = false;
	
	/**
	 * Method name: sendMail <BR>
	 * Description: 发送邮件具体实现 <BR>
	 * Remark: <BR>
	 * @param sender发送人
	 * @param receivers接收人  void<BR>
	 */
	public Date sendMail(Sender sender, List<Receiver> receivers) {
		String sendEmail = sender.getEmail();	//发送人Email
		String sendName = sender.getName();		//发送人名称
		String sendPass = sender.getPass();		//发送人密码
		
		if(sendEmail==null || sendPass == null ||
				sendEmail.equals("")||sendPass.equals("")) {//发送人邮箱密码为空
			logger.error(ErrCode.SENDER_ACCOUNT_PASS_EMPTY_ERR.getMsg());
			return startDateTime;
		}
		
		try {
			maxAdjunctCount = Integer.parseInt(PropertyUtil.getValue(Constant.MAX_ADJUNCT_COUNT));
		}catch (Exception e) {//配置最大附件文件数出错，请修改配置文件！
			logger.error(ErrCode.CONFIG_MAX_ADJUNCT_COUNT.getMsg());
		}
		try {
			zipAdjunctDelete = Boolean.valueOf(PropertyUtil.getValue(Constant.ZIP_ADJUNCT_DELETE));
		}catch (Exception e) {//配置删除压缩包参数出错，请修改配置文件！
			logger.error(ErrCode.CONFIG_ZIP_ADJUNCT_DELETE.getMsg());
		}
		try {
			allAdjunctDelete = Boolean.valueOf(PropertyUtil.getValue(Constant.ALL_ADJUNCT_DELETE));
		}catch (Exception e) {//配置删除所有已发送文件出错，请修改配置文件！
			logger.error(ErrCode.CONFIG_ALL_ADJUNCT_DELETE.getMsg());
		}
		
		//新建配置文件
		Properties props = new Properties();
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		//开启调试模式,会在控制台打印发送过程
		//session.setDebug(true);
		//待发送的邮件
		MimeMessage msg = new MimeMessage(session);
		//设置退信修改日期(修改该日期之后的日志)
		startDateTime = new Date();
		
		//遍历所有接收人
		for (Receiver receiver : receivers) {
			if(receiver.getState()) {	//是否发送
				Log log = new Log();	//记录日志
				Date date = null;		
				log.setReceiverId(receiver.getId());
				log.setSender(sendEmail);
				
				try {
					//发件人
					msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText(sendName) + "\" <"+sendEmail+">"));
					//设置邮件标题
					msg.setSubject(receiver.getTitle().trim());
					//发送内容
					msg.setText(receiver.getContent().trim());
					//设置接收人信息
					String rec = getList(receiver.geteMail().trim());
					msg.setRecipients(RecipientType.TO,InternetAddress.parse(rec));
					
					//抄送人
					if(receiver.getCc()!=null&&!receiver.getCc().equals("")) {
						String str = getList(receiver.getCc().trim());
						msg.setRecipients(RecipientType.CC, InternetAddress.parse(str));
					}
					
					//整封邮件的MINE消息体--向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
					if(receiver.getAdjunct()!=null&&!receiver.getAdjunct().equals("")) {
						MimeMultipart msgMultipart = new MimeMultipart("mixed");//混合的组合关系
						MimeBodyPart htmlPart = new MimeBodyPart();	
						msgMultipart.addBodyPart(htmlPart);
						htmlPart.setContent(receiver.getContent().trim(), "text/html;charset=utf-8");
						//设置邮件的MINE消息体
						String[] adjuncts = receiver.getAdjunct().split("\\|");
						for (String adjunct : adjuncts) {	
						    String path = adjunct.trim();//要遍历的路径
							File file = new File(path);//获取其file对象
							folderFiles.clear();//清空
							findFiles(file);//获取文件夹下的所有附件
							if(folderFiles.size() > maxAdjunctCount) {//大于规定附件数
								//进行压缩,产生压缩文件
								ZipUtil.createZip(path, path+".zip", allAdjunctDelete);
								File f = new File(path+".zip");
								zipFiles.add(f);//获取所有的zip文件
								MimeBodyPart attch = new MimeBodyPart();	// 附件
								//附件数据源
								DataSource ds = new FileDataSource(f);
								DataHandler dh = new DataHandler(ds);
								attch.setDataHandler(dh);
								attch.setFileName(MimeUtility.encodeText(f.getName()));
								msgMultipart.addBodyPart(attch);		// 将附件添加到MIME消息体中
							}else {
								for (File f : folderFiles) {
									MimeBodyPart attch = new MimeBodyPart();	// 附件
									//附件数据源
									DataSource ds = new FileDataSource(f);
									DataHandler dh = new DataHandler(ds);
									attch.setDataHandler(dh);
									attch.setFileName(MimeUtility.encodeText(f.getName()));
									msgMultipart.addBodyPart(attch);		// 将附件添加到MIME消息体中
								}
							}
						}
						msg.setContent(msgMultipart);
					}
					// 发送日期
					date = new Date();
					msg.setSentDate(date);
					//保存改变
					msg.saveChanges();
					
					//创建会话
					Transport transport = session.getTransport();
					//发送方---会话smtp服务器,端口号,用户名,密码
					String server = MyUtils.getHost(sendEmail);
					transport.connect("smtp."+server+".com", 25, MyUtils.getSenderName(sendEmail), sendPass);
					transport.sendMessage(msg, msg.getAllRecipients());
					transport.close();
					//日志里面增加发送成功
					log.setSeTime(date);
					log.setStatu(ErrCode.SEND_SUCCESS.getCode());
					log.setDetail(ErrCode.SEND_SUCCESS.getMsg());
					//------------------发送结束------------------------//
					if(zipAdjunctDelete) {//是否删除zip文件
						for (File f : zipFiles) {							
							ZipUtil.deleteZip(f);
						}
					}
				}catch (Exception e) {//日志里面增加发送失败
					if(date==null) {
						date = new Date();
					}
					String emsg = e.getMessage();
					if(e.toString().contains("NullPointerException")) {//发送内容为空
						log.setStatu(ErrCode.SENDER_EMPTY_CONTENT.getCode());
						log.setDetail(ErrCode.SENDER_EMPTY_CONTENT.getMsg());
						logger.error(ErrCode.SENDER_EMPTY_CONTENT.getMsg());
					}else if(emsg.contains("timeout")) {//网络超时
						log.setStatu(ErrCode.SYSTEM_TIME_OUT.getCode());
						log.setDetail(ErrCode.SYSTEM_TIME_OUT.getMsg());
						logger.error(ErrCode.SYSTEM_TIME_OUT.getMsg());
					}else if(emsg.contains("Invalid Addresses") || emsg.contains("illegal")) {//接收人地址错误
						log.setStatu(ErrCode.RECEIVER_ADDRESS_ERR.getCode());
						log.setDetail(ErrCode.RECEIVER_ADDRESS_ERR.getMsg());
						logger.error(receiver.geteMail()+"@接收人地址有错误,发送失败@");
					}else if(emsg.contains("Empty address")) {//接收人地址为空
						log.setStatu(ErrCode.RECEIVER_EMPTY_ADDRESS.getCode());
						log.setDetail(ErrCode.RECEIVER_EMPTY_ADDRESS.getMsg());
						logger.error(ErrCode.RECEIVER_EMPTY_ADDRESS.getMsg());
					}else if(emsg.contains("550")) {
						log.setStatu(ErrCode.SEND_FAILURE.getCode());
						log.setDetail(ErrCode.SEND_FAILURE.getMsg());
						logger.error(ErrCode.SEND_FAILURE.getMsg());
					}else if(emsg.contains("553")) {//发送人账号密码不匹配
						log.setStatu(ErrCode.SENDER_ACCOUNT_ERR.getCode());
						log.setDetail(ErrCode.SENDER_ACCOUNT_ERR.getMsg());
						logger.error(ErrCode.SENDER_ACCOUNT_ERR.getMsg());
					}else if(emsg.contains("535")) {//发送人账号密码不匹配
						log.setStatu(ErrCode.SENDER_ACCOUNT_PASS_ERR.getCode());
						log.setDetail(ErrCode.SENDER_ACCOUNT_PASS_ERR.getMsg());
						logger.error(ErrCode.SENDER_ACCOUNT_PASS_ERR.getMsg());
					}else if(emsg.contains("IOException")) {//发送人附件异常
						log.setStatu(ErrCode.RECEIVER_ADJUNCT_ERR.getCode());
						log.setDetail(ErrCode.RECEIVER_ADJUNCT_ERR.getMsg());
						logger.error(ErrCode.RECEIVER_ADJUNCT_ERR.getMsg());
					}else if(log.getDetail() == null || log.getDetail().equals("")) {//其他错误
						log.setStatu(ErrCode.SYSTEM_UNKNOWN_ERR.getCode());
						if(emsg!=null && !emsg.equals("")) {//英文错误提示
							log.setDetail(emsg);
						}else {//未知错误
							log.setDetail(ErrCode.SYSTEM_UNKNOWN_ERR.getMsg());
						}
						logger.error(ErrCode.SYSTEM_UNKNOWN_ERR.getMsg(),e);
					}
				}finally {
					log.setSeTime(date);
					iLog.addLog(log);
				}
			}
		}
		return startDateTime;
	}
	
	/**
	 * Method name: findFiles <BR>
	 * Description: 找到一个文件夹下所有的文件 <BR>
	 * Remark: <BR>
	 * @param file  void<BR>
	 */
	private void findFiles(File file){
		if(file.isDirectory()) {			
			File[] fs = file.listFiles();
			for(File f:fs){
				if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
					findFiles(f);
				if(f.isFile())		//若是文件，直接打印
					folderFiles.add(f);
			}
		}else if(file.isFile()) {
			folderFiles.add(file);
		}
	}

	/**
	 * Method name: getList <BR>
	 * Description: 获取发送人列表 <BR>
	 * Remark: <BR>
	 * @param trim
	 * @return
	 * @throws UnsupportedEncodingException  String<BR>
	 */
	private String getList(String trim) throws UnsupportedEncodingException {
		String[] ccs = trim.split(";");
		String ccText = "";
		for (String cc : ccs) {
			ccText += MimeUtility.encodeText(cc.trim()) + " <"+cc.trim()+">,";
		}
		return ccText.substring(0,ccText.length()-1);
	}
}
