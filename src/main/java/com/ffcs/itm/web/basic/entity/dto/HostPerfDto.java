package com.ffcs.itm.web.basic.entity.dto;

public class HostPerfDto {
    
    private String deviceName;
    
    private String ip;
    
    private Double perfValue;
    
    private String perfName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getPerfValue() {
        return perfValue;
    }

    public void setPerfValue(Double perfValue) {
        this.perfValue = perfValue;
    }

    public String getPerfName() {
        return perfName;
    }

    public void setPerfName(String perfName) {
        this.perfName = perfName;
    }

}
