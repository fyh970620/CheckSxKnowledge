package com.ffcs.itm.web.check.TypeValue.repository;

import com.ffcs.itm.web.check.entity.TpDomainListvalues;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface TypeMapper {

    public List<TpDomainListvalues> selectGroupType();

    public List<TpDomainListvalues> selectStaffType();

    public List<TpDomainListvalues> selectListType();

    public String selectGroupName(@Param("keywords1") String keywords1);
    public String selectStaffTypeName(@Param("keywords2") String keywords2);
    public String selectListTypeName(@Param("keywords3") String keywords3);
}
