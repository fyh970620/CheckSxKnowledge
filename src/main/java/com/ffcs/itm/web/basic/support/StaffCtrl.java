/**************************************************
 * Copyright (c) 2005.
 * 文件名称: Staff.java
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-16
 **************************************************/
package com.ffcs.itm.web.basic.support;

import com.bsnnms.bean.common.DBCtrl;
import com.bsnnms.bean.common.StringTemplate;
import com.bsnnms.exception.ApplicationException;
import com.bsnnms.exception.SystemException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StaffCtrl {
	private static Logger LOG = Logger.getLogger(StaffCtrl.class);

	// 判断员工号是否重复
	private static final String SQL_COUNT_STAFF_ID = "SELECT STAFF_NAME FROM STAFF WHERE STAFF_ID = ?";

	// 判断员工登录名是否重复
	private static final String SQL_COUNT_STAFF_NAME = "SELECT STAFF_ID,STAFF_NAME FROM STAFF WHERE USER_NAME = ?";

	// 插入员工信息
	private static final String SQL_INSERT_STAFF = "INSERT INTO STAFF(STAFF_ID,USER_NAME,PASSWD,STAFF_NAME,STATE,REGION_ID,STAFF_DESC,STAFF_LEVEL,ORG_ID,STAFF_POST,STAFFB_ID,STAFFC_ID,"
			+ "PARENT_STAFF_ID,PRI_LEVEL,STAFF_GUID,CALLED_NAME,CREATE_TIME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYS_GUID(),?,SYSDATE)";

	// 插入个人信息
	private static final String SQL_INSERT_INDIVIDUAL = "INSERT INTO INDIVIDUAL(INDIVIDUAL_ID,STAFF_ID,GENDER,BIRTH_PLACE,BIRTH_DATE,MARITAL_STATUS,SKILL,SKILL2,ID_CARD) "
			+ "VALUES(INDIVIDUAL_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";

	// 插入个人联系信息
	private static final String SQL_INSERT_CONTACT = "INSERT INTO STAFF_CONTACT(STAFF_ID,LINKMAN_NAME,MOBILE,TEL,ADDRESS,FAX,EMAIL,REMARK,PHS,VOICE,SMS_RECEIVE,ISSEND,SMS_GATEWAY) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

	// 插入员工到岗位
	private static final String SQL_INSERT_STAFF_STA = "INSERT INTO STAFF_STATION(STAFF_ID,STATION_ID) VALUES(?,?)";

	// 更改用户密码
	private static final String SQL_CHAGE_PSWD = "UPDATE STAFF SET PASSWD = ? WHERE STAFF_ID = ?";

	// 取待选组织SQL
	private static final String SQL_UNSELECTED_ORG = "SELECT A.ORG_ID,A.ORG_NAME FROM ORGANIZATION A,(SELECT ORG_ID FROM STAFF_ORGANIZATION WHERE STAFF_ID = ?) B "
			+ "WHERE A.ORG_ID=B.ORG_ID(+) AND B.ORG_ID IS NULL AND A.ORG_ID <> 0 ORDER BY A.ORG_ID";

	// 取得员工所属组织SQL
	private static final String SQL_SELECTED_ORG = "SELECT A.ORG_ID,A.ORG_NAME FROM ORGANIZATION A,STAFF_ORGANIZATION B WHERE A.ORG_ID = B.ORG_ID AND B.STAFF_ID = ? ORDER BY A.ORG_ID";

	private static final String SQL_ALL_STA = "SELECT STATION_ID,STATION_NAME FROM STATION WHERE STATE = '0SA' AND (BPR_LINE IN (?) OR BPR_LINE = '0') ORDER BY STATION_ID";

	private static final String SQL_STAFF_CHILD = "SELECT STAFF_ID FROM STAFF CONNECT BY PRIOR STAFF_ID = PARENT_STAFF_ID START WITH STAFF_ID = ?";

	// private static final String SQL_STAFF_PARENT =
	// "SELECT STAFF_ID,STAFF_NAME FROM STAFF WHERE STAFF_ID != ? CONNECT BY
	// PRIOR PARENT_STAFF_ID = STAFF_ID START WITH STAFF_ID = ? ORDER BY
	// STAFF_POST, nlssort(STAFF_NAME,'NLS_SORT=SCHINESE_PINYIN_M')";
	private static final String SQL_STAFF_PARENT = "SELECT DISTINCT K.* FROM (SELECT STAFF_ID,STAFF_NAME FROM STAFF WHERE STAFF_ID != ? CONNECT BY PRIOR PARENT_STAFF_ID = STAFF_ID START WITH STAFF_ID = ? UNION ALL SELECT C.STAFF_ID,C.STAFF_NAME FROM AUDITS_CONFIG A,STAFF B,STAFF C WHERE A.STAFF_ID=B.STAFF_ID(+) AND A.STATE='0SA' AND A.STAFF_ID=? AND lower(A.TYPE)=lower(?) AND INSTR(','||A.AUDIT_IDS||',',','||C.STAFF_ID||',')>0) K";

	private static final String SQL_SET_STAFF_STATE_BY_ID = "UPDATE STAFF SET STATE = ?,STATE_DATE=SYSDATE WHERE STAFF_ID = ?";

	private static final String SQL_SET_STAFF_STATE_BY_NAME = "UPDATE STAFF SET STATE = ?,STATE_DATE=SYSDATE WHERE USER_NAME = ?";

	private static final String[] SQL_SET_STAFF_STATE = {
			SQL_SET_STAFF_STATE_BY_NAME, SQL_SET_STAFF_STATE_BY_ID };

	// 取得指定组织下的员工
	// private static final String SQL_ORG_STAFF = "SELECT
	// A.STAFF_ID,A.STAFF_NAME,A.USER_NAME,D.LIST_LABEL,DECODE(A.STATE,'2VA','可用','2VX','注销','2VI','已登陆','2VL','已锁定')
	// STATE,C.REGION_NAME FROM STAFF A,MANAGE_REGION C,(SELECT
	// DOMAIN_CODE,LIST_VALUE,LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE
	// DOMAIN_CODE='DOMAIM_STAFF_LEVEL') D WHERE A.REGION_ID = C.REGION_ID AND
	// A.STAFF_LEVEL=D.LIST_VALUE(+) AND A.STATE<>'2VX' AND A.ORG_ID = ? ORDER
	// BY A.STAFF_ID";
	private static final String SQL_ORG_STAFF = "SELECT A.STAFF_ID, DECODE(A.STAFF_ID, G.STAFF_ID, '<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">★</SPAN>')||DECODE(instr(','||F.VICE_PRINCIPAL||',',','||A.STAFF_ID||','),0,'','<SPAN STYLE=\"COLOR: #FFCC00;MARGIN-RIGHT:10PX\">★</SPAN>') ||  DECODE(A.STATE,'2VX','<span><img src=\"/resource/image/delTag.gif\"/></span>','2VD','<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">*</SPAN>') || decode(a.staff_type,'AD','<span><img src=\"/resource/image/pwdManage/staff/user.png\"/></span>'||A.STAFF_NAME,A.STAFF_NAME) STAFF_NAME, A.USER_NAME, (SELECT STRCAT(BLC.NAME) FROM BPR_LINE_CFG BLC,STAFF_BPR_RELA SBR WHERE BLC.BPR_LINE_CFG_ID = SBR.BPR_LINE_CFG_ID AND SBR.STAFF_ID=A.STAFF_ID) NAME, B.STAFF_NAME PARENT_STAFF_NAME, E.LIST_LABEL, C.REGION_NAME, DECODE(A.STATE, '2VD', 0, '2VF', 2, '2VX', 3, 1) STATE_SORT, A.STATE, A.ORG_ID, f.ORG_NAME, (SELECT LIST_LABEL FROM TP_DOMAIN_LISTVALUES TP WHERE DOMAIN_CODE = 'DOMAIN_STAFF_STATE' AND TP.LIST_VALUE = A.STATE) LIST_VALUE FROM STAFF A, STAFF B, MANAGE_REGION C, (SELECT DOMAIN_CODE, LIST_VALUE, LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIM_STAFF_POST') E, ORGANIZATION F, STAFF G,STAFF_ORG H WHERE G.STAFF_ID(+) = F.PRINCIPAL AND H.ORG_ID = F.ORG_ID AND B.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.REGION_ID = C.REGION_ID AND A.STAFF_POST = E.LIST_VALUE(+) AND A.STAFF_ID = H.STAFF_ID AND H.ORG_ID = ?  AND A.DEL_FLAG = '0BF' ORDER BY STATE_SORT, A.SORT_ID, A.STAFF_ID";
	private static final String SQL_ORG_STAFF_GATEWAY = "SELECT decode(h.user_name,null,'<span onclick=\"resetPwd(' || A.STAFF_ID || ')\"><img src=\"/resource/image/pwdManage/staff/u101.png\"/></span>','<span onclick=\"resetPwd(' || A.STAFF_ID ||')\"><img src=\"/resource/image/pwdManage/staff/u103.png\"/></span>') IMG,A.STAFF_ID, DECODE(A.STAFF_ID, G.STAFF_ID, '<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">★</SPAN>') || DECODE(instr(','||F.VICE_PRINCIPAL||',',','||A.STAFF_ID||','),0,'','<SPAN STYLE=\"COLOR: #FFCC00;MARGIN-RIGHT:10PX\">★</SPAN>') || DECODE(A.STATE,'2VX','<span><img src=\"/resource/image/delTag.gif\"/></span>','2VD','<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">*</SPAN>') || decode(a.staff_type,'AD','<span><img src=\"/resource/image/pwdManage/staff/user.png\"/></span>'||A.STAFF_NAME,A.STAFF_NAME) STAFF_NAME, A.USER_NAME, (SELECT STRCAT(BLC.NAME) FROM BPR_LINE_CFG BLC,STAFF_BPR_RELA SBR WHERE BLC.BPR_LINE_CFG_ID = SBR.BPR_LINE_CFG_ID AND SBR.STAFF_ID=A.STAFF_ID) NAME, B.STAFF_NAME PARENT_STAFF_NAME, E.LIST_LABEL, C.REGION_NAME, DECODE(A.STATE, '2VD', 0, '2VF', 2, '2VX', 3, 1) STATE_SORT, A.STATE, A.ORG_ID, f.ORG_NAME, (SELECT LIST_LABEL FROM TP_DOMAIN_LISTVALUES TP WHERE DOMAIN_CODE = 'DOMAIN_STAFF_STATE' AND TP.LIST_VALUE = A.STATE) LIST_VALUE,(SELECT MEAN FROM CODELIST WHERE CODE_TYPE = 'GATEWAY_TYPE' AND CODE = (SELECT SMS_GATEWAY FROM STAFF_CONTACT WHERE STAFF_ID = A.STAFF_ID)) SMS_GATEWAY FROM STAFF A, STAFF B, MANAGE_REGION C, (SELECT DOMAIN_CODE, LIST_VALUE, LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIM_STAFF_POST') E, ORGANIZATION F, STAFF G,password_protect H WHERE G.STAFF_ID(+) = F.PRINCIPAL AND A.ORG_ID = F.ORG_ID AND B.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.REGION_ID = C.REGION_ID AND A.STAFF_POST = E.LIST_VALUE(+) AND A.ORG_ID = ?  AND A.DEL_FLAG = '0BF' and h.user_name(+) = a.user_name ORDER BY STATE_SORT, A.SORT_ID, A.STAFF_ID";
	private static final String SQL_ORG_INFO_BY_RELATION2 = "SELECT decode(h.user_name,null,'<span onclick=\"resetPwd(' || A.STAFF_ID || ')\"><img src=\"/resource/image/pwdManage/staff/u101.png\"/></span>','<span onclick=\"resetPwd(' || A.STAFF_ID ||')\"><img src=\"/resource/image/pwdManage/staff/u103.png\"/></span>') IMG,A.STAFF_ID, DECODE(A.STAFF_ID, G.STAFF_ID, '<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">★</SPAN>') || decode(a.staff_type,'AD','<span><img src=\"/resource/image/pwdManage/staff/user.png\"/></span>'||A.STAFF_NAME,A.STAFF_NAME) STAFF_NAME, A.USER_NAME, (SELECT STRCAT(BLC.NAME) FROM BPR_LINE_CFG BLC, STAFF_BPR_RELA SBR WHERE BLC.BPR_LINE_CFG_ID = SBR.BPR_LINE_CFG_ID AND SBR.STAFF_ID = A.STAFF_ID) NAME, B.STAFF_NAME PARENT_STAFF_NAME, E.LIST_LABEL, C.REGION_NAME, DECODE(A.STATE, '2VD', 0, '2VF', 2, '2VX', 3, 1) STATE_SORT, A.STATE, A.ORG_ID, f.ORG_NAME,(SELECT LIST_LABEL FROM TP_DOMAIN_LISTVALUES TP WHERE DOMAIN_CODE = 'DOMAIN_STAFF_STATE' AND TP.LIST_VALUE = A.STATE) LIST_VALUE,(SELECT MEAN FROM CODELIST WHERE CODE_TYPE = 'GATEWAY_TYPE' AND CODE = (SELECT SMS_GATEWAY FROM STAFF_CONTACT WHERE STAFF_ID = A.STAFF_ID)) SMS_GATEWAY FROM STAFF A, STAFF B, MANAGE_REGION C, (SELECT DOMAIN_CODE, LIST_VALUE, LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIM_STAFF_POST') E, ORGANIZATION F, STAFF G,password_protect H WHERE G.STAFF_ID(+) = F.PRINCIPAL AND A.ORG_ID = F.ORG_ID AND B.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.REGION_ID = C.REGION_ID AND A.STAFF_POST = E.LIST_VALUE(+) AND A.DEL_FLAG = '0BF' AND A.STAFF_ID IN (SELECT STAFF_ID FROM STAFF_ORG_RELATION WHERE RELATION_ID = (SELECT RELATION_ID FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?)) and h.user_name(+) = a.user_name ORDER BY STATE_SORT, A.SORT_ID, A.STAFF_ID ";

	private static final String SQL_ALL_STAFF = "SELECT A.STAFF_ID, A.STATE, DECODE(A.STAFF_ID, G.STAFF_ID, '<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">★</SPAN>') || A.STAFF_NAME STAFF_NAME, A.USER_NAME, C.REGION_NAME, f.ORG_NAME, (SELECT LIST_LABEL FROM TP_DOMAIN_LISTVALUES TP WHERE DOMAIN_CODE = 'DOMAIN_STAFF_STATE' AND TP.LIST_VALUE = A.STATE) LIST_VALUE,'<a onclick=\"editStaff('||a.staff_id||')\">修改</a>&nbsp;'||decode(a.state,'2VX','<a onclick=\"unDelStaff('||a.staff_id||')\">启用</a>','<a onclick=\"delStaff('||a.staff_id||')\">注销</a>') ACTION FROM STAFF A, STAFF B, MANAGE_REGION C, (SELECT DOMAIN_CODE, LIST_VALUE, LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIM_STAFF_POST') E, ORGANIZATION F, STAFF G WHERE G.STAFF_ID(+) = F.PRINCIPAL AND A.ORG_ID = F.ORG_ID AND B.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.REGION_ID = C.REGION_ID AND A.STAFF_POST = E.LIST_VALUE(+) AND A.DEL_FLAG = '0BF' AND F.org_type <> '20E' and F.t_level>=2 and F.state='0SA'";

	private static final String SQL_ORG_STAFF_NAME = "SELECT A.STAFF_ID,A.STAFF_NAME,A.ORG_ID,pkp_staff_org.get_org_path(A.ORG_ID) ORG_NAME,A.STAFF_POST,A.STAFF_ID,(select max(m.region_name) from manage_region m where m.region_id = A.REGION_ID) SELF_REGION_NAME, "
			+ " (select g.org_name from organization g where g.org_id = A.ORG_ID) SELF_ORG_NAME,B.MOBILE,B.EMAIL FROM STAFF A,STAFF_CONTACT B, STAFF_ORG C WHERE A.STAFF_ID = B.STAFF_ID(+) AND A.STATE<>'2VX' AND A.STATE<>'2VB' AND A.STAFF_ID = C.STAFF_ID AND C.ORG_ID = ? &FILTER_STAFF_WHERE ORDER BY A.SORT_ID,A.STAFF_POST, nlssort(A.STAFF_NAME,'NLS_SORT=SCHINESE_PINYIN_M')";

	private static final String SQL_ORG_DUTY_STAFF_NAME = "SELECT A.STAFF_ID,NVL2(B.MOBILE,A.STAFF_NAME||' '|| B.MOBILE, A.STAFF_NAME) STAFF_NAME,A.ORG_ID,pkp_staff_org.get_org_path(A.ORG_ID) ORG_NAME,A.STAFF_POST,A.STAFF_ID FROM STAFF A,STAFF_CONTACT B WHERE A.STAFF_ID = B.STAFF_ID(+) AND A.STATE<>'2VX' AND A.STATE<>'2VB' AND A.ORG_ID = ? &FILTER_STAFF_WHERE ORDER BY A.SORT_ID,A.STAFF_POST, nlssort(A.STAFF_NAME,'NLS_SORT=SCHINESE_PINYIN_M')";

	private static final String SQL_STAFF_BY_PRIVILEGE = "SELECT A.STAFF_ID, A.STAFF_NAME FROM STAFF A,(SELECT STAFF_ID FROM STAFF_PRIVILEGE WHERE PRIVILEGE_ID = ? UNION SELECT A.STAFF_ID FROM STAFF_STATION A, STATION_PRIVILEGE B WHERE A.STATION_ID = B.STATION_ID AND B.PRIVILEGE_ID = ? UNION SELECT 1 FROM DUAL) B WHERE A.STAFF_ID = B.STAFF_ID AND A.STATE IN('2VA','2VI','2VL') AND A.ORG_ID=DECODE(?,0,A.ORG_ID,?) ORDER BY A.STAFF_POST, nlssort(A.STAFF_NAME,'NLS_SORT=SCHINESE_PINYIN_M')";

	// 取得员工密码
	private static final String SQL_GET_PSWD = "SELECT PASSWD FROM STAFF WHERE STAFF_ID = ?";

	// 取得指定岗位下的员工
	// private static final String SQL_STA_STAFF = "SELECT
	// A.STAFF_ID,A.STAFF_NAME,D.LIST_LABEL,DECODE(A.STATE,'2VA','可用','2VX','注销','2VI','已登陆','2VL','已锁定')
	// STATE,C.REGION_NAME,'-' MANAGER FROM STAFF A,STAFF_STATION
	// B,MANAGE_REGION C,(SELECT DOMAIN_CODE,LIST_VALUE,LIST_LABEL FROM
	// TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE='DOMAIM_STAFF_LEVEL') D WHERE
	// A.STAFF_ID=B.STAFF_ID AND A.REGION_ID = C.REGION_ID AND
	// A.STAFF_LEVEL=D.LIST_VALUE(+) AND A.STATE<>'2VX' AND B.STATION_ID = ?
	// ORDER BY A.STAFF_ID";

	// 删除员工岗位关系
	private static final String SQL_DEL_STAFF_STA = "DELETE FROM STAFF_STATION WHERE STAFF_ID = ?";

	// 删除员工信息
	private static final String SQL_DEL_STAFF = "UPDATE STAFF SET STATE = '2VX' WHERE STAFF_ID = ?";

	private static final String SQL_UNDEL_STAFF = "UPDATE STAFF SET STATE = '2VA' WHERE STAFF_ID = ?";

	// 得到员工基本信息
	private static final String SQL_GET_STAFF = "SELECT A.STAFF_ID,A.PRI_LEVEL ,A.STATE,A.USER_NAME,A.CALLED_NAME,A.STAFF_NAME,A.ORG_ID,A.STAFF_LEVEL,A.STAFF_POST,B.ID_CARD,A.STAFF_DESC,A.REGION_ID,A.STAFFB_ID,A.STAFFC_ID,A.PARENT_STAFF_ID,C.STAFF_NAME STAFFB_NAME,D.STAFF_NAME STAFFC_NAME,E.STAFF_NAME PARENT_STAFF_NAME,B.GENDER,B.BIRTH_PLACE,TO_CHAR(B.BIRTH_DATE, 'YYYY-MM-DD') BIRTH_DATE,B.MARITAL_STATUS,B.SKILL,B.SKILL2,F.MOBILE,F.TEL,F.ADDRESS,F.FAX,F.EMAIL,F.REMARK,Replace(F.PHS, '-', '') PHS,F.VOICE,G.STAFF_ID CENSOR_STAFF_ID,G.STAFF_NAME CENSOR_STAFF_NAME,PKP_ALARM_RULE.GET_STAFF_SMS_TERMINAL(A.STAFF_ID) SMS_TERMINAL ,F.SMS_GATEWAY,F.ISSEND, '' BPR_LINE, (SELECT STRCAT(H.ORG_ID) FROM STAFF_ORG H WHERE H.STAFF_ID = A.STAFF_ID) ORG_IDS, (SELECT STRCAT(ORG.ORG_NAME) FROM STAFF_ORG H, ORGANIZATION ORG WHERE H.ORG_ID = ORG.ORG_ID AND H.STAFF_ID = A.STAFF_ID) ORG_NAMES FROM STAFF A,INDIVIDUAL B,STAFF C,STAFF D,STAFF E,STAFF_CONTACT F,STAFF G WHERE F.STAFF_ID(+) = A.STAFF_ID AND C.STAFF_ID(+) = A.STAFFB_ID AND D.STAFF_ID(+) = A.STAFFC_ID AND E.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.STAFF_ID = B.STAFF_ID(+) AND G.STAFF_ID(+) = A.CENSOR_STAFF AND A.STAFF_ID = ?";

	private static final String SQL_GET_STAFF_SELECT = "SELECT STAFF_ID,STAFF_NAME FROM STAFF";

	// 修改员工信息
	private static final String SQL_UPDATE_STAFF = "UPDATE STAFF SET STAFF_NAME=?,STAFF_LEVEL=?,STAFF_DESC=?,REGION_ID=?,STAFF_POST=?,STAFFB_ID=?,STAFFC_ID=?,PARENT_STAFF_ID=?,USER_NAME=?,ORG_ID=?,PRI_LEVEL=?,CALLED_NAME=? WHERE STAFF_ID = ?";

	private static final String SQL_UPDATE_STAFF_STATE = "UPDATE STAFF SET STATE='2VD' WHERE STAFF_ID = ?";

	// 修改个人信息
	private static final String SQL_UPDATE_INDIVIDUAL = "UPDATE INDIVIDUAL SET GENDER=?,BIRTH_PLACE=?,BIRTH_DATE=?,MARITAL_STATUS=?,SKILL=?,SKILL2=?,ID_CARD=? WHERE STAFF_ID = ?";

	// 更新个人联系信息
	private static final String SQL_UPDATE_CONTACT = "UPDATE STAFF_CONTACT SET LINKMAN_NAME=?,MOBILE=?,TEL=?,ADDRESS=?,FAX=?,EMAIL=?,REMARK=?,PHS=?,VOICE=?,SMS_RECEIVE=?,ISSEND=?,SMS_GATEWAY=? WHERE STAFF_ID = ?";

	// 查找员工快捷菜单数目
	private static final String SQL_GET_STAFF_MENU_COUNT = "SELECT COUNT(*) FROM STAFF_SHORTCUT_MENU WHERE PRIVILEGE_ID = ? AND STAFF_ID = ?";

	// 添加员工快捷菜单
	private static final String SQL_ADD_STAFF_MENU = "INSERT INTO STAFF_SHORTCUT_MENU(STAFF_ID,PRIVILEGE_ID) VALUES(?,?)";

	// 删除员工快捷菜单
	private static final String SQL_DEL_STAFF_MENU = "DELETE STAFF_SHORTCUT_MENU WHERE PRIVILEGE_ID = ? AND STAFF_ID = ?";

	// 获取员工快捷菜单
	private static final String SQL_GET_STAFF_MENU = "SELECT A.PRIVILEGE_ID,A.PRIVILEGE_NAME,A.SCRIPT_NAME,A.SERVER_URL_NAME FROM PRIVILEGE A,STAFF_SHORTCUT_MENU B WHERE A.PRIVILEGE_ID=B.PRIVILEGE_ID AND B.STAFF_ID = ?";

	private static final String DEL_STAFF_PRI = "DELETE FROM STAFF_PRIVILEGE WHERE STAFF_ID = ? AND PRIVILEGE_ID = ?";

	// 添加员工权限列表
	private static final String ADD_STAFF_PRI = "INSERT INTO STAFF_PRIVILEGE(STAFF_ID,PRIVILEGE_ID) VALUES(?,?)";

	private static final String COUNT_LINE_STAFF = "SELECT COUNT(sbr.STAFF_ID) FROM STAFF_BPR_RELA sbr, STAFF s WHERE sbr.STAFF_ID=s.STAFF_ID AND s.ORG_ID=? AND sbr.BPR_LINE_CFG_ID = ?";

	// 设置组织领导
	private static final String SQL_SET_MANAGE = "UPDATE ORGANIZATION SET PRINCIPAL=? WHERE ORG_ID = ?";

	// 取消组织领导
	private static final String SQL_CANCEL_MANAGE = "UPDATE ORGANIZATION SET PRINCIPAL=NULL WHERE ORG_ID = ?";

	// 取得目前领导数目
	private static final String SQL_GET_MANAGE = "SELECT A.STAFF_ID,A.STAFF_NAME FROM STAFF A,ORGANIZATION B WHERE A.STAFF_ID = B.PRINCIPAL AND B.ORG_ID =?";

	// 取得所在部门的负责人
	private static final String SQL_GET_PRINCIPAL = "SELECT A.PRINCIPAL FROM ORGANIZATION A,STAFF B WHERE A.ORG_ID = B.ORG_ID AND B.STAFF_ID = ?";

	private static final String SQL_NEW_STAFF_ID = "SELECT MAX(STAFF_ID)+1 FROM STAFF";

	private static final String SQL_GET_BBS_ORG_ID = "SELECT ORG_ID FROM ORGANIZATION WHERE ORG_NAME = '*论坛用户'";

	// private static final String SQL_CENSOR_STAFF_INSERT =
	// "INSERT INTO
	// STAFF_CENSOR(CENSOR_ID,STAFF_ID,CENSOR_STAFF,CENSOR_RESULT,CENSOR_MSG,CENSOR_DATE)
	// VALUES (CENSOR_ID_SEQ.NEXTVAL,?,?,?,?,SYSDATE)";

	private static final String SQL_CENSOR_MSG = "SELECT B.STAFF_NAME CENSOR_NAME, C.LIST_LABEL CENSOR_RESULT, A.CENSOR_MSG, TO_CHAR(A.CENSOR_DATE,'YYYY-MM-DD HH24:MI:SS') CENSOR_DATE FROM STAFF_CENSOR A, STAFF B, TP_DOMAIN_LISTVALUES C WHERE A.CENSOR_STAFF = B.STAFF_ID AND C.DOMAIN_CODE = 'DOMAIN_STAFF_CENSOR_RESULT' AND C.LIST_VALUE = A.CENSOR_RESULT AND A.STAFF_ID = ? ORDER BY A.CENSOR_DATE DESC";

	private static final String SQL_DEFAULT_CENSOR = "SELECT A.STAFF_ID,A.STAFF_NAME FROM STAFF A,SYS_CONFIG B WHERE A.STAFF_ID = B.SYS_VAR_VALUE AND B.SYS_VAR='DEFAULT_CENSOR_STAFF'";

	private static final String CMD_ADD_DEFAULT_FAV = "{CALL PKP_FAVORITES.ADD_DEFAULT_FAV(?)}";

	private static final String SQL_PROJECT_STAFF_NAME = "SELECT A.STAFF_ID, A.STAFF_NAME,GROUP_ID,pkp_staff_org.get_org_path(A.ORG_ID) ORG_NAME,A.STAFF_POST,A.STAFF_ID,(select max(m.region_name) from manage_region m where m.region_id = A.REGION_ID) SELF_REGION_NAME,"
			+ "(select g.org_name from organization g where g.org_id = A.ORG_ID) SELF_ORG_NAME,B1.MOBILE,B1.EMAIL FROM STAFF A,STAFF_PROJECT_GROUP B, STAFF_CONTACT B1 WHERE A.STAFF_ID = B1.STAFF_ID(+) AND A.STAFF_ID = B.STAFF_ID AND A.STATE <> '2VX' AND A.STATE <> '2VB' AND B.GROUP_ID = ? &filterStaffWhere ORDER BY B.SORT_ID,A.STAFF_POST, nlssort(A.STAFF_NAME,'NLS_SORT=SCHINESE_PINYIN_M')";

	// 判断员工是否省级负责人
	private static final String SQL_GET_PROV_PRINCIPAL = "{? = call PKP_ORG.getProvPrincipal(?)}";

	private static final String SQL_GET_STAFF_NAME = "SELECT STAFF_NAME FROM STAFF WHERE STAFF_ID = ?";

	private static final String SQL_GET_STAFF_ID = "SELECT STAFF_ID_SEQ.NEXTVAL FROM DUAL";

	private static final String SQL_GET_STAFF_COUNT_BY_ID = "SELECT COUNT(STAFF_ID) FROM STAFF WHERE STAFF_ID = ?";

	private static final String SQL_GET_STAFF_EVENT_TYPE = " SELECT STATE, STATE_DESC FROM STATE WHERE TABLE_NAME = 'EVENT_Q' AND COL_NAME = 'EVENT_TYPE' AND  NOT exists  (SELECT EVENT_TYPE FROM staff_event_type_config config WHERE  STAFF_ID=? and config.EVENT_TYPE=state )";

	private static final String SQL_GET_STAFF_SELECTED_ETYPE = " SELECT a.STATE, a.STATE_DESC FROM STATE a, STAFF_EVENT_TYPE_CONFIG b WHERE a.STATE=b.EVENT_TYPE and a.TABLE_NAME = 'EVENT_Q' AND a.COL_NAME = 'EVENT_TYPE' and b.STAFF_ID=?";

	private static final String SQL_ADD_STAFF_EVNET_TYPE = "INSERT INTO STAFF_EVENT_TYPE_CONFIG(STAFF_ID,EVENT_TYPE) VALUES(?,?)";

	private static final String SQL_DELETE_STAFF_EVENT_TYPE = "DELETE FROM STAFF_EVENT_TYPE_CONFIG WHERE STAFF_ID=? ";

	private static final String SQL_SYNC_ODSO_USER_INFO = "{call PKP_SYNC_ODSO_USER.updateOdsoUserInfo(?,?)}";

	private static final String SQL_SYNC_ODSO_ORG_INFO = "{call PKP_SYNC_ODSO_USER.updateOdsoOrgInfo(?,?)}";

	// private static final String SQL_GET_PATH_FOR_STAFF =
	// "SELECT ORG_ID,ORG_NAME FROM ORGANIZATION WHERE STATE='0SA' CONNECT BY
	// PRIOR PARENT_ORG_ID=ORG_ID START WITH ORG_ID =?";

	private static final String SQL_GET_SMS_RECEIVE_TERMINAL = "SELECT TERMINAL_ID, TERMINAL_NAME FROM SMS_RECEIVE_TERMINAL ORDER BY SORT_ID";

	private static final String GET_STAFF_LIST_BY_STAFFIDS = "SELECT A.STAFF_ID, DECODE(A.STAFF_ID, G.STAFF_ID, '<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">★</SPAN>') || A.STAFF_NAME STAFF_NAME, A.USER_NAME, (SELECT STRCAT(BLC.NAME) FROM BPR_LINE_CFG BLC,STAFF_BPR_RELA SBR WHERE BLC.BPR_LINE_CFG_ID = SBR.BPR_LINE_CFG_ID AND SBR.STAFF_ID=A.STAFF_ID) NAME, B.STAFF_NAME PARENT_STAFF_NAME, E.LIST_LABEL, C.REGION_NAME, DECODE(A.STATE, '2VD', 0, '2VF', 2, '2VX', 3, 1) STATE_SORT, A.STATE, A.ORG_ID, f.ORG_NAME, (SELECT LIST_LABEL FROM TP_DOMAIN_LISTVALUES TP WHERE DOMAIN_CODE = 'DOMAIN_STAFF_STATE' AND TP.LIST_VALUE = A.STATE) LIST_VALUE FROM STAFF A, STAFF B, MANAGE_REGION C, (SELECT DOMAIN_CODE, LIST_VALUE, LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIM_STAFF_POST') E, ORGANIZATION F, STAFF G WHERE G.STAFF_ID(+) = F.PRINCIPAL AND A.ORG_ID = F.ORG_ID AND B.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.REGION_ID = C.REGION_ID AND A.STAFF_POST = E.LIST_VALUE(+) AND A.STAFF_ID IN (@STAFF_IDS@) ORDER BY STATE_SORT,A.STAFF_ID";

	private static final String SQL_GET_STAFF_INFO = "SELECT STAFF_ID,STAFF_NAME FROM STAFF WHERE STAFF_ID = ?";

	// / 用户审核相关
	/**
	 * 获取审核用户
	 */
	private static final String SQL_GET_CENSORS = "SELECT PKP_USER_CENSOR.getCensors(?,?) FROM DUAL";
	/**
	 * 取得当前用户审核信息
	 */
	private static final String SQL_GET_CURRENT_CENSOR_INFO = "SELECT CENSOR_ID, IS_FORCE_CENSOR FROM STAFF_CENSOR WHERE STAFF_ID=? AND CENSOR_STAFF=? AND CENSOR_STATE=?";
	/**
	 * 更新员工审核记录信息
	 */
	private static final String SQL_UPDATE_STAFF_CENSOR = "UPDATE STAFF_CENSOR SET CENSOR_RESULT=?,CENSOR_MSG=?,CENSOR_DATE=SYSDATE,CENSOR_STATE=? WHERE CENSOR_ID=?";
	/**
	 * 统计某待审员工的未审记录数
	 */
	private static final String SQL_GET_CENSOR_STAFF_COUNT = "SELECT COUNT(*) FROM STAFF_CENSOR WHERE STAFF_ID=? AND CENSOR_STATE=?";
	/**
	 * 获取下一审核记录
	 */
	private static final String SQL_GET_NEXT_STAFF_CENSOR = "SELECT CENSOR_ID FROM (SELECT ROWNUM,CENSOR_ID FROM STAFF_CENSOR WHERE STAFF_ID=? AND CENSOR_STATE=? ORDER BY SORT_ID) WHERE ROWNUM=1";
	/**
	 * 更新审核记录
	 */
	private static final String SQL_UPDATE_CENSOR_STAFF_STATE = "UPDATE STAFF_CENSOR SET CENSOR_STATE=? WHERE CENSOR_ID=?";
	/**
	 * 更新员工状态
	 */
	private static final String UPDATE_STAFF_STATE = "UPDATE STAFF SET STATE=?,CENSOR_STAFF=?,CENSOR_DATE=SYSDATE WHERE STAFF_ID = ?";
	/**
	 * 删除用户审核信息
	 */
	private static final String SQL_DEL_STAFF_CENSOR = "DELETE FROM STAFF_CENSOR WHERE STAFF_ID=? AND CENSOR_STATE IN (?, ?)";
	/**
	 * 员工待审信息
	 */
	private static final String SQL_CHECK_CENSOR_MSG = "SELECT B.STAFF_NAME CENSOR_NAME, C.LIST_LABEL CENSOR_STATE FROM STAFF B, STAFF_CENSOR A LEFT JOIN TP_DOMAIN_LISTVALUES C ON C.LIST_VALUE = A.CENSOR_STATE AND C.DOMAIN_CODE = 'DOMAIN_CENSOR_STAFF_STATE' WHERE A.CENSOR_STAFF = B.STAFF_ID AND A.STAFF_ID = ? AND A.CENSOR_STATE<>? ORDER BY A.SORT_ID";
	/**
	 * 添加员工审核记录
	 */
	private static final String SQL_CENSOR_STAFF_INSERT = "INSERT INTO STAFF_CENSOR(CENSOR_ID,STAFF_ID,CENSOR_STAFF,CENSOR_STATE,SORT_ID,IS_FORCE_CENSOR,CENSOR_DATE) VALUES (CENSOR_ID_SEQ.NEXTVAL,?,?,?,?,?,SYSDATE)";
	/**
	 * 获取员工信息(机构id,姓名)，用于审核时向审核人发送员工信息
	 */
	private static final String SQL_GET_STAFF_INFO_FOR_MSG = "SELECT ORG_ID, STAFF_NAME FROM STAFF WHERE STAFF_ID=?";

	/*
     * 根据登录名获取员工数量
     */
	private static final String SQL_GET_STAFF_COUNT = "SELECT COUNT(STAFF_ID) FROM STAFF WHERE USER_NAME = ?";
	private static final String SQL_GET_STAFF_COUNT_LIKE = "SELECT COUNT(STAFF_ID) FROM STAFF WHERE USER_NAME LIKE ? || '-%'";
	// 112 根据关联id获取用户部门数
	private static final String SQL_GET_STAFF_BY_NAME = "SELECT STAFF_ID FROM STAFF WHERE USER_NAME = ?";
	// 判断用户是否和指定的根有关联
	private static final String SQL_GET_STAFF_RELATION = "SELECT 1 FROM STAFF_ORG_RELATION WHERE STAFF_ID = ? and relation_id = (select relation_id from STAFF_ORG_RELATION where staff_id = (select staff_id from staff where user_name = ?))";
	private static final String SQL_GET_NAME_BY_STAFF = "SELECT USER_NAME FROM STAFF WHERE STAFF_ID = ?";
	private static final String SQL_ORG_INFO_STAFF_BY_RELATION = "SELECT A.STAFF_ID, DECODE(A.STAFF_ID, G.STAFF_ID, '<SPAN STYLE=\"COLOR: #FF0000;MARGIN-RIGHT:10PX\">★</SPAN>') || decode(a.staff_type,'AD','<span><img src=\"/resource/image/pwdManage/staff/user.png\"/></span>'||A.STAFF_NAME,A.STAFF_NAME) STAFF_NAME, A.USER_NAME, (SELECT STRCAT(BLC.NAME) FROM BPR_LINE_CFG BLC, STAFF_BPR_RELA SBR WHERE BLC.BPR_LINE_CFG_ID = SBR.BPR_LINE_CFG_ID AND SBR.STAFF_ID = A.STAFF_ID) NAME, B.STAFF_NAME PARENT_STAFF_NAME, E.LIST_LABEL, C.REGION_NAME, DECODE(A.STATE, '2VD', 0, '2VF', 2, '2VX', 3, 1) STATE_SORT, A.STATE, A.ORG_ID, f.ORG_NAME,(SELECT LIST_LABEL FROM TP_DOMAIN_LISTVALUES TP WHERE DOMAIN_CODE = 'DOMAIN_STAFF_STATE' AND TP.LIST_VALUE = A.STATE) LIST_VALUE FROM STAFF A, STAFF B, MANAGE_REGION C, (SELECT DOMAIN_CODE, LIST_VALUE, LIST_LABEL FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIM_STAFF_POST') E, ORGANIZATION F, STAFF G WHERE G.STAFF_ID(+) = F.PRINCIPAL AND A.ORG_ID = F.ORG_ID AND B.STAFF_ID(+) = A.PARENT_STAFF_ID AND A.REGION_ID = C.REGION_ID AND A.STAFF_POST = E.LIST_VALUE(+) AND A.DEL_FLAG = '0BF' AND A.STAFF_ID IN (SELECT STAFF_ID FROM STAFF_ORG_RELATION WHERE RELATION_ID = (SELECT RELATION_ID FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?)) ORDER BY STATE_SORT, A.SORT_ID, A.STAFF_ID ";
	private static final String SQL_GET_STAFF_ORG = "SELECT COUNT(STAFF_ID) FROM STAFF_ORG_RELATION WHERE RELATION_ID = (SELECT RELATION_ID FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?)";
	// 插入关联表
	private static final String SQL_INSERT_STAFF_ORG_RELATION = "insert into STAFF_ORG_RELATION(STAFF_ID,RELATION_ID,IS_DEFAULT)VALUES(?,?,'0BF')";
	// 关联表是否存在数据
	private static final String SQL_GET_SORG_RELATION_COUNT = "SELECT COUNT(STAFF_ID) FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?";
	// 更新关联表
	private static final String SQL_UPDATE_STAFF_ORG_RELATION = "UPDATE STAFF_ORG_RELATION SET RELATION_ID = ?,IS_DEFAULT = '0BF' WHERE STAFF_ID = ?";
	// 根据用户id获取用户部门index.jsp
	private static final String SQL_GET_ORG_BY_STAFF_ID = "SELECT B.ORG_NAME FROM STAFF A,ORGANIZATION B WHERE A.ORG_ID = B.ORG_ID AND A.STAFF_ID = ?";
	// 重置用户默认部门
	private static final String SQL_RESET_STAFF_ORG = "UPDATE STAFF_ORG_RELATION SET IS_DEFAULT = '0BF' WHERE RELATION_ID = (SELECT RELATION_ID FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?)";
	// 设置用户默认部门
	private static final String SQL_SET_STAFF_ORG = "UPDATE STAFF_ORG_RELATION SET IS_DEFAULT = '0BT' WHERE STAFF_ID = ?";
	// 获取用户默认部门
	private static final String SQL_GET_STAFF_ID_ORG = "SELECT STAFF_ID FROM STAFF_ORG_RELATION WHERE RELATION_ID = (SELECT RELATION_ID FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?) AND IS_DEFAULT = '0BT'";

	// 判断是否已存在指定 关联id和部门ID的数据
	private static final String SQL_GET_STAFF_ORG_COUNT = "SELECT COUNT(STAFF_ID) FROM STAFF WHERE user_name != ? and STAFF_ID IN (SELECT STAFF_ID FROM STAFF_ORG_RELATION WHERE RELATION_ID = (SELECT RELATION_ID FROM STAFF_ORG_RELATION WHERE STAFF_ID = ?) AND ORG_ID = ?)";
	// 117 判断是否已存在指定 关联id和部门ID的数据

	// 插入到审核表中
	private static final String SQL_INSERT_STAFF_PROJECT_GROUP = "INSERT INTO STAFF_PROJECT_GROUP(STAFF_ID,GROUP_ID,STATE) VALUES(?,?,?)";

	private static final StringTemplate SQL_ITSM_PROJECT_INTERFACE = new StringTemplate(
			"SELECT DISTINCT A.STAFF_ID, D.REGION_NAME || '-' || A.STAFF_NAME STAFF_NAME FROM STAFF A, PROJECT_GROUP_STAFF_CFG B, PROJECT_GROUP C, MANAGE_REGION D WHERE A.STAFF_ID = B.VALUE AND B.GROUP_ID = C.GROUP_ID AND A.REGION_ID = D.REGION_ID AND B.GROUP_ID IN ({$PROJECT_ID})");

	private static final StringTemplate SQL_ITSM_REGION_INTERFACE = new StringTemplate(
			"SELECT ST.STAFF_ID, ST_MG.REGION_NAME || '-' || ST.STAFF_NAME STAFF_NAME FROM ORGANIZATION T, MANAGE_REGION RT, STAFF ST, MANAGE_REGION ST_MG WHERE T.REGION_ID = RT.REGION_ID AND ST.REGION_ID = T.REGION_ID AND ST.REGION_ID = ST_MG.REGION_ID AND ST.STAFF_POST in ('C') AND RT.REGION_NAME IN ({$REGION_NAME}) AND T.ORG_NAME LIKE '%-企业信息化部'");

	// 获取员工所在虚拟组
	private static final String SQL_GET_STAFF_PROJECT_GROUP = "SELECT STRCAT(PG.GROUP_NAME) GROUP_NAME FROM PROJECT_GROUP PG WHERE PG.GROUP_ID IN (SELECT DISTINCT SPG.GROUP_ID FROM STAFF_PROJECT_GROUP SPG WHERE SPG.STAFF_ID = ?)";

	// 从虚拟组移除员工
	private static final String SQL_DEL_STAFF_PROJECT_GROUP = "DELETE STAFF_PROJECT_GROUP WHERE STAFF_ID = ?";

	private static final String SQL_DEL_PROJECT_GROUP_LEADER = "UPDATE PROJECT_GROUP PG SET PG.LEADER = '' WHERE PG.GROUP_ID IN(SELECT PG.GROUP_ID FROM PROJECT_GROUP PG WHERE PG.LEADER = ?)";

	private static final String SQL_DEL_PROJECT_GROUP_STAFF = "DELETE PROJECT_GROUP_STAFF_CFG PGSC WHERE PGSC.COLUMN_NAME = 'STAFF_ID' AND PGSC.VALUE = ?";

	private static final String SQL_GET_ORG_ALL = "select o.org_id,o.org_name from organization o  where o.org_type <> '20E' and o.t_level>=2 and o.state='0SA' order by o.parent_org_id,o.sort_id";

	private static final String SQL_STAFF_SELECTED = "SELECT STAFF_ID,STAFF_NAME FROM STAFF WHERE INSTR(','||?||',',','||STAFF_ID||',')>0";

	// 得到员工基本信息 包括区域名 组织名
	private static final String SQL_GET_STAFF_FOR_FLOW_PROC = "SELECT A.STAFF_ID,A.STAFF_NAME,B.MOBILE,B.TEL,Replace(B.PHS, '-', '') PHS,PKP_EXE_PERSON_INFOR_CONFIG.getOrgPathName(T.P) ORG_N,C.REGION_NAME FROM STAFF A,STAFF_CONTACT B,MANAGE_REGION C,(SELECT SUBSTR(SYS_CONNECT_BY_PATH(Org_Id, '-'), 2) P, ORG_ID FROM ORGANIZATION CONNECT BY PRIOR ORG_ID = PARENT_ORG_ID START WITH ORG_ID = 0) T WHERE B.STAFF_ID(+) = A.STAFF_ID AND A.ORG_ID = T.ORG_ID(+) AND A.REGION_ID = C.REGION_ID AND A.STAFF_ID = ?";

	// 获取配置
	private static final String SQL_GET_SYS_SMS_SORT_CFG = "select t.module_item_id,t.module_item_code,t.module_item_name,t.module_type,m.list_label module_name,t.sms_type,s.list_label sms_name,b.is_receive from SYS_SMS_SORT t, tp_domain_listvalues m, tp_domain_listvalues s,(select sc.staff_id, sc.module_item_id, sc.is_receive from staff_sms_cfg sc where sc.staff_id = ?) b where t.module_item_id = b.module_item_id(+) and t.module_type = m.list_value and t.sms_type = s.list_value and s.domain_code = 'DOMAIN_SYS_SMS_TYPE' and m.domain_code = 'DOMAIN_SYS_SMS_MODULE_TYPE' and t.state = '0SA' order by t.sort_id,t.module_type,t.module_item_code,t.sms_type";

	// 判断员工是否省级负责人
	private static final String SQL_UPDATE_STAFF_SMS_SORT_CFG = "{call PKP.getProvPrincipal(?)}";

	// 加载部门工作线
	private static final String LOAD_ORG_BPR_LINE = "SELECT a.BPR_LINE_CFG_ID ID, a.NAME FROM BPR_LINE_CFG a, ORG_BPRLINE b WHERE a.BPR_LINE_CFG_ID <> 0 AND b.BPR_LINE = a.BPR_LINE_CFG_ID AND b.ORG_ID in (select column_value from table(pkp_string_util.inlist(?))) AND PKP_TREE_PRIVILEGE.HASPRIVILEGE('BPR_LINE', a.BPR_LINE_CFG_ID, ?, 'QUERY') = 1 ORDER BY a.BPR_LINE_CFG_ID";
	// 加载用户工作线
	private static final String LOAD_STAFF_BPR_LINE = "SELECT a.BPR_LINE_CFG_ID ID, a.NAME FROM BPR_LINE_CFG a, STAFF_BPR_RELA b WHERE a.BPR_LINE_CFG_ID = b.BPR_LINE_CFG_ID AND b.STAFF_ID = ? AND PKP_TREE_PRIVILEGE.HASPRIVILEGE('BPR_LINE', a.BPR_LINE_CFG_ID, ?, 'QUERY') = 1";
	// 加截工作线与任务关联信息
	private static final String LOAD_LINE_TASKS = "SELECT BPR_LINE, STATION_ID, STATION_NAME FROM STATION WHERE STATE = ''0SA'' AND (BPR_LINE IN ({0})) AND PKP_TREE_PRIVILEGE.HASPRIVILEGE(''BPR_LINE'', BPR_LINE, ?, ''QUERY'') = 1 ORDER BY STATION_ID";
	private static final String LOAD_ORG_TASKS = "SELECT STATION_ID, STATION_NAME FROM STATION WHERE STATE = ''0SA'' AND (BPR_LINE IN ({0})) and (STATION_ID not in ({1})) AND PKP_TREE_PRIVILEGE.HASPRIVILEGE(''BPR_LINE'', BPR_LINE, ?, ''QUERY'') = 1 ORDER BY STATION_ID";
	// 删除用户工作线
	private static final String SQL_DEL_STAFF_BPR_RELA = "DELETE FROM STAFF_BPR_RELA WHERE STAFF_ID = ?";
	// 新增用户工作线
	private static final String SQL_INSERT_STAFF_BPR_RELA = "INSERT INTO STAFF_BPR_RELA(RELA_ID, BPR_LINE_CFG_ID, STAFF_ID) VALUES(STAFF_BPR_RELA_SEQ.nextVal, ?, ?)";
	// 获取短信网管组数据字典
	private static final String SQL_GET_SMS_GATEWAY = "SELECT CODE,MEAN FROM CODELIST WHERE CODE_TYPE = 'GATEWAY_TYPE' ORDER BY SORT_ID";
	private static final String INSERT_STAFF_ORG = "INSERT INTO STAFF_ORG(ORG_ID,STAFF_ID) VALUES (?,?)";

	private static final String DELETE_STAFF_ORG_BY_STAFF_ID = "DELETE STAFF_ORG T WHERE T.STAFF_ID = ?";

	// 获取部门副职领导
	private static final String SQL_GET_VICE_MANAGE = "SELECT A.STAFF_ID,A.STAFF_NAME FROM STAFF A,ORGANIZATION B WHERE A.STAFF_ID = B.PRINCIPAL AND B.ORG_ID =?";
	// 设置部门副职领导
	private static final String SQL_SET_VICE_MANAGE = "UPDATE ORGANIZATION SET PRINCIPAL=? WHERE ORG_ID = ?";

	// 根据组织id获取所有的上级关联的组织id
	private static final String   SQL_GET_PARENT_ORGID      = "SELECT ORG_ID FROM ORGANIZATION START WITH ORG_ID=? CONNECT BY PRIOR PARENT_ORG_ID=ORG_ID";
	// 根据员工id查找该工作线的id
	private static final String SQL_GET_LINE_BY_STAFF_ID ="SELECT BPR_LINE_CFG_ID FROM STAFF_BPR_RELA WHERE STAFF_ID=? and rownum=1";
	//关联系统id和员工id
	private static final String DEL_STAFF_SYS = "DELETE FROM SYSIMPORT_STAFF WHERE SYS_ID = ? AND STAFF_ID = ?";
	private static final String SQL_GET_SYS_BY_PRIVILEGE = "select t.system_source_id from NEW_FUNCTION t where t.PRIVILEGE_ID = ?";
	private static final String SQL_INSERT_TABLE = "INSERT INTO SYSIMPORT_STAFF(SYS_ID,STAFF_ID) VALUES(?,?)";

	public static void setStaffState(String loginName, String state, String sType)
			throws SystemException, ApplicationException {
		Connection conn = DBCtrl.getConnection();
		PreparedStatement pstmt = null;
		try {
			int type = Integer.parseInt(sType);

			pstmt = conn.prepareStatement(SQL_SET_STAFF_STATE[type]);
			pstmt.setString(1, state);
			pstmt.setString(2, loginName);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new ApplicationException("设置用户状态错误!", e);
		}
		catch (NumberFormatException e) {
			throw new ApplicationException("字符转化错误!", e);
		}
		finally {
			DBCtrl.close(conn, pstmt);
		}
	}
}