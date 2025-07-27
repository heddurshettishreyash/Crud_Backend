package com.example.demo.dto;

public class OrganizationDTO {

    private int orgId;
    private String orgName;
    private String orgAddress;
    private String orgEmail;
    private String orgNumber;

    public OrganizationDTO() {
    }

    public OrganizationDTO(int orgId, String orgName, String orgAddress, String orgEmail, String orgNumber) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.orgEmail = orgEmail;
        this.orgNumber = orgNumber;
    }

    // Getters and Setters
    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }
}
