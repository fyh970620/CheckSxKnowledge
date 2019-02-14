package com.ffcs.itm.web.service;

import com.ffcs.itm.common.mybatis.plugin.domain.PageParam;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.support.PageTool;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseService<M, ID extends Serializable> {
	public M findOne(ID id) {
		return getMapper().findOne(id);
	}
	
	public void save(M m) {
		getMapper().save(m);
	}
	
    /**
     * 保存多个实体
     * @param ms
     */
    public void save(Iterable<M> ms) {
    	for (M m : ms) {
    		getMapper().save(m);
    	}
    }
	
	public void update(M m) {
		getMapper().update(m);
	}
	
    public void update(Iterable<M> ms) {
    	for (M m : ms) {
    		getMapper().update(m);
    	}
    }
	
	public void delete(M m) {
		getMapper().delete(m);
	}
	
	public void batchDelete(ID[] ids) {
	    for (ID id : ids) {
	        delete(id);
	    }
	}
	
	public void delete(ID id) {
		getMapper().delete(id);
	} 
	
	public Page<M> findAll(PageParam pager, Map<String, ? extends Object> params) {
		List<M> entityList = getMapper().findAll(pager, params);

		return PageTool.getPage(entityList, pager);
	}
	
	public List<M> findAll(Map<String, ? extends Object> params) {
		return getMapper().findAll(null, params);
	}
	
	public List<M> findAll() {
		return getMapper().findAll(null, null);
	}
	
	public abstract BaseMapper<M, ID> getMapper();
}