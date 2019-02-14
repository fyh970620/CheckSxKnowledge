
package com.bsnnms.bean.common.propertyCfg;

import com.bsnnms.bean.common.ContextInfo;
import com.bsnnms.bean.common.DBCtrl;
import com.bsnnms.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PropertyConfig {
    
	private static final String SQL_GET_PROPERTY_CONFIG = "SELECT CODE,VALUE FROM PROPERTY_CONFIG";

    private static final String SQL_GET_PROPERTY_CONFIG_BY_CODE = "SELECT CODE,VALUE FROM PROPERTY_CONFIG where code = ?";

	
	//获得property_config表中的对应CODE字段的属性配置信息
	public static String getValue(String propertyCfgCode){
		return (String)ContextInfo.PROPERTY_CONFIG_MAP.get(propertyCfgCode);
	}

    //获得property_config表中的对应CODE字段的属性配置信息
    public static String getValueByCode(String propertyCfgCode){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String value = null;
        try {
            conn = DBCtrl.getConnection();
            pstmt = conn.prepareStatement(SQL_GET_PROPERTY_CONFIG_BY_CODE);
            pstmt.setString(1,propertyCfgCode);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                value = rs.getString("VALUE");
            }
        }catch (SQLException e1) {
            e1.printStackTrace();
        }catch (SystemException e1) {
            e1.printStackTrace();
        } finally{
            try{
                DBCtrl.close(conn, pstmt);
            }catch (SystemException e){
                e.printStackTrace();
            }
        }
        return value;
    }
	
	public static void setValue(String propertyCfgCode,String value) {
		ContextInfo.PROPERTY_CONFIG_MAP.remove(propertyCfgCode);
		ContextInfo.PROPERTY_CONFIG_MAP.put(propertyCfgCode, value);
	}
	
	
	public static void setPropertyCfgMap(){
		Connection conn = null;
		PreparedStatement pstmt = null;
	    try {
	    	conn = DBCtrl.getConnection();
			pstmt = conn.prepareStatement(SQL_GET_PROPERTY_CONFIG);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
	        {
	            ContextInfo.PROPERTY_CONFIG_MAP.put(rs.getString("CODE"), rs.getString("VALUE"));
	        }
		}catch (SQLException e1) {
			e1.printStackTrace();
		}catch (SystemException e1) {
			e1.printStackTrace();
		} finally{
            try{
                DBCtrl.close(conn, pstmt);
            }catch (SystemException e){
                e.printStackTrace();
            }
        }
	}
    
}
