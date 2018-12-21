/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: MyTask.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月20日        | Aisino)Jack    | original version
 */
package com.aisino.task;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
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
@EnableScheduling
public class MyTask implements SchedulingConfigurer{
	
	@Autowired
	private IReceiver iReceiver;
	@Autowired
	private ILog iLog;
	//定时发送时间
	private static String rule;
	//附件列表
	private static List<File> files = null;
	//打印日志
	private static Logger logger = Logger.getLogger(MyTask.class);
	/**定时规则
	0 0 5-15 * * ? 每天5-15点整点触发
	0 0/3 * * * ? 每三分钟触发一次
	0 0-5 14 * * ? 在每天下午2点到下午2:05期间的每1分钟触发 
	0 0/5 14 * * ? 在每天下午2点到下午2:55期间的每5分钟触发
	0 0/5 14,18 * * ? 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
	0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时
	0 0 10,14,16 * * ? 每天上午10点，下午2点，4点 

	0 0 12 ? * WED 表示每个星期三中午12点
	0 0 17 ? * TUES,THUR,SAT 每周二、四、六下午五点
	0 10,44 14 ? 3 WED 每年三月的星期三的下午2:10和2:44触发 
	0 15 10 ? * MON-FRI 周一至周五的上午10:15触发
	0 0 23 L * ? 每月最后一天23点执行一次
	0 15 10 L * ? 每月最后一日的上午10:15触发 
	0 15 10 ? * 6L 每月的最后一个星期五上午10:15触发 
	0 15 10 * * ? 2005 2005年的每天上午10:15触发 
	0 15 10 ? * 6L 2002-2005 2002年至2005年的每月的最后一个星期五上午10:15触发 
	0 15 10 ? * 6#3 每月的第三个星期五上午10:15触发

	"30 * * * * ?" 每半分钟触发任务
	"30 10 * * * ?" 每小时的10分30秒触发任务
	"30 10 1 * * ?" 每天1点10分30秒触发任务
	"30 10 1 20 * ?" 每月20号1点10分30秒触发任务
	"30 10 1 20 10 ? *" 每年10月20号1点10分30秒触发任务
	"30 10 1 20 10 ? 2011" 2011年10月20号1点10分30秒触发任务
	"30 10 1 ? 10 * 2011" 2011年10月每天1点10分30秒触发任务
	"30 10 1 ? 10 SUN 2011" 2011年10月每周日1点10分30秒触发任务
	"15,30,45 * * * * ?" 每15秒，30秒，45秒时触发任务
	"15-45 * * * * ?" 15到45秒内，每秒都触发任务
	"15/5 * * * * ?" 每分钟的每15秒开始触发，每隔5秒触发一次
	"15-30/5 * * * * ?" 每分钟的15秒到30秒之间开始触发，每隔5秒触发一次
	"0 0/3 * * * ?" 每小时的第0分0秒开始，每三分钟触发一次
	"0 15 10 ? * MON-FRI" 星期一到星期五的10点15分0秒触发任务
	"0 15 10 L * ?" 每个月最后一天的10点15分0秒触发任务
	"0 15 10 LW * ?" 每个月最后一个工作日的10点15分0秒触发任务
	"0 15 10 ? * 5L" 每个月最后一个星期四的10点15分0秒触发任务
	"0 15 10 ? * 5#3" 每个月第三周的星期四的10点15分0秒触发任务*/

	/**
	 * Method name: myTask <BR>
	 * Description: 发送任务 <BR>
	 * Remark: <BR>  void<BR>
	 */
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
					String str = receiver.getAdjunct();
					String[] adjuncts = str.split("\\|");
					for (String adjunct : adjuncts) {	
					    String path = "D:\\upload\\"+adjunct.trim();		//要遍历的路径
						File file = new File(path);		//获取其file对象
						//logger.error(path);
						files = new ArrayList<>();
						func(file);	
						
						for (File f : files) {
							//logger.error(f.getAbsolutePath());
							MimeBodyPart attch = new MimeBodyPart();	// 附件
							//附件数据源
							DataSource ds = new FileDataSource(f);
							DataHandler dh = new DataHandler(ds);
							attch.setDataHandler(dh);
							//String[] name = adjunct.split("\\\\");
							attch.setFileName(MimeUtility.encodeText(f.getName()));
							msgMultipart.addBodyPart(attch);		// 将附件添加到MIME消息体中
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
				String[] se = sendEmail.split("@");
				String server = se[1].split("\\.")[0].trim();
				
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

	private static void func(File file){
		if(file.isDirectory()) {			
			File[] fs = file.listFiles();
			for(File f:fs){
				if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
					func(f);
				if(f.isFile())		//若是文件，直接打印
					files.add(f);
			}
		}else if(file.isFile()) {
			files.add(file);
		}
	}

	/**
	 * Method name: getList <BR>
	 * Description: 获取联系人列表 <BR>
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

	/**
	 * @Override
	 * @see org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks(org.springframework.scheduling.config.ScheduledTaskRegistrar) <BR>
	 * Method name: configureTasks <BR>
	 * Description: 动态获取配置文件发送时间,并执行任务<BR>
	 * Remark: <BR>
	 * @param taskRegistrar  <BR>
	*/
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		rule = PropertyUtil.getValue("sendTime");
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				//执行我的任务
				myTask();
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				// 任务触发，可修改任务的执行周期
				CronTrigger trigger = new CronTrigger(rule);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
			}
		});
	}
}
