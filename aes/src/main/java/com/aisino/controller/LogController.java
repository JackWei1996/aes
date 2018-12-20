package com.aisino.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aisino.pojo.Log;
import com.aisino.service.ILog;

@Controller
public class LogController {

	@Autowired
	private ILog iLog;

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

}
