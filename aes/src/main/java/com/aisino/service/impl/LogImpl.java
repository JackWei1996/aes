package com.aisino.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisino.mapper.LogMapper;
import com.aisino.pojo.Log;
import com.aisino.pojo.LogExample;
import com.aisino.service.ILog;

@Service
public class LogImpl implements ILog {

	@Autowired
	private LogMapper logMapper;

	@Override
	public List<Log> queryAllLog() {
		return logMapper.queryAllLog();
	}

	@Override
	public int addLog(Log log) {
		return logMapper.insert(log);
	}

	@Override
	public int updateByExampleSelective(Log record, LogExample example) {
		
		return logMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<Log> selectByExample(LogExample example) {
		return logMapper.selectByExample(example);
	}

}
