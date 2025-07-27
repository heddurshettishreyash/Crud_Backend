package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Size(min = 5, max = 100, message = "User name must be between 10 and 100 characters.")
    @NotNull(message = "User address must not be null.")
    @Column(name = "user_name")
    private String userName;
    @Email(message = "Please enter a valid email address.")
    @Column(name = "user_email")
    private String userEmail;
    @Size(min = 4, max = 100, message = "User password must be between 4 and 100 characters.")
    @Column(name = "user_password")
    private String userPassword;
    @NotNull(message = "User role must not be null.")
    @Column(name = "user_role")
    @Size(min = 5, max = 100, message = "User Role must be between 10 and 100 characters.")
    private String userRole;
//    @Column(name = "app_id")
//    private int appId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "app_id")
    private Application application;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

//    public int getAppId() {
//        return appId;
//    }
//
//    public void setAppId(int appId) {
//        this.appId = appId;
//    }

}