package com.ffcs.itm.web.outtersso.support;

import EIAC.EAC.SSO.AppSSOBLL;
import EIAC.EAC.SSO.ReadConfig;

/**
 * Created by xc on 2017/11/22.
 */
public class GdOaEntrance {
    private static ReadConfig rd = new ReadConfig();
    private static AppSSOBLL app = new AppSSOBLL();

    public static String getStr(String str) {
        String result = rd.getString(str).toString();
        return result;
    }

    public static String getPostStr(String IASID, String TimeStamp, String defaulturl) {
        String result = app.PostString(IASID, TimeStamp, defaulturl, null);
        return result;
    }

    public static boolean ValidateFromEAC(String IASID, String TimeStamp, String UserAccount, String Result,
            String ErrorDescription, String Authenticator) throws Exception {
        return app.ValidateFromEAC(IASID, TimeStamp, UserAccount, Result, ErrorDescription, Authenticator);
    }
}
