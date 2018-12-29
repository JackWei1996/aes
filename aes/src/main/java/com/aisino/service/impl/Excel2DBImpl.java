/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: Excel2DBImpl.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月21日        | Aisino)Jack    | original version
 */
package com.aisino.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aisino.common.ErrCode;
import com.aisino.mapper.ReceiverMapper;
import com.aisino.pojo.Receiver;
import com.aisino.pojo.ReceiverExample;
import com.aisino.service.IExcel2DB;
import com.aisino.service.IReceiver;

/**
 * class name:Excel2DBImpl <BR>
 * class description: excel导入到数据库 实现类 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月21日
 * @author Aisino)weihaohao
 */
@Service
public class Excel2DBImpl implements IExcel2DB {

	@Autowired
	private IReceiver iReceiver;
	@Autowired
	private ReceiverMapper rMapper;
	//打印日志
	private static Logger logger = Logger.getLogger(Excel2DBImpl.class);
	
	/**
	 * @Override
	 * @see com.aisino.service.IExcel2DB#receiverImport(org.springframework.web.multipart.MultipartFile) <BR>
	 * Method name: receiverImport <BR>
	 * Description: 实现收件人的excel导入到数据库  <BR>
	 * Remark: <BR>
	 * @param file
	 * @return  <BR>
	*/
	@Override
	public boolean receiverImport(MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			String fileName = file.getOriginalFilename();
			
		    Workbook wb = null;
		    String fileType = fileName.substring(fileName.lastIndexOf('.'));  
		    if((".xls").equals(fileType)){  
		        wb = new HSSFWorkbook(in);  //2003-  
		    }else if((".xlsx").equals(fileType)){  
		       wb = new XSSFWorkbook(in);  //2007+  
		    }else{//导入Excel格式出错
		    	logger.error(fileName+"--->"+ErrCode.SYSTEM_LOAD_EXCEL_ERR.getMsg());
		    	return false;
		    }   		  
		    //得到sheet  
		    Sheet sheet = wb.getSheetAt(0); //默认取第一个sheet 	         
		    //int colsNum = sheet.getPhysicalNumberOfRows();  //获取实际的行数	       
		    int rowsNum = sheet.getLastRowNum();//  
		    loop:
		    for(int j=1; j<rowsNum+1;j++) //第一行为表头，所以从第二行开始
		    {// getLastRowNum，获取最后一行的行标
		    	String projectName=null;
		        String eMail=null;
		        String title=null;
		        String content=null;
		        String adjunct=null;
		        String cc=null;
		        String state=null;
		        
		        Row row =sheet.getRow(j);
		        if (row != null && row.getCell(1)!=null) {
		        	projectName = row.getCell(1).toString().trim();
		        }
		        if (row != null && row.getCell(2)!=null) {
		        	eMail = row.getCell(2).toString().trim();
		        }
		        if (row != null && row.getCell(3)!=null) {
		        	title = row.getCell(3).toString().trim();
		        }
		        if (row != null && row.getCell(4)!=null) {
		        	content = row.getCell(4).toString().trim();
		        }
		        if (row != null && row.getCell(5)!=null) {
		        	adjunct = row.getCell(5).toString().trim();
		        }
		        if (row != null && row.getCell(6)!=null) {
		        	cc = row.getCell(6).toString().trim();
		        }
		        if (row != null && row.getCell(7)!=null) {
		        	state = row.getCell(7).toString().trim();
		        }
		        
		        Receiver receiver = new Receiver();
		        receiver.setProjectName(projectName);
		        receiver.seteMail(eMail);
		        receiver.setTitle(title);
		        receiver.setContent(content);
		        receiver.setAdjunct(adjunct);
		        receiver.setCc(cc);
		        receiver.setState(Boolean.valueOf(state));
		        
		        // 处理重复导入问题
				ReceiverExample example = new ReceiverExample();
				example.createCriteria().andEMailEqualTo(eMail);
				List<Receiver> existReceiver = rMapper.selectByExample(example);
				for (Receiver receiver2 : existReceiver) {
					if (receiver.equals(receiver2)) {
						continue loop;
					}
				}
		        iReceiver.addReceiver(receiver);  
		    }
		    wb.close();
		    in.close();
		}catch (Exception e) {
			logger.error(file.getOriginalFilename()+"--->"+ErrCode.SYSTEM_LOAD_EXCEL_FAILURE.getMsg());
			return false;
		}
		return true;
	}
}