/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: MyTask.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月20日        | Aisino)Jack    | original version
 */
package com.aisino.task;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.aisino.pojo.Log;
import com.aisino.pojo.Receiver;
import com.aisino.pojo.Sender;
import com.aisino.service.ILog;
import com.aisino.service.IReceiver;
import com.aisino.utils.PropertyUtil;

/**
 * class name:MyTask <BR>
 * class description: 定时发送邮件实现类 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月20日
 * @author Aisino)weihaohao
 */

@Component
@Controller
public class MyTask {
	
	@Autowired
	private IReceiver iReceiver;
	@Autowired
	private ILog iLog;
	
	private static Logger logger = Logger.getLogger(MyTask.class);
	
	/**
	 * Method name: myTask <BR>
	 * Description: 每隔XX时间定时发送任务 <BR>
	 * Remark: <BR>  void<BR>
	 */
	@Scheduled(cron = "0/10 * * * * ?")  //每隔5秒执行一次定时任务
    public void myTask(){
		//从数据库获取接收人信息
        List<Receiver> receivers = iReceiver.queryAllReceiver();
		//从配置文件中获取发送人信息
		Sender sender = new Sender();
		sender.setName(PropertyUtil.getValue("name"));
		sender.setEmail(PropertyUtil.getValue("email"));
		sender.setPass(PropertyUtil.getValue("pass"));
		//发送邮件
		sendMail(sender, receivers);
    }

	/**
	 * Method name: sendMail <BR>
	 * Description: 发送邮件具体实现 <BR>
	 * Remark: <BR>
	 * @param sender发送人
	 * @param receivers接收人  void<BR>
	 */
	private void sendMail(Sender sender, List<Receiver> receivers) {
		String sendEmail = sender.getEmail();	//发送人Email
		String sendName = sender.getName();		//发送人名称
		String sendPass = sender.getPass();		//发送人密码
		//遍历所有接收人
		for (Receiver receiver : receivers) {
			Log log = new Log();	//记录日志
			Date date = null;		
			try {
				//新建配置文件
				Properties props = new Properties();
				// 发送服务器需要身份验证
				props.setProperty("mail.smtp.auth", "true");
				// 发送邮件协议名称
				props.setProperty("mail.transport.protocol", "smtp");
				Session session = Session.getInstance(props);
				//开启调试模式,会在控制台打印发送过程
				session.setDebug(true);
				
				//待发送的邮件
				MimeMessage msg = new MimeMessage(session);
				//发件人
				msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText(sendName) + "\" <"+sendEmail+">"));
				//设置邮件标题
				msg.setSubject(receiver.getTitle());
				//发送内容
				msg.setText(receiver.getContent());
				//设置接收人信息
				msg.setRecipients(RecipientType.TO,InternetAddress.parse(MimeUtility.encodeText(receiver.getProjectName()) + " <"+receiver.geteMail()+">"));
				
				//抄送人
				if(receiver.getCc()!=null&&!receiver.getCc().equals("")) {
					String str = receiver.getCc();
					String[] ccs = str.split(";");
					String ccText = "";
					for (String cc : ccs) {
						String name = cc;
						ccText += MimeUtility.encodeText(name) + " <"+cc+">,";
					}
					logger.error(ccText.substring(0,ccText.length()-1));
					msg.setRecipients(RecipientType.CC, InternetAddress.parse(ccText.substring(0,ccText.length()-1)));
				}
				
				//整封邮件的MINE消息体--向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
				if(receiver.getAdjunct()!=null&&!receiver.getAdjunct().equals("")) {
					MimeMultipart msgMultipart = new MimeMultipart("mixed");//混合的组合关系
					MimeBodyPart htmlPart = new MimeBodyPart();	
					msgMultipart.addBodyPart(htmlPart);
					htmlPart.setContent(receiver.getContent(), "text/html;charset=utf-8");
					//设置邮件的MINE消息体
					String str = receiver.getAdjunct();
					String[] adjuncts = str.split("\\|");
					for (String adjunct : adjuncts) {	
						MimeBodyPart attch = new MimeBodyPart();	// 附件1	
						//附件数据源
						DataSource ds = new FileDataSource("D:\\upload\\"+adjunct);
						DataHandler dh = new DataHandler(ds);
						attch.setDataHandler(dh);
						String[] name = adjunct.split("\\\\");
						attch.setFileName(MimeUtility.encodeText(name[name.length-1]));
						msgMultipart.addBodyPart(attch);		// 将附件添加到MIME消息体中
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
				String[] se = sendEmail.split("@");
				String server = se[1].split("\\.")[0];
				
				transport.connect("smtp."+server+".com", 25, se[0], sendPass);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
				logger.info("发送成功");
				//日志里面增加发送成功
				log.setReceiverId(receiver.getId());
				log.setSender(sendEmail);
				log.setSeTime(date);
				log.setStatu(1);
			} catch (Exception e) {//日志里面增加发送失败
				logger.error("发送失败");
				logger.error(e.getMessage());
				log.setReceiverId(receiver.getId());
				log.setSender(sendEmail);
				if(date==null) {
					date = new Date();
				}
				log.setSeTime(date);
				log.setStatu(2);
			}finally {
				if(log.getStatu()!=1 && log.getStatu()!=2) {//日志里面增加发送异常
					log.setReceiverId(receiver.getId());
					log.setSender(sendEmail);
					if(date==null) {
						date = new Date();
					}
					log.setSeTime(date);
					log.setStatu(3);
				}
				iLog.addLog(log);
			}
		}
	}
}
