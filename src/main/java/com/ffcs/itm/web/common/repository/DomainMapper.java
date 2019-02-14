package com.ffcs.itm.web.common.repository;

import com.ffcs.itm.web.common.entity.Domain;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface DomainMapper extends BaseMapper<Domain, String> {
	List<Domain> getListByCode(String code);

	List<Domain> getListByCodeLists(@Param("domainCode") List<String> domainCode);
}