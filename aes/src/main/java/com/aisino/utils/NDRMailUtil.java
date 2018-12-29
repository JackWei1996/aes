/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: MailUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月26日        | Aisino)Jack    | original version
 */
package com.aisino.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * class name:MailUtil <BR>
 * class description: 处理退信工具类 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月26日
 * @author Aisino)weihaohao
 */
public class NDRMailUtil {
	private static final String PROTOCOL = "imap"; 
    private static final String PORT = "143"; 
    private static final String HOST = MyUtils.getHost(PropertyUtil.getValue("email")); 
    private static final String USERNAME = PropertyUtil.getValue("email");  
    private static final String LOGINPASS = PropertyUtil.getValue("pass"); 
    
    // 初始化连接邮件服务器的会话信息  
    private  static  Properties props = null; 
    static{
        props = new Properties();  
        props.setProperty("mail.store.protocol", PROTOCOL);       // 协议  
        props.setProperty("mail."+PROTOCOL+".port", PORT);             // 端口  
        props.setProperty("mail."+PROTOCOL+".host", PROTOCOL+"."+HOST+".com");    // pop3服务器
    }
    public static String receive() throws Exception {  
        // 创建Session实例对象  
        Session session = Session.getInstance(props);  
        Store store = session.getStore(PROTOCOL);  
        store.connect(USERNAME, LOGINPASS);  
          
        // 获得收件箱  
        Folder folder = store.getFolder("INBOX");  
        /* Folder.READ_ONLY：只读权限 
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
         */  
        folder.open(Folder.READ_WRITE); //打开收件箱          
        //得到未读的邮件
        Message[] messages = folder.getMessages(folder.getMessageCount()-folder.getUnreadMessageCount()+1,
        		folder.getMessageCount());
        String text = "";
        if (messages != null && messages.length > 0) {        	
        	text =  parseMessage(messages);
        }
        //释放资源  
        folder.close(true);  
        store.close();  
        return text;
    } 
    
    /** 
     * 解析邮件 
     * @param messages 要解析的邮件列表 
     * @return 
     */  
    public static  String parseMessage(Message ...messages) throws MessagingException, IOException {  
    	StringBuffer ndrs = new StringBuffer(30);   
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {  
            MimeMessage msg = (MimeMessage) messages[i];  
	        if(getSubject(msg).contains("系统退信")) {	        	
	        	StringBuffer content = new StringBuffer(30);  
	        	getMailTextContent(msg, content);  
	        	ndrs.append(getErrMail(content)+",");
	        }
	        //解析完之后设为已读
	       msg.setFlag(Flags.Flag.SEEN, true);
        }  
        return ndrs.substring(0, ndrs.length()-1);
    }
    
    /**
     * Method name: getErrMail <BR>
     * Description: 返回收件人邮箱 <BR>
     * Remark: <BR>
     * @param content
     * @return  String<BR>
     */
    private static String getErrMail(StringBuffer content) {
    	if(content==null || content.toString().equals("")) {
    		return null;
    	}
		String[] mm = content.toString().split("收件人：");
		if(mm[1]==null || mm[1].equals("")) {
    		return null;
    	}
		String[] te = mm[1].split("\n");
		return te[0].trim();
	}
	/** 
     * 获得邮件主题 
     * @param msg 邮件内容 
     * @return 解码后的邮件主题 
     */  
    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {  
        return MimeUtility.decodeText(msg.getSubject());  
    }  
      
    /** 
     * 获得邮件发件人 
     * @param msg 邮件内容 
     * @return 姓名 <Email地址> 
     * @throws MessagingException 
     * @throws UnsupportedEncodingException  
     */  
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {  
        String from = "";  
        Address[] froms = msg.getFrom();  
        if (froms.length < 1)  
            throw new MessagingException("没有发件人!");  
          
        InternetAddress address = (InternetAddress) froms[0];  
        String person = address.getPersonal();  
        if (person != null) {  
            person = MimeUtility.decodeText(person) + " ";  
        } else {  
            person = "";  
        }  
        from = person + "<" + address.getAddress() + ">";  
          
        return from;  
    }  
      
    /** 
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人 
     * <p>Message.RecipientType.TO  收件人</p> 
     * <p>Message.RecipientType.CC  抄送</p> 
     * <p>Message.RecipientType.BCC 密送</p> 
     * @param msg 邮件内容 
     * @param type 收件人类型 
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ... 
     * @throws MessagingException 
     */  
    public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {  
        StringBuffer receiveAddress = new StringBuffer();  
        Address[] addresss = null;  
        if (type == null) {  
            addresss = msg.getAllRecipients();  
        } else {  
            addresss = msg.getRecipients(type);  
        }  
          
        if (addresss == null || addresss.length < 1)  
            throw new MessagingException("没有收件人!");  
        for (Address address : addresss) {  
            InternetAddress internetAddress = (InternetAddress)address;  
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");  
        }  
          
        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号  
          
        return receiveAddress.toString();  
    }  
      
    /** 
     * 获得邮件发送时间 
     * @param msg 邮件内容 
     * @return yyyy年mm月dd日 星期X HH:mm 
     * @throws MessagingException 
     */  
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {  
        Date receivedDate = msg.getSentDate();  
        if (receivedDate == null)  
            return "";  
          
        if (pattern == null || "".equals(pattern))  
            pattern = "yyyy年MM月dd日 E HH:mm ";  
          
        return new SimpleDateFormat(pattern).format(receivedDate);  
    }  
      
    /**  
     * 判断邮件是否已读  
     * @param msg 邮件内容  
     * @return 如果邮件已读返回true,否则返回false  
     * @throws MessagingException   
     */  
    public static boolean isSeen(MimeMessage msg) throws MessagingException {  
        return msg.getFlags().contains(Flags.Flag.SEEN);  
    }  

       
    /** 
     * 获得邮件文本内容 
     * @param part 邮件体 
     * @param content 存储邮件文本内容的字符串 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static  void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
        if (part.isMimeType("text/*") && !isContainTextAttach) {  
            content.append(part.getContent().toString());  
        } else if (part.isMimeType("message/rfc822")) {   
            getMailTextContent((Part)part.getContent(),content);  
        } else if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                getMailTextContent(bodyPart,content);  
            }  
        }  
    }  

    /** 
     * 文本解码 
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本 
     * @return 解码后的文本 
     * @throws UnsupportedEncodingException 
     */  
    public  String decodeText(String encodeText) throws UnsupportedEncodingException {  
        if (encodeText == null || "".equals(encodeText)) {  
            return "";  
        } else {  
            return MimeUtility.decodeText(encodeText);  
        }  
    }  
}
