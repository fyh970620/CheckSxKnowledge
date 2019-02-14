/**************************************************
 * Copyright (c) 2005.
 * 文件名称: ContextInfo.java
 * 摘　　要: 记录容器基本信息(如登陆页地址等等),在初始化的时候赋值,和容器中一些常用的方法
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-4
 **************************************************/
package com.ffcs.itm.common.utils;

import com.bsnnms.bean.common.resource.ResourceInfo;
import com.bsnnms.bean.user.LogModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 容器基本信息
 * 
 * @author 方旭尘
 * @version 1.0
 */
public class ContextInfo
{	
	public static PageInfo getPageInfo(String key){
		if(key!=null && MAIN_PAGE_MAP.containsKey(key))
			return (PageInfo)MAIN_PAGE_MAP.get(key);
		else
			return (PageInfo)MAIN_PAGE_MAP.get("default");
	}
	
	public static Map 		   MAIN_PAGE_MAP 			   = new HashMap(); 
	
    /**
     * 登陆页地址
     */
    public static String       LOGIN_URL           = "/LoginForm.jsp";
    /**
     * 皮肤名称，用于各个不同的登陆页间切换时select显示的名称
     */
    public static String       SKIN_NAME;
    
    /**
     * cookies中用户名的Name
     */
    public static String       LOGIN_NAME          = "BOSSWG_LOGINNAME";

    /**
     * cookies中密码的Name
     */
    public static String       PSWD_NAME           = "BOSSWG_PASSWORD";

    /**
     * cookies中登录方式的Name
     */
    public static String       LOGIN_TYPE          = "BOSSWG_LOGINTYPE";

    /**
     * cookies的生存周期(默认一年)
     */
    public static int          COOKIES_AGE         = 365 * 24 * 60 * 60;

    public static String       COPYRIGHT           = "Copyright  &copy; 中国电信";

    public static String       CompanyName         = "中国电信";

    public static String       UPLOAD_PATH         = "upload";
    
    public static String       DEFAULT_LOCAL_REGION= "";

    public static final String DOWN_EXCEL          = "/workshop/logicaudit/analyzeIframeList_excel.jsp";

    public static final String DOWN_PATH           = "/servlet/downloadservlet";

    public static final String BBS_URL             = "/BBSIndex";

    public static final String NO_PRIVILEGE_URL    = "/No_Privilege.jsp";

    public static final String XML_CONTENT_TYPE    = "text/xml; charset=GBK";

    public static final String HTML_CONTENT_TYPE   = "text/html; charset=GBK";
    
    public static final String JSON_CONTENT_TYPE   = "application/json; charset=GBK";

    public static final String BBS_LOGIN_NAME      = "guest";

    public static final int    USER_UNLOGIN        = -1;

    public static final int    USER_INVALID        = 0;

    public static final int    USER_SYS            = 1;

    public static final int    USER_BBS            = 2;

    public static final int    USER_UNSYS          = 3;

    public static final int    USER_ONLINE         = 4;

    public static final int    USER_LOCKED         = 5;

    public static final int    USER_CENSOR         = 6;

    public static final String STAFF_INIT_PASSWD   = "123456";

    public static final String DOMAIN_TRUE         = "0BT";

    public static final String DOMAIN_FALSE        = "0BF";

    public static String       MANI_TITLE_TEXT;

    public static String       MAIN_TITLE_IMG;

    public static String       BILL_INFO_TITLE;

    public static String       FLOW_INFO_TITLE;

    public static String       IS_SHOW_FLASH;

    public static String       MAIN_FOOTER_CO;

    public static String       MAIN_FOOTER_URL;

    public static String       MAIN_FOOTER_TEXT;

    public static String       LOGIN_BACKGROUND;
    
    // add by lsa,at 2011-06-29
    public static String       LOGIN_UPPER_LEFT_LOGO = "resource/image/indexImage/style1/logo.jpg";		// 登录页 左上角 LOGO

    public static String       IS_LOGIN_EMAIL;

    public static String       IS_SEARCH_ENTRANCE;

    // add by qih,at 2007-07-22
    public static String       UNDO_WIN_HEIGHT;

    public static String       UNDO_WIN_WIDTH;
    // add by qih,at 2007-12-20
    public static String       IS_SHOW_BBS;
    // add by qih,at 2007-12-25
    public static String       IS_SHOW_DEFINE_SELF;
    public static String       DEFINE_SELF_TITLE;
    public static String       DEFINE_SELF_ROW;
    public static String       DEFINE_SELF_FLASHTIME;
    public static String       DEFINE_SELF_TD_WIDTH;
    // add by qih,at 2008-1-17
    public static String       IS_SHOW_ATTEND;
    
    public static String 	     IS_SHOW_DUTY;

    // add
    // by
    // wuzhb,
    // at
    // 2006-10-11.

    public static String       MAIN_URL;

    public static String       SECOND_PAGE_TOP_URL;

    public static String       SECOND_PAGE_MAIN_FRAME_COLS;

    public static String       SECOND_PAGE_BOTTOM_FRAME_COLS;
    
    public static String       SECOND_PAGE_TITLE_IMG;//系统二级页标题图片
    
    public static String       SECOND_PAGE_TITLE_IMG_WIDTH="250";//系统二级页标题图片宽度

    public static List         billInfoBeforeItems = new ArrayList();

    public static List         billInfoAfterItems  = new ArrayList();

    public static List         attentionList;

    public static List         ALARM_MERGE_LIST  = new ArrayList();//告警列表
    public static String 	   DISPLAY_ALARM_COUNT = "20";//默认显示20条记录,在MainPage.xml的limitCount属性可配置
    public static String 	   DISPLAY_ALARM_REFRESH_TIME = "10000";//默认10000毫秒(10秒)刷新,在MainPage.xml的limitCount属性可配置

    public static String       ALARM_INFO_TITLE;//告警列表图标
    
    //add by chenjw 2009/03/04
    public static String	   LOGIN_BBS_LINK;	//登陆页是否显示登陆到论坛链接,0表示显示,1表示不显示
    public static String 	   LOGIN_SAVE_PASSWORD_INPUT;	//登陆页是否显示记住密码链接,0表示显示,1表示不显示
    public static String 	   LOGIN_LOWER_LEFT_LOGO;	//登陆页左下方LOGO图片地址
    public static String	   INDEX_LOWER_LEFT_LOGO;	//首页左下方LOGO图片地址
    public static String 	   INDEX_TOP_FLASH;		//首页顶部Banner Flash
    public static String	   WELCOME_RIGHT_TEXT;	//欢迎页右下角文字

    //add by qih 2010/02/08
    public static Map         flowJmsListenerList = new HashMap();	//两级流程jms监听器列表
    
    //add by oushy 2010/03/24
    public static List         sidJmsListenerList = new ArrayList();    //SID共享信息平台JMS监听器列表
    
    //add by linrl,at 2010-08-23
    public static String       UNDO_WIN_POSITION;  //浮动框位置
    public static String       WAIT_HANDLE_HEIGHT; //待办事宜高度
    public static String       PIGEON_SPEAK_SHOW; //飞鸽传说是否显示
    public static String       IS_SHOW_BILING_IT;//是否显示登陆页面底部的BILING_IT的图标0不显示
    //add by chenxx, at 2011-05-31
    public static final String REQUEST_WEB_CONTEXT = "request.web.context";
    public static final String MESSAGE_CHARSET = "GBK"; //AMQP 消息编码
    
    //add by linzhzh, at 2011-11-17
    public static String  ITSM_TOP_IMG;//MainPage.xml中标签页Itsm_Img
	
	public static ResourceInfo		RESOURCE_INFO;
    
    //add by tangft 2012/02/16
    public static String       NORTH_OA_LOGIN_CHECK_JSP = "/northOaLoginCheck.jsp";
    public static String 	   NORTH_OA_LOGIN_CHECK = "/servlet/NorthOaLoginCheck";  //北方九省OA单点登陆用户验证
    public static String       SOUTH_OA_LOGIN_CHECK_JSP = "/southOaLoginCheck.jsp";
    public static String 	   SOUTH_OA_LOGIN_CHECK = "/servlet/SouthOaLoginCheck";  //南方九省OA单点登陆用户验证
    public static String       JS_SINGLE_LOGIN_CHECK_JSP = "/JS_SingleLogin.jsp";
    public static String 	   JS_SINGLE_LOGIN_CHECK = "/servlet/JsSingleLogin";  //江苏ITSM单点登陆用户验证
    public static String 	   MSS_INDEX = "/indexMSS.html";  //集团ITSM首页
    public static String       FLOW_REPORT = "/workshop/queryTemplate/requirement_group_report.html";//需求跟踪报表
    public static String       KNOWLEDGE_SEARCH = "/workshop/searchEngine/search_entrance.htm";//知识库搜索
    public static String       FLOW_VIEW = "/workshop/queryTemplate/mainFrame.html?result=90000100&tab=1";//流程代办

    public static Map PROPERTY_CONFIG_MAP = new HashMap();
    
    public static String JDBC_GATHER_DEST_DS_NAME; //JDBC采集写入目标数据源名称
    
    public static Map<String,String> CUSTOM_MAIN_PAGE = new HashMap();//个性首页
    
    public static Map<String,LogModule> LOG_MODULE_PAGE = new HashMap();//需要记录访问日志的模块页面
    
    public static String       BOSSWG_LOGIN_MODEL = "BOSSWG";
    public static String       ACC_LOGIN_MODEL = "ACC";
    public static String       ACC_LOGIN_URL = "/LoginFormACC.jsp";
    public static String       ESCAPE_LOGIN_MODEL = "ESC";
    public static String       ESCAPE_LOGIN_URL = "/LoginFormEscape.jsp";
    public static String       SEARCH_ENGINE_CONFIG_FILE="/resource/xml/search_engine_config.xml";
    
    
    public static String WEB_INF_PATH;
}
