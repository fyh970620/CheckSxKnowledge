package com.ffcs.itm.web.check.TypeValue.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.entity.TpDomainListvalues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeValueService {

    @Autowired
    private TypeMapper typeMapper;

    public List<TpDomainListvalues> selectAllGroupType(){
        List<TpDomainListvalues> list = typeMapper.selectGroupType();
        return list;
    }

    public List<TpDomainListvalues> selectAllStaffType(){
        List<TpDomainListvalues> list = typeMapper.selectStaffType();
        return list;
    }

    public List<TpDomainListvalues> selectAllListType(){
        List<TpDomainListvalues> list = typeMapper.selectListType();
        return list;
    }
}
