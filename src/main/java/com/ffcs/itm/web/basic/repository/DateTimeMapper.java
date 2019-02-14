package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.respository.MyBatisRepository;

@MyBatisRepository
public interface DateTimeMapper {

    String getDBCurrentDateTime(String format);
}
