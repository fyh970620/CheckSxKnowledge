package com.ffcs.itm.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqlService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    public Object excuteSql(String sql) {
    	return jdbcTemplate.queryForObject(sql, Object.class);
    }
    
    public List<Map<String, Object>> excuteSqlForList(String sql) {
    	return jdbcTemplate.queryForList(sql);
    }
}
