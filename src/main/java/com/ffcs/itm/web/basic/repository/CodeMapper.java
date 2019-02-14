package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.basic.entity.dto.CodeDto;
import com.ffcs.itm.web.respository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface CodeMapper {

    List<CodeDto> getCodeList(String codeType);

}
