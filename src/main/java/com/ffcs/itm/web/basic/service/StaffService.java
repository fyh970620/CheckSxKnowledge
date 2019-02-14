package com.ffcs.itm.web.basic.service;

import com.bsnnms.bean.user.StaffInfo;
import com.bsnnms.exception.ApplicationException;
import com.bsnnms.exception.SystemException;
import com.ffcs.itm.common.utils.ContextInfo;
import com.ffcs.itm.common.utils.MD5Encode;
import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.entity.dto.ListStaffDto;
import com.ffcs.itm.web.basic.entity.dto.StaffDto;
import com.ffcs.itm.web.basic.repository.StaffMapper;
import com.ffcs.itm.web.respository.BaseMapper;
import com.ffcs.itm.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffService extends BaseService<Staff, Long> {
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private IndividualService individualService;

    @Autowired
    private StaffContactService staffContactService;

    public static final int LOCKED_ERROR = 0;

    public static final int INVALID_ERROR = 1;

    public static final int WRONG_PSWD_ERROR = 2;

    public static final int ONLINE_ERROR = 3;

    public static final int UNSYS_ERROR = 4;

    public static final int CENSOR_ERROR = 5;

    public static final int NOSTAFF_ERROR = 6;

    private static final String[] ERROR_MSG = new String[] {"员工已锁定!", "员工已停用!", "员工密码错误", "员工已经登陆", "非本系统用户,如果要使用论坛,可以在论坛登录!", "该用户还未审批", "无此用户！"};

    @Override
    public void save(Staff staff) {
        staff.getIndividual().setStaffId(staff.getId());
        staff.getStaffContact().setStaffId(staff.getId());

        super.save(staff);
        individualService.save(staff.getIndividual());
        staffContactService.save(staff.getStaffContact());
    }

    @Override
    public void update(Staff staff) {
        super.update(staff);
        individualService.update(staff.getIndividual());
        staffContactService.update(staff.getStaffContact());
    }

    public void unDelete(long staffId) {
        staffMapper.unDelete(staffId);
    }

    public Long getNewStaffId() {
        return staffMapper.getNewStaffId();
    }

    public List<ListStaffDto> getListByOrgId(long orgId) {
        return staffMapper.findAllByOrgId(orgId);
    }

    public Staff findStaffByUserName(String userName) {
        return staffMapper.findByUserName(userName);
    }

    public Map<String, Object> check(StaffDto staffDto) throws SystemException {
        Staff staff = staffMapper.findByUserName(staffDto.getUserName());
        Map<String, Object> checkResult = new HashMap<String, Object>();
        String errorMsg = null;
        if (staff == null) {
            errorMsg = ERROR_MSG[NOSTAFF_ERROR];
        }
        else {
            int iStaffType = this.getStaffType(staff.getStaffName(), staff.getState());
            switch (iStaffType) {
                case ContextInfo.USER_INVALID:
                    errorMsg = ERROR_MSG[INVALID_ERROR];
                    break;
                case ContextInfo.USER_CENSOR:
                    errorMsg = ERROR_MSG[CENSOR_ERROR];
                    break;
                case ContextInfo.USER_LOCKED:
                    errorMsg = ERROR_MSG[LOCKED_ERROR];
                    break;
                default:
                    break;
            }
            if (errorMsg == null) {
                String requestPwd = MD5Encode.encode(staff.getId() + staffDto.getPassword());
                if (!staff.getPasswd().equals(requestPwd)) {
                    errorMsg = ERROR_MSG[WRONG_PSWD_ERROR];
                }
            }
        }
        checkResult.put("errorMsg", errorMsg);
        if (errorMsg == null) {
            checkResult.put("staff", staff);
        }
        else {
            checkResult.put("staff", null);
        }
        return checkResult;
    }

    public Staff checkStaff(String userName) throws ApplicationException {
        Staff staff = staffMapper.findByUserName(userName);
        if (staff != null) {
            // 员工已经锁定
            String staffState = staff.getState();
            if (staffState.equals("2VL")) {
                // throw new SystemException("380013");
                throw new ApplicationException("该员工已经锁定!");
            }
            // 员工已经停用
            if (staffState.equals("2VX")) {
                // throw new SystemException("380014");
                throw new ApplicationException("该员工已经停用!");
            }
            return staff;
        } else {
            throw new ApplicationException("数据库内无该员工!");
        }
    }

    public int getStaffType(String userName, String state) {
        int iType;
        if (userName.equals(ContextInfo.BBS_LOGIN_NAME)) {
            iType = ContextInfo.USER_BBS;
        }
        else {
            if (state.equals("2VA") || state.equals("2VE")) {
                iType = ContextInfo.USER_SYS;
            }
            else if (state.equals("2VB")) {
                iType = ContextInfo.USER_BBS;
            }
            else if (state.equals("2VC")) {
                iType = ContextInfo.USER_UNSYS;
            }
            else if (state.equals("2VI")) {
                iType = ContextInfo.USER_ONLINE;
            }
            else if (state.equals("2VL")) {
                iType = ContextInfo.USER_LOCKED;
            }
            else if (state.equals("2VD")) {
                iType = ContextInfo.USER_CENSOR;
            }
            else {
                iType = ContextInfo.USER_INVALID;
            }
        }
        return iType;
    }

    public List<Staff> getStaffListByIds(List<Long> staffIdList) {
        return staffMapper.getStaffListByIds(staffIdList);
    }
    
    @SuppressWarnings("rawtypes")
	public List<Map> getProjectTree(Long startProjectId) {
    	return this.staffMapper.getProjectTree(startProjectId);
    }
    
    @SuppressWarnings("rawtypes")
	public List<Map> getFavoriteGroupList(Long staffId) {
    	return this.staffMapper.getFavoriteGroupList(staffId);
    }
    
    @SuppressWarnings("rawtypes")
    public Map getFavoriteGroupListById(Long staffId, Long groupId) {
    	return this.staffMapper.getFavoriteGroupListById(staffId, groupId);
    }
    
    @SuppressWarnings("rawtypes")
	public List<Map> findStaffByIds(String staffIds) {
    	return this.staffMapper.findStaffByIds(Arrays.asList(staffIds.split(",")));
    }
    

    @Override
    public BaseMapper<Staff, Long> getMapper() {
        return staffMapper;
    }

    public void updateStaffPwd(String userName, String passWord, Long staffId) throws SystemException {
        String requestPwd = MD5Encode.encode(staffId + passWord);
        staffMapper.updateStaffPwd(requestPwd, staffId);
    }

    public String getPopoverHtml() {
        return staffMapper.getPopoverHtml();
    }
}
