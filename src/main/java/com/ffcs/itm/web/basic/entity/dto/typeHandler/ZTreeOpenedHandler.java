package com.ffcs.itm.web.basic.entity.dto.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZTreeOpenedHandler implements TypeHandler<Boolean> {

        @Override
        public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
                ps.setBoolean(i, parameter);                
        }

        @Override
        public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
                return rs.getString(columnName) == null ? true : false;
        }

        @Override
        public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
            return rs.getString(columnIndex) == null ? true : false;
        }

        @Override
        public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
            return cs.getString(columnIndex) == null ? true : false;
        }
        
}
