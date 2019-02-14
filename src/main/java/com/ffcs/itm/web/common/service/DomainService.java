package com.ffcs.itm.web.common.service;

import com.ffcs.itm.web.common.entity.Domain;
import com.ffcs.itm.web.common.repository.DomainMapper;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService extends BaseService<Domain, String> {
	@Autowired
	private DomainMapper domainMapper;
	
	public List<Domain> getListByCode(String code) {
		return domainMapper.getListByCode(code);
	}

	public List<Domain> getListByCodeLists(List<String> code) {
		return domainMapper.getListByCodeLists(code);
	}


	@Override
	public BaseMapper<Domain, String> getMapper() {
		return domainMapper;
	}
}
