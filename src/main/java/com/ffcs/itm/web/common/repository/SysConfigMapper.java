package com.ffcs.itm.web.common.repository;

import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface SysConfigMapper {
	String getSysValue(String sysVar);
	
	void updateSysValue(@Param("sysVar") String sysVar, @Param("value") String value);
}