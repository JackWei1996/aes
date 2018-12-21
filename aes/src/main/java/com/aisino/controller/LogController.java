package com.aisino.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aisino.service.IExcel2DB;


@Component
@Controller
public class LogController {
	
	
	/**
	 * Method name: uploadAjax <BR>
	 * Description: 从前台导入Excel到数据库 <BR>
	 * Remark: <BR>
	 * @param file
	 * @return  String<BR>
	 */
	@Autowired
	private IExcel2DB excel2db;
	
	private static Logger logger = Logger.getLogger(LogController.class);
	
	@ResponseBody
	@RequestMapping("/uploadAjax")
	public String uploadAjax(@RequestParam("file") MultipartFile file) {
		logger.info("收件人导入数据库操作");
		boolean flag = excel2db.receiverImport(file);
		
		if(flag) {//导入成功
			logger.info("收件人导入数据库操作成功:)");
			return "{\r\n" + 
					"  \"code\": 0\r\n" + 
					"  ,\"msg\": \"success\"\r\n" + 
					"  ,\"data\": {\r\n" + 
					"    \"src\": \"\"\r\n" + 
					"  }\r\n" + 
					"} ";
		}
		//导入失败
		logger.error("收件人导入数据库操作失败:(");
		return "{\r\n" + 
					"  \"code\": -1\r\n" + 
					"  ,\"msg\": \"error\"\r\n" + 
					"  ,\"data\": {\r\n" + 
					"    \"src\": \"\"\r\n" + 
					"  }\r\n" + 
					"} ";
	}
}
