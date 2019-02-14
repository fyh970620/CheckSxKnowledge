package com.ffcs.itm.web.check.respository;

import com.ffcs.itm.common.mybatis.plugin.domain.PageParam;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseMapper<M, ID extends Serializable> {
	M findOne(ID id);
	
	void save(M m);
	
	void update(M m);

	void delete(M m);
	
	void delete(ID id);
	
	List<M> findAll(PageParam pager, @Param("params") Map<String, ? extends Object> params);
}
