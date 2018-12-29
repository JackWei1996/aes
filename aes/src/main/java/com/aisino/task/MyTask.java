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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.aisino.common.Constant;
import com.aisino.common.ErrCode;
import com.aisino.pojo.Receiver;
import com.aisino.pojo.Sender;
import com.aisino.service.IReceiver;
import com.aisino.service.IScanNDRMail;
import com.aisino.service.ISendMail;
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
	private ISendMail iSendMail;
	@Autowired
	private IScanNDRMail iScanNDRMail;
	//定时发送时间
	private static String rule;
	//打印日志
	private static Logger logger = Logger.getLogger(MyTask.class);
	//从何时开始修改日志
	private  Date startDateTime = null;
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
		sender.setName(PropertyUtil.getValue(Constant.SENDER_NAME));
		sender.setEmail(PropertyUtil.getValue(Constant.SENDER_EMAIL));
		sender.setPass(PropertyUtil.getValue(Constant.SENDER_PASSWORD));
		//发送邮件
		startDateTime = iSendMail.sendMail(sender, receivers);
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
		rule = PropertyUtil.getValue(Constant.SEND_TIME);
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				//执行我的任务
				myTask();
				try {
					//配置延迟扫描邮件时间(毫秒)
					int delay = Integer.parseInt(PropertyUtil.getValue(Constant.SCAN_DELAY_TIME));
					if(delay==-1) {
						return ;
					}
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					logger.error(ErrCode.CONFIG_SCANTIME_ERR.getMsg());
				}
				//查看是否有退信
				if(startDateTime==null) {//扫描开始时间为空
					logger.error(ErrCode.SYSTEM_START_SCANTIME_ERR.getMsg());
				}else {					
					iScanNDRMail.scanNDR(startDateTime);
				}
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				CronTrigger trigger = null;
				Date nextExec = null;
				// 任务触发，可修改任务的执行周期
				try {					
					trigger = new CronTrigger(rule);
					nextExec = trigger.nextExecutionTime(triggerContext);
				} catch (Exception e) {
					logger.error(ErrCode.CONFIG_SENDTIME_ERR.getMsg());
				}
                return nextExec;
			}
		});
	}
}
