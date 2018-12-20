package service;

import java.util.List;

import com.aisino.pojo.Log;

public interface ILog {

	List<Log> queryAllLog();

	int addLog(Log log);
}
