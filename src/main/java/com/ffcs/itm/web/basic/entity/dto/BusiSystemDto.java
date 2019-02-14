package com.ffcs.itm.web.basic.entity.dto;

public class BusiSystemDto {

    private Long instanceId;

    private String systemName;

    private Integer hostCount;

    private Integer databaseCount;

    private Integer middlewareCount;

    private Integer processCount;

    private Integer modularCount;

    private Integer alarmLevel;

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getHostCount() {
        return hostCount;
    }

    public void setHostCount(Integer hostCount) {
        this.hostCount = hostCount;
    }

    public Integer getDatabaseCount() {
        return databaseCount;
    }

    public void setDatabaseCount(Integer databaseCount) {
        this.databaseCount = databaseCount;
    }

    public Integer getMiddlewareCount() {
        return middlewareCount;
    }

    public void setMiddlewareCount(Integer middlewareCount) {
        this.middlewareCount = middlewareCount;
    }

    public Integer getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Integer processCount) {
        this.processCount = processCount;
    }

    public Integer getModularCount() {
        return modularCount;
    }

    public void setModularCount(Integer modularCount) {
        this.modularCount = modularCount;
    }

    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

}
