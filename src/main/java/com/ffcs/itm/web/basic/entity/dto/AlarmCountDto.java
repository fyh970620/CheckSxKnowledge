package com.ffcs.itm.web.basic.entity.dto;

public class AlarmCountDto {
    
    private String alarmLevel;
    
    private Long count;

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
}
