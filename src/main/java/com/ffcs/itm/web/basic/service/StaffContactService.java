package com.ffcs.itm.web.basic.service;

import com.ffcs.itm.web.basic.entity.StaffContact;
import com.ffcs.itm.web.basic.repository.StaffContactMapper;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffContactService extends BaseService<StaffContact, Long> {
	@Autowired
	private StaffContactMapper staffContactMapper;
	
	@Override
	public BaseMapper<StaffContact, Long> getMapper() {
		return staffContactMapper;
	}
}
