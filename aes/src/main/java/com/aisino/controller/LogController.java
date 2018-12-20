package com.aisino.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aisino.pojo.Log;
import com.aisino.pojo.Receiver;
import com.aisino.service.ILog;
import com.aisino.service.IReceiver;
import com.aisino.service.impl.LogImpl;
import com.aisino.service.impl.ReceiverImpl;

@Controller
public class LogController {

	@Autowired
	private ILog iLog;
	@Autowired
	private IReceiver iReceiver;

	@RequestMapping("/testQuery")
	public void testQuery(HttpServletResponse response) {
		try {
			response.getWriter().write(iLog.queryAllLog().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(iLog.queryAllLog());
	}

	@RequestMapping("/testInsert")
	public void testInsert() {
		Log log = new Log();
		log.setReceiverId(2);
		log.setSender("d");
		log.setSeTime(new Date());
		log.setStatu(2);
		int result = iLog.addLog(log);
		System.out.println(result);
	}

	@ResponseBody
	@RequestMapping("/uploadAjax")
	public String uploadAjax(@RequestParam("file") MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			String fileName = file.getOriginalFilename();
			
		    Workbook wb = null;
		    String fileType = fileName.substring(fileName.lastIndexOf("."));  
		    if((".xls").equals(fileType)){  
		        wb = new HSSFWorkbook(in);  //2003-  
		    }else if((".xlsx").equals(fileType)){  
		       wb = new XSSFWorkbook(in);  //2007+  
		    }else{  
		    	System.out.println("导入文件格式有问题");
		    }   		  
		    //得到sheet  
		    Sheet sheet = wb.getSheetAt(0); //默认取第一个sheet 	         
		    //int colsNum = sheet.getPhysicalNumberOfRows();  //获取实际的行数	       
		    int rowsNum = sheet.getLastRowNum();//  
		    
		    for(int j=1; j<rowsNum+1;j++) //第一行为表头，所以从第二行开始
		    {// getLastRowNum，获取最后一行的行标
		    	String projectName=null;
		        String eMail=null;
		        String title=null;
		        String content=null;
		        String adjunct=null;
		        String cc=null;
		        
		        Row row =sheet.getRow(j);
		        if (row != null && row.getCell(1)!=null) {
		        	projectName = row.getCell(1).toString();
		        }
		        if (row != null && row.getCell(2)!=null) {
		        	eMail = row.getCell(2).toString();
		        }
		        if (row != null && row.getCell(3)!=null) {
		        	title = row.getCell(3).toString();
		        }
		        if (row != null && row.getCell(4)!=null) {
		        	content = row.getCell(4).toString();
		        }
		        if (row != null && row.getCell(5)!=null) {
		        	adjunct = row.getCell(5).toString();
		        }
		        if (row != null && row.getCell(6)!=null) {
		        	cc = row.getCell(6).toString();
		        }
		        
		        Receiver receiver = new Receiver();
		        receiver.setProjectName(projectName);
		        receiver.seteMail(eMail);
		        receiver.setTitle(title);
		        receiver.setContent(content);
		        receiver.setCc(cc);
		        
		        iReceiver.addReceiver(receiver);  
		        
		       
		    }
		    wb.close();
		    in.close();
		    System.out.println("顺利导入");
		    
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "{\r\n" + 
					"  \"code\": -1\r\n" + 
					"  ,\"msg\": \"error\"\r\n" + 
					"  ,\"data\": {\r\n" + 
					"    \"src\": \"\"\r\n" + 
					"  }\r\n" + 
					"} ";
		}

		return "{\r\n" + 
				"  \"code\": 0\r\n" + 
				"  ,\"msg\": \"success\"\r\n" + 
				"  ,\"data\": {\r\n" + 
				"    \"src\": \"\"\r\n" + 
				"  }\r\n" + 
				"} ";
	}
	
	
}
