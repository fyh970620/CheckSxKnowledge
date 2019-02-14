package com.ffcs.itm.web.basic.entity;

import com.ffcs.itm.web.respository.MyBatisRepository;

import java.util.Date;

@MyBatisRepository
public class KpiCodeList {
    private Long kpiId;

    private String neMsgType;

    private Long neTypeId;

    private Long neConfigTypeId;

    private String kpiName;

    private String kpiDescription;

    private Integer attrValueTypeId;

    private Integer execScriptId;

    private String perfLevel;

    private String alarmLevel;

    private String configLevel;

    private String state;

    private Date stateDate;

    private String alarmClass;

    private String alarmType;

    private String ctrlClass;

    private String expertAdvice;

    private Integer valueUnitId;

    private Long alarmKpiId;

    private String alarmClassify;

    public Long getKpiId() {
        return kpiId;
    }

    public void setKpiId(Long kpiId) {
        this.kpiId = kpiId;
    }

    public String getNeMsgType() {
        return neMsgType;
    }

    public void setNeMsgType(String neMsgType) {
        this.neMsgType = neMsgType;
    }

    public Long getNeTypeId() {
        return neTypeId;
    }

    public void setNeTypeId(Long neTypeId) {
        this.neTypeId = neTypeId;
    }

    public Long getNeConfigTypeId() {
        return neConfigTypeId;
    }

    public void setNeConfigTypeId(Long neConfigTypeId) {
        this.neConfigTypeId = neConfigTypeId;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getKpiDescription() {
        return kpiDescription;
    }

    public void setKpiDescription(String kpiDescription) {
        this.kpiDescription = kpiDescription;
    }

    public Integer getAttrValueTypeId() {
        return attrValueTypeId;
    }

    public void setAttrValueTypeId(Integer attrValueTypeId) {
        this.attrValueTypeId = attrValueTypeId;
    }

    public Integer getExecScriptId() {
        return execScriptId;
    }

    public void setExecScriptId(Integer execScriptId) {
        this.execScriptId = execScriptId;
    }

    public String getPerfLevel() {
        return perfLevel;
    }

    public void setPerfLevel(String perfLevel) {
        this.perfLevel = perfLevel;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getConfigLevel() {
        return configLevel;
    }

    public void setConfigLevel(String configLevel) {
        this.configLevel = configLevel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public String getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(String alarmClass) {
        this.alarmClass = alarmClass;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getCtrlClass() {
        return ctrlClass;
    }

    public void setCtrlClass(String ctrlClass) {
        this.ctrlClass = ctrlClass;
    }

    public String getExpertAdvice() {
        return expertAdvice;
    }

    public void setExpertAdvice(String expertAdvice) {
        this.expertAdvice = expertAdvice;
    }

    public Integer getValueUnitId() {
        return valueUnitId;
    }

    public void setValueUnitId(Integer valueUnitId) {
        this.valueUnitId = valueUnitId;
    }

    public Long getAlarmKpiId() {
        return alarmKpiId;
    }

    public void setAlarmKpiId(Long alarmKpiId) {
        this.alarmKpiId = alarmKpiId;
    }

    public String getAlarmClassify() {
        return alarmClassify;
    }

    public void setAlarmClassify(String alarmClassify) {
        this.alarmClassify = alarmClassify;
    }
}