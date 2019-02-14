package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.basic.entity.StaffContact;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.respository.MyBatisRepository;

@MyBatisRepository
public interface StaffContactMapper extends BaseMapper<StaffContact, Long> {
}