package com.aisino.mapper;

import com.aisino.pojo.ReMail;
import com.aisino.pojo.ReMailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReMailMapper {
    int countByExample(ReMailExample example);

    int deleteByExample(ReMailExample example);

    int deleteByPrimaryKey(String reMail);

    int insert(ReMail record);

    int insertSelective(ReMail record);

    List<ReMail> selectByExample(ReMailExample example);

    ReMail selectByPrimaryKey(String reMail);

    int updateByExampleSelective(@Param("record") ReMail record, @Param("example") ReMailExample example);

    int updateByExample(@Param("record") ReMail record, @Param("example") ReMailExample example);

    int updateByPrimaryKeySelective(ReMail record);

    int updateByPrimaryKey(ReMail record);
}