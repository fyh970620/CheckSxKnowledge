package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.basic.entity.Organization;
import com.ffcs.itm.web.basic.entity.dto.OrganizationTreeItemDto;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface OrganizationMapper extends BaseMapper<Organization, Long> {
	List<OrganizationTreeItemDto> getOrgTree(long orgId);
	
	List<OrganizationTreeItemDto> getOrgChildTree(long orgId);

	void updateName(long id, String name);
	
	@SuppressWarnings("rawtypes")
	List<Map> getTopOrgTree(Long startOrgId);
	
	@SuppressWarnings("rawtypes")
	List<Map> getTopOrgTreeBySearchOrgPath(String searchOrgPath);
	
	@SuppressWarnings("rawtypes")
	List<Map> getTopOrgTreeWhere(@Param("startOrgId") Long startOrgId, @Param("orgId") Long orgId);

	Integer getTopOrgTreeChildCount(@Param("level") Integer level, @Param("startOrgId") Long startOrgId);

	Long getGropuSeqId();

	void saveProjectDefined(@Param("groupId") Long groupId, @Param("groupName") String groupName,
            @Param("defineStaffId") Long defineStaffId);

	void saveUserFavGroupConfig(@Param("staffId") Long staffId, @Param("groupId") Long groupId);

	void saveStaffGroup(@Param("staffId") Long staffId, @Param("groupId") Long groupId);

	void deleteProjectGroup(@Param("groupId") Long groupId);

	void deleteUserFavGroupConfig(@Param("staffId") Long staffId, @Param("groupId") Long groupId);

	void deleteStaffGroup(@Param("groupId") Long groupId);

	void deleteStaffGroupConfig(@Param("groupId") Long groupId);

	void updateProjectGroup(@Param("groupName") String groupName, @Param("groupId") Long groupId);

	@SuppressWarnings("rawtypes")
	List<Map> deltaImport(@Param("staffIds") String staffIds);

	@SuppressWarnings("rawtypes")
	List<Map>loadStaffGroupConfigByGroupId(@Param("groupId") Long groupId);

	@SuppressWarnings("rawtypes")
	List<Map> searchOrgPathByOrgId(@Param("orgIds") List<String> orgIds);
}