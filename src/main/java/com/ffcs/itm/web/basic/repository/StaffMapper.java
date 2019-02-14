package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.entity.dto.ListStaffDto;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface StaffMapper extends BaseMapper<Staff, Long> {
	Long getNewStaffId();
	
	void unDelete(long staffid);
	
	List<ListStaffDto> findAllByOrgId(long orgId);

	Staff findByUserName(String userName);

	List<Staff> getStaffListByIds(List<Long> staffIdList);
	
	@SuppressWarnings("rawtypes")
	List<Map> getOrgPath(@Param("orgId") Long orgId, @Param("rootOrgId") Long rootOrgId);

	@SuppressWarnings("rawtypes")
	List<Map> getProjectTree(@Param("startProjectId") Long startProjectId);

	@SuppressWarnings("rawtypes")
	List<Map> getFavoriteGroupList(Long staffId);

	@SuppressWarnings("rawtypes")
	List<Map> findStaffByIds(@Param("staffIds") List<String> staffIds);

	@SuppressWarnings("rawtypes")
	Map getFavoriteGroupListById(@Param("staffId") Long orgId, @Param("groupId") Long groupId);

	void updateStaffPwd(@Param("requestPwd") String requestPwd, @Param("staffId") Long staffId);

	String getPopoverHtml();
}