package com.ffcs.itm.web.check.entity;

import java.io.Serializable;

public class TpDomainListvalues implements Serializable{

    private String domainCode;
    private String TypeId;
    private String TypeValue;

    @Override
    public String toString() {
        return "TpDomainListvalues{" +
                "domainCode='" + domainCode + '\'' +
                ", TypeId='" + TypeId + '\'' +
                ", TypeValue='" + TypeValue + '\'' +
                '}';
    }

    public String getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public String getTypeValue() {
        return TypeValue;
    }

    public void setTypeValue(String typeValue) {
        TypeValue = typeValue;
    }
}
