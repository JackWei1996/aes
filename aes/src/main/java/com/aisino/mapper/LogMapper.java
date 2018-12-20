package com.aisino.mapper;

import com.aisino.pojo.Log;
import com.aisino.pojo.LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
	List<Log> queryAllLog();
	
    int countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}