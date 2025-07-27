package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private int orgId;
    @Size(min = 5, max = 100, message = "Organization name must be between 10 and 100 characters.")
    @Column(name = "org_name")
    private String orgName;
    @NotNull(message = "Organization address must not be null.")
    @Size(min = 5, max = 100, message = "Organization address must be between 10 and 100 characters.")
    @Column(name = "org_address")
    private String orgAddress;
    @NotNull(message = "Email must not be null.")
    @Email(message = "Please enter a valid email address.")
    @Column(name = "org_email")
    private String orgEmail;
    @NotNull(message = "Phone number must not be null.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits and numeric only.")
    @Column(name = "org_number")
    private String orgNumber;


    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Application> applications;

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }


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