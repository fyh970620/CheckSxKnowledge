package com.ffcs.itm.web.check.checkSubject.repository;

import com.ffcs.itm.web.check.checkKnow.entity.CheckKnowListAttach;
import com.ffcs.itm.web.check.checkKnow.entity.CheckKonwList;
import com.ffcs.itm.web.check.checkSubject.entity.CheckSubjectList;
import com.ffcs.itm.web.check.checkSubject.entity.CheckSubjectListAttach;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CheckSubjectListMapper {

    public List<CheckSubjectList> selectAllSubjectAndAttach();

    public CheckSubjectList selectDetail(@Param("subjectId")Integer subjectId);

    public boolean updateOneSubject(@Param("csl")CheckSubjectList csl);

    /**
     * 同时添加
     */
    public boolean CKLaddFirst(@Param("csl")CheckSubjectList csl);
    public boolean CKLASecond(@Param("csla")CheckSubjectListAttach csla);

    /**
     * 只添加附件
     */
    public boolean OnlyInsertAttach(@Param("csla")CheckSubjectListAttach csla);

    public boolean deleteAttachOnly(@Param("attachId")Integer attachId);

    public boolean deleteAll(@Param("subjectId")Integer subjectId);
    public boolean deleteList(@Param("subjectId")Integer subjectId);

}
