/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: ErrCode.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月26日        | Aisino)Jack    | original version
 */
package com.aisino.common;

/**
 * class name:ErrCode <BR>
 * class description: 错误代码信息 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月26日
 * @author Aisino)weihaohao
 */
public enum ErrCode {
	/**
	 * 2XX代表发送成功
	 * 3XX代表发送方错误
	 * 4XX代表接收方错误
	 * 5XX代表系统/配置文件错误
	*/
	SEND_SUCCESS(200, "发送成功:)"),
	SEND_FAILURE(300, "发送失败，今天发送的次数太多了:("),
	SENDER_ACCOUNT_ERR(301,"<发送人账号密码不匹配，请修改配置文件>"),
	SENDER_ACCOUNT_PASS_ERR(302,"<发送人授权密码错误，请修改配置文件>"),
	SENDER_ACCOUNT_PASS_EMPTY_ERR(303,"<发送人授权密码错误，请修改配置文件>"),
	SENDER_EMPTY_CONTENT(304,"失败！发送内容为空0.0"),
	
	RECEIVER_ADDRESS_ERR(400,"@接收人地址有错误,详情请看log日志@"),
	RECEIVER_NDR(401,"部分邮件已退回！详情请看log日志"),
	RECEIVER_EMPTY_ADDRESS(402,"失败！接收人邮箱为空=.=!"),
	RECEIVER_ADJUNCT_ERR(403,"失败！添加附件错误,请检查附件目录***"),
	
	SYSTEM_TIME_OUT(500,"网络超时......"),
	SYSTEM_SCANTIME_OUT(501,"网络超时,无法扫描邮件..."),
	SYSTEM_LOAD_EXCEL_ERR(502,"导入Excel文件格式有问题:("),
	SYSTEM_LOAD_EXCEL_FAILURE(503,"导入Excel失败:("),
	SYSTEM_ZIP_FAILURE(504,"[压缩失败]--"),
	SYSTEM_START_SCANTIME_ERR(505,"开始扫描邮件时间为空，无法扫描!"),
	
	CONFIG_SCANTIME_ERR(520,"延迟扫描邮件时间出错,请修改配置文件!"),
	CONFIG_SENDTIME_ERR(521,"！发送时间配置出错，请修改配置文件！"),
	CONFIG_MAX_ADJUNCT_COUNT(522,"配置最大附件文件数出错，请修改配置文件！"),
	CONFIG_ZIP_ADJUNCT_DELETE(523,"配置删除压缩包参数出错，请修改配置文件！"),
	CONFIG_ALL_ADJUNCT_DELETE(524,"配置删除所有已发送文件出错，请修改配置文件！"),
	
	SYSTEM_UNKNOWN_ERR(555,"？未知错误？详情请看log日志");
	
    private String msg;
    private int code;
     
    private ErrCode() { 
    }
    private ErrCode(int code, String msg){
        this.msg=msg;
        this.code=code;
    }
    public String getMsg(){
        return this.msg;
    }
    public int getCode() {
      return this.code;
    }
}
