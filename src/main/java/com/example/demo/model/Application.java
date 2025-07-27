package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private int appId;
    @Size(min = 5, max = 100, message = "Application name must be between 10 and 100 characters.")
    @Column(name = "app_name")
    private String appName;
    @NotNull(message = "Application Description must not be null.")
    @Column(name = "app_desc")
    private String appDesc;
    @NotNull(message = "Application Type must not be null.")
    @Column(name = "app_type")
    private String appType;

//    @Column(name = "org_id")
//    private int orgId;


    //    public int getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(int orgId) {
//        this.orgId = orgId;
//    }
    //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    @JsonBackReference
    private Organization organization;
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

}

