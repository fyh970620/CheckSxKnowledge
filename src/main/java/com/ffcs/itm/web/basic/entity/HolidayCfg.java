package com.ffcs.itm.web.basic.entity;

import java.util.Date;

public class HolidayCfg {
    private Integer holidayId;

    private Date holidayDay;

    private String note;

    private String state;

    private Integer fHolidayId;
    
    
    private String holidayBeginDay;

    private String holidayEndDay;
    
    private String holidayName;

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Date getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(Date holidayDay) {
        this.holidayDay = holidayDay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getfHolidayId() {
        return fHolidayId;
    }

    public void setfHolidayId(Integer fHolidayId) {
        this.fHolidayId = fHolidayId;
    }
    
    public String getHolidayBeginDay() {
        return holidayBeginDay;
    }

    public void setHolidayBeginDay(String holidayBeginDay) {
        this.holidayBeginDay = holidayBeginDay;
    }

    public String getHolidayEndDay() {
        return holidayEndDay;
    }

    public void setHolidayEndDay(String holidayEndDay) {
        this.holidayEndDay = holidayEndDay;
    }

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
    
    
}