package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.basic.entity.Individual;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.respository.MyBatisRepository;

@MyBatisRepository
public interface IndividualMapper extends BaseMapper<Individual, Long> {
}