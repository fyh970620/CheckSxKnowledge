/**************************************************
 * Copyright (c) 2005.
 * 文件名称: ContextInfo.java
 * 摘　　要: 记录容器基本信息(如登陆页地址等等),在初始化的时候赋值,和容器中一些常用的方法
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-4
 **************************************************/
package com.ffcs.itm.web.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ContextInfo {
    
    public static final String BBS_LOGIN_NAME = "guest";

    public static final int USER_UNLOGIN = -1;

    public static final int USER_INVALID = 0;

    public static final int USER_SYS = 1;

    public static final int USER_BBS = 2;

    public static final int USER_UNSYS = 3;

    public static final int USER_ONLINE = 4;

    public static final int USER_LOCKED = 5;

    public static final int USER_CENSOR = 6;

    public static final String STAFF_INIT_PASSWD = "123456";

    public static final String DOMAIN_TRUE = "0BT";

    public static final String DOMAIN_FALSE = "0BF";

    public static Map PROPERTY_CONFIG_MAP = new HashMap();
    
}