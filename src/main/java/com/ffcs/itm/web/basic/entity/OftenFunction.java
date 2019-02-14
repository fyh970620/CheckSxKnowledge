package com.ffcs.itm.web.basic.entity;

public class OftenFunction {
    
    private Long functionId;
    
    private String shortDescription;
    
    private String icon;
    
    private String serverUrlName;

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getServerUrlName() {
        return serverUrlName;
    }

    public void setServerUrlName(String serverUrlName) {
        this.serverUrlName = serverUrlName;
    }
    
}
