package com.ffcs.itm.web.basic.service;

import com.ffcs.itm.web.basic.entity.Individual;
import com.ffcs.itm.web.basic.repository.IndividualMapper;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualService extends BaseService<Individual, Long> {
	@Autowired
	private IndividualMapper individualMapper;

	@Override
	public BaseMapper<Individual, Long> getMapper() {
		return individualMapper;
	}
}
