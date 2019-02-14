package com.ffcs.itm.web.basic.service;

import com.ffcs.itm.web.basic.entity.dto.CodeDto;
import com.ffcs.itm.web.basic.repository.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Autowired
    private CodeMapper codeMapper;

    /**
     *  根据code_type获取codelist
     * */
    public List<CodeDto> getCodeList(String codeType) {
        return codeMapper.getCodeList(codeType);
    }

}
