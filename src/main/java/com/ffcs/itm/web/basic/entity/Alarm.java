package com.ffcs.itm.web.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Alarm {
    
    private Long neId;
    
    private Long neAlarmListId;
    
    private String neTypeName;

    private String neName;
    
    private String alarmLevel;
    
    private String  alarmTitle;
    
    private String  regionName;
    
    private String  operatorName;
    
    private String  domainName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date generateTime;
    
    public Long getNeId() {
        return neId;
    }

    public void setNeId(Long neId) {
        this.neId = neId;
    }

    public Long getNeAlarmListId() {
        return neAlarmListId;
    }

    public void setNeAlarmListId(Long neAlarmListId) {
        this.neAlarmListId = neAlarmListId;
    }

    public String getNeTypeName() {
        return neTypeName;
    }

    public void setNeTypeName(String neTypeName) {
        this.neTypeName = neTypeName;
    }

    public String getNeName() {
        return neName;
    }

    public void setNeName(String neName) {
        this.neName = neName;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
    
    
}
