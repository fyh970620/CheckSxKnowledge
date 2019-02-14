package com.bsnnms.bean.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 登陆页、首页的基本信息
 * 
 */
public class PageInfo extends ContextInfo
{
    public String       LOGIN_URL           = "/LoginForm.jsp";//登陆页地址

    public String       SKIN_NAME;//皮肤名称，用于各个不同的登陆页间切换时select显示的名称
    
    public String       MANI_TITLE_TEXT;//系统主页标题内容

	public String       MAIN_TITLE_IMG;//系统主页标题图片

    public String       BILL_INFO_TITLE;//计费动态信息标题

    public String       FLOW_INFO_TITLE;//流程代办事宜标题

    public String       IS_SHOW_FLASH;//是否显示Flash动画

    public String       MAIN_FOOTER_CO;//系统主页下标公司名称

    public String       MAIN_FOOTER_URL;//系统主页下标公司网址

    public String getMAIN_FOOTER_CO() {
		return MAIN_FOOTER_CO;
	}

	public void setMAIN_FOOTER_CO(String mAIN_FOOTER_CO) {
		MAIN_FOOTER_CO = mAIN_FOOTER_CO;
	}

	public String getMAIN_FOOTER_URL() {
		return MAIN_FOOTER_URL;
	}

	public void setMAIN_FOOTER_URL(String mAIN_FOOTER_URL) {
		MAIN_FOOTER_URL = mAIN_FOOTER_URL;
	}

	public String getMAIN_FOOTER_TEXT() {
		return MAIN_FOOTER_TEXT;
	}

	public void setMAIN_FOOTER_TEXT(String mAIN_FOOTER_TEXT) {
		MAIN_FOOTER_TEXT = mAIN_FOOTER_TEXT;
	}

	public String       MAIN_FOOTER_TEXT;//系统主页下标公司地址

    public String       LOGIN_BACKGROUND;//登陆页背景图片
    
    // add by lsa,at 2011-06-29
    public String		LOGIN_UPPER_LEFT_LOGO = "resource/image/indexImage/style1/logo.jpg";		// 登录页 左上角 LOGO

    public String       IS_LOGIN_EMAIL;//是否使用外部邮箱

    public String getIS_LOGIN_EMAIL() {
		return IS_LOGIN_EMAIL;
	}

	public void setIS_LOGIN_EMAIL(String iS_LOGIN_EMAIL) {
		IS_LOGIN_EMAIL = iS_LOGIN_EMAIL;
	}

	public String       IS_SEARCH_ENTRANCE;//是否使用搜索引擎

    // add by qih,at 2007-07-22
    public String       UNDO_WIN_HEIGHT;//提醒浮动层的高度

    public String getUNDO_WIN_HEIGHT() {
		return UNDO_WIN_HEIGHT;
	}

	public void setUNDO_WIN_HEIGHT(String uNDO_WIN_HEIGHT) {
		UNDO_WIN_HEIGHT = uNDO_WIN_HEIGHT;
	}

	public String getUNDO_WIN_WIDTH() {
		return UNDO_WIN_WIDTH;
	}

	public void setUNDO_WIN_WIDTH(String uNDO_WIN_WIDTH) {
		UNDO_WIN_WIDTH = uNDO_WIN_WIDTH;
	}

	public String getUNDO_WIN_POSITION() {
		return UNDO_WIN_POSITION;
	}

	public void setUNDO_WIN_POSITION(String uNDO_WIN_POSITION) {
		UNDO_WIN_POSITION = uNDO_WIN_POSITION;
	}

	public String       UNDO_WIN_WIDTH;//提醒浮动层的宽度
    // add by qih,at 2007-12-20
    public String       IS_SHOW_BBS;//是否显示BBS
    
    /**
     *	显示滚动自定义SQL信息
	 *	IS_SHOW_DEFINE_SELF为是否显示该模块
	 *	DEFINE_SELF_TITLE为模块标题
	 *	DEFINE_SELF_ROW字段为翻页一次显示几行
	 *	DEFINE_SELF_REFLASH为翻页刷新一次时间（单位毫秒）
	 *	DEFINE_SELF_TD_WIDTH为每列的宽度百分比（用“|”隔开,数字后面不要加“%”）
     */
    public String       IS_SHOW_DEFINE_SELF;
    public String       DEFINE_SELF_TITLE;
    public String       DEFINE_SELF_ROW;
    public String       DEFINE_SELF_FLASHTIME;
    public String       DEFINE_SELF_TD_WIDTH;
    // add by qih,at 2008-1-17
    public String       IS_SHOW_ATTEND;//是否使用加班模块
    
    public String 	     IS_SHOW_DUTY;//是否显示值班（开始、结束、交接班）

    // add
    // by
    // wuzhb,
    // at
    // 2006-10-11.

    public String       MAIN_URL;//系统主页地址

    public String       SECOND_PAGE_TOP_URL;//系统二级主页上帧地址

    public String       SECOND_PAGE_MAIN_FRAME_COLS;//系统二级主页上帧和下帧的高度

    public String       SECOND_PAGE_BOTTOM_FRAME_COLS;//系统二级主页下帧各页的宽度
    
    public String       SECOND_PAGE_TITLE_IMG;//系统二级页标题图片
    
    public String       SECOND_PAGE_TITLE_IMG_WIDTH="250";//系统二级页标题图片宽度
    
    public List         billInfoBeforeItems = new ArrayList();//计费动态模块的配置(标准)

    public List         billInfoAfterItems  = new ArrayList();//计费动态模块的配置(标准)

    public List         attentionList;//BBS链接只适用于集团版本

    public List         ALARM_MERGE_LIST  = new ArrayList();//告警列表
    public String 	   DISPLAY_ALARM_COUNT = "20";//默认显示20条记录,在MainPage.xml的limitCount属性可配置
    public String 	   DISPLAY_ALARM_REFRESH_TIME = "10000";//默认10000毫秒(10秒)刷新,在MainPage.xml的limitCount属性可配置

    public String       ALARM_INFO_TITLE;//告警列表图标
    
    //add by chenjw 2009/03/04
    public String	   LOGIN_BBS_LINK;	//登陆页是否显示登陆到论坛链接,0表示显示,1表示不显示
    public String 	   LOGIN_SAVE_PASSWORD_INPUT;	//登陆页是否显示记住密码链接,0表示显示,1表示不显示
    public String 	   LOGIN_LOWER_LEFT_LOGO;	//登陆页左下方LOGO图片地址
    public String	   INDEX_LOWER_LEFT_LOGO;	//首页左下方LOGO图片地址
    public String 	   INDEX_TOP_FLASH;		//首页顶部Banner Flash
    public String	   WELCOME_RIGHT_TEXT;	//欢迎页右下角文字

    
    
    //add by linrl,at 2010-08-23
    public String       UNDO_WIN_POSITION;  //浮动框位置
    public String       WAIT_HANDLE_HEIGHT; //待办事宜高度
    public String       PIGEON_SPEAK_SHOW; //飞鸽传说是否显示
    public String       IS_SHOW_BILING_IT;//是否显示登陆页面底部的BILING_IT的图标0不显示
    
    //add by linzhzh,at 2011-11-17
    public String		ITSM_TOP_IMG;//MainPage.xml中标签页Itsm_Img
        
}
