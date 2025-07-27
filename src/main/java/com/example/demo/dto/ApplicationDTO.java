package com.example.demo.dto;

public class ApplicationDTO {

    private int appId;
    private String appName;
    private String appDesc;
    private String appType;
    private int orgId;

    public ApplicationDTO() {
    }

    public ApplicationDTO(int appId, String appName, String appDesc, String appType, int orgId) {
        this.appId = appId;
        this.appName = appName;
        this.appDesc = appDesc;
        this.appType = appType;
        this.orgId = orgId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

}
