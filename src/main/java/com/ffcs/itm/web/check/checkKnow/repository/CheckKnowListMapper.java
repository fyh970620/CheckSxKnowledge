package com.ffcs.itm.web.check.checkKnow.repository;

import com.ffcs.itm.web.check.checkKnow.entity.CheckKnowListAttach;
import com.ffcs.itm.web.check.checkKnow.entity.CheckKonwList;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CheckKnowListMapper {

    /**
     * 查询所有的内容<列表按需查找>and 分页</>
     * @return
     */
    public List<CheckKonwList> selectCheckKnows(@Param("knowGroupType") String knowGroupType);
    public List<CheckKnowListAttach> selectListCheckKnowAttach(@Param("knowId") Integer knowId);

    /**
     * insert
     */
    public boolean CKLaddFirst(@Param("ckl") CheckKonwList ckl);
    public Integer CKLASecond(@Param("ckla") CheckKnowListAttach ckla);
    /**
     * 删除
     */
    public boolean deleteOneCkla(@Param("knowId") Integer knowId);
    public boolean deleteOneCkl(@Param("knowId") Integer knowId);

    /**
     * 只删除附件
     */
    public boolean delOnlyAttach(@Param("attachId")Integer attachId);

    /**
     * 更新
     */
    public boolean updateOneCKL(@Param("ckl") CheckKonwList ckl);

    /**
     * 额外添加附件
     */
    public boolean onlyAddCheckKnow(@Param("ckla") CheckKnowListAttach ckla);


}
