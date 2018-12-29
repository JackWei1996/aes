/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: MainTest.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月25日        | Aisino)Jack    | original version
 */
package com.aisino.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

import com.sun.mail.imap.IMAPMessage;

/**
 * class name:MainTest <BR>
 * class description: java邮件接收<BR>
 * Remark: <BR>
 * @version 1.00 2018年12月25日
 * @author Aisino)weihaohao
 */
public class MainTest {
	
	@Test
	public void test1() {
		String[] s = "hhhh;hhh;".split(";");
		System.out.println(s.length);
	}
	   
	    public static void main(String args[]) throws Exception {
	    	// 准备连接服务器的会话信息
	    	Properties props = new Properties();
	    	props.setProperty("mail.store.protocol", "imap");
	    	props.setProperty("mail.imap.host", "imap.sina.com");
	    	props.setProperty("mail.imap.port", "143");
	    	// 创建Session实例对象
	    	Session session = Session.getInstance(props);
	    	// 创建IMAP协议的Store对象
	    	Store store = session.getStore("imap");
	    	// 连接邮件服务器
	    	store.connect("jackwei2018@sina.com", "Jack@sina.com");
	    	// 获得收件箱
	    	Folder folder = store.getFolder("INBOX");
	    	// 以读写模式打开收件箱
	    	folder.open(Folder.READ_WRITE);
	    	// 获得收件箱的邮件列表
	    	Message[] messages = folder.getMessages();
	    	// 打印不同状态的邮件数量
	    	System.out.println("收件箱中共" + messages.length + "封邮件!");
	    	System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
	    	System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
	    	System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");
	    	System.out.println("------------------------开始解析邮件----------------------------------");
	    	// 解析邮件
	    	for (Message message : messages) {
	    	IMAPMessage msg = (IMAPMessage) message;
	    	String subject = MimeUtility.decodeText(msg.getSubject());
	    	String to  = MimeUtility.decodeText(msg.getFrom().toString());
	    	System.out.println("getSender   "+msg.getSender());
	    	System.out.println("getFrom   "+msg.getFrom());
	    	System.out.println("getReceivedDate   "+msg.getReceivedDate());
	    	System.out.println("getAllRecipients   "+msg.getAllRecipients()[0]);
	    	MimeMultipart part = (MimeMultipart) msg.getContent();
	    	System.out.println(part.getContentType());
	    	
	    System.out.println(part);
	    	msg.getSender().toString();
	    	System.out.println("[" + subject + "]未读，是否需要阅读此邮件（yes/no）？");

	    	
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    	String answer = reader.readLine(); 
	    	if ("yes".equalsIgnoreCase(answer)) {
	    	//POP3ReceiveMailTest.parseMessage(msg); // 解析邮件
	    	// 第二个参数如果设置为true，则将修改反馈给服务器。false则不反馈给服务器
	    	msg.setFlag(Flag.SEEN, true); //设置已读标志
	    	}
	    	}
	    	// 关闭资源
	    	folder.close(false);
	    	store.close();
	    	}
	    	
	    
	            /*String imapserver = "imap.sina.com"; // 邮件服务器
	            String user = "jackwei2018";
	            String password = "Jack@sina.com";     // 根据自已的密码修改
	            // 获取默认会话
	            Properties prop = System.getProperties();
	            prop.put("mail.imap.host",imapserver);
	           
	            prop.put("mail.imap.auth.plain.disable","true");
	            Session mailsession=Session.getInstance(prop,null);
	            mailsession.setDebug(false); //是否启用debug模式
	            IMAPFolder folder= null;
	            IMAPStore store=null;
	            int total= 0;
	            try{
	               store=(IMAPStore)mailsession.getStore("imap");  // 使用imap会话机制，连接服务器
	               store.connect(imapserver,user,password);
	               folder=(IMAPFolder)store.getFolder("INBOX"); //收件箱 
	               // 使用只读方式打开收件箱 
	               folder.open(Folder.READ_WRITE);
	               //获取总邮件数
	               total = folder.getMessageCount();
	               System.out.println("-----------------您的邮箱共有邮件：" + total+" 封--------------");
	               // 得到收件箱文件夹信息，获取邮件列表
	               Message[] msgs =folder.getMessages();
	               System.out.println("\t收件箱的总邮件数：" + msgs.length);  
	               System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());  
	               System.out.println("\t新邮件数：" + folder.getNewMessageCount());  
	               System.out.println("----------------End------------------");
	            }
	            catch(MessagingException ex){
	                 System.err.println("不能以读写方式打开邮箱!");
	                 ex.printStackTrace();
	            }finally {
	            // 释放资源
	              try{
	                  if(folder!=null)
	                      folder.close(true); //退出收件箱时,删除做了删除标识的邮件
	                 if (store != null)
	                    store.close();
	                }catch(Exception bs){
	                 bs.printStackTrace();
	                }             
	            }         */ 
	    
}
