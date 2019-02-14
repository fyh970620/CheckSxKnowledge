package com.ffcs.itm.web.check.checkStaffComment.repository;

import com.ffcs.itm.web.check.checkStaffComment.entity.CheckStaffComment;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CheckStaffCommentMapper {

   public List<CheckStaffComment> selectCheckStaffComment();
   public StaffUser selectStaffDetail(@Param("keywords")Integer keywords);

   public boolean addOneCheckStaffComment(@Param("csc")CheckStaffComment csc);

   public boolean updateOneCheckStaffComment(@Param("csc")CheckStaffComment csc);

   public List<CheckStaffComment> selectDetail(@Param("checkStaffId")Integer checkStaffId);
}
