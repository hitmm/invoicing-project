package com.glacier.frame.dao.system;

import com.glacier.frame.entity.system.OperaLog;
import com.glacier.frame.entity.system.OperaLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperaLogMapper {
    int countByExample(OperaLogExample example);

    int deleteByExample(OperaLogExample example);

    int deleteByPrimaryKey(String operalogId);

    int insert(OperaLog record);

    int insertSelective(OperaLog record);

    List<OperaLog> selectByExample(OperaLogExample example);

    OperaLog selectByPrimaryKey(String operalogId);

    int updateByExampleSelective(@Param("record") OperaLog record, @Param("example") OperaLogExample example);

    int updateByExample(@Param("record") OperaLog record, @Param("example") OperaLogExample example);

    int updateByPrimaryKeySelective(OperaLog record);

    int updateByPrimaryKey(OperaLog record);
}