package com.ffcs.itm.web.basic.entity.dto;

public class LogModuleInfo {
    public LogModuleInfo() {}

    public LogModuleInfo(int moduleCode, String logCode) {
        this.moduleCode = moduleCode;
        this.logCode = logCode;
    }

    private int moduleCode;

    private String logCode;

    public int getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(int moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getLogCode() {
        return logCode;
    }

    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }
}
