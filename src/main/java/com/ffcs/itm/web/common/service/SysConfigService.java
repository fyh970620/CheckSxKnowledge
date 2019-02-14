package com.ffcs.itm.web.common.service;

import com.ffcs.itm.common.mapper.JsonMapper;
import com.ffcs.itm.web.common.repository.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 系统配置表服务
 */
@Service
public class SysConfigService {
	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	/**
	 * 获取系统配置值
	 * @param sysVar
	 * @return
	 */
	public String getSysValue(String sysVar) {
		return sysConfigMapper.getSysValue(sysVar);
	}
	
	public Map<String, Object> getSysValueToMap(String sysVar) {
		String val = getSysValue(sysVar);
		return JsonMapper.nonDefaultMapper().fromJson(val, Map.class);
	}
	
	/**
	 * 更新系统配置值
	 * @param sysVar
	 * @param value
	 */
	public void updateSysValue(String sysVar, String value) {
		sysConfigMapper.updateSysValue(sysVar, value);
	}
}
