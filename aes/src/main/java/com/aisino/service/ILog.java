package com.aisino.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aisino.pojo.Log;
import com.aisino.pojo.LogExample;

public interface ILog {

	List<Log> queryAllLog();

	int addLog(Log log);
	
	List<Log> selectByExample(LogExample example);
	
	int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

}
