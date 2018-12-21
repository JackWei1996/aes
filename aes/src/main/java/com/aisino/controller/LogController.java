package com.aisino.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aisino.pojo.Log;
import com.aisino.pojo.Receiver;
import com.aisino.service.IExcel2DB;
import com.aisino.service.ILog;
import com.aisino.service.IReceiver;

@Component
@Controller
public class LogController {
	
	@Autowired
	private IExcel2DB excel2db;
	
	/**
	 * Method name: uploadAjax <BR>
	 * Description: 从前台导入Excel到数据库 <BR>
	 * Remark: <BR>
	 * @param file
	 * @return  String<BR>
	 */
	@ResponseBody
	@RequestMapping("/uploadAjax")
	public String uploadAjax(@RequestParam("file") MultipartFile file) {
		
		boolean flag = excel2db.receiverImport(file);
		
		if(flag) {//导入成功
			return "{\r\n" + 
					"  \"code\": 0\r\n" + 
					"  ,\"msg\": \"success\"\r\n" + 
					"  ,\"data\": {\r\n" + 
					"    \"src\": \"\"\r\n" + 
					"  }\r\n" + 
					"} ";
		}
		//导入失败
		return "{\r\n" + 
					"  \"code\": -1\r\n" + 
					"  ,\"msg\": \"error\"\r\n" + 
					"  ,\"data\": {\r\n" + 
					"    \"src\": \"\"\r\n" + 
					"  }\r\n" + 
					"} ";
	}
}
