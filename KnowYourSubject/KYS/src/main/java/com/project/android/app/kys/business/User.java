package com.project.android.app.kys.business;

import java.util.Date;

public class User {
    private Integer mUserId;
    private String mPassword;
    private String mEmailId;
    private String mFirstName;
    private String mLastName;
    private Integer mDepartmentId;
    private String mSecurityQuestion;
    private String mSecurityAnswer;
    private Boolean mIsAdmin = false;
    private String mDob;
    private String mContact;
    private String mZipCode;

    public Integer getUserID() {
        return mUserId;
    }

    public void setUserID(Integer userID) {
        this.mUserId = userID;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getEmailId() {
        return mEmailId;
    }

    public void setEmailId(String emailId) {
        this.mEmailId = emailId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public Integer getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.mDepartmentId = departmentId;
    }

    public String getSecurityQuestion() {
        return mSecurityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.mSecurityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return mSecurityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.mSecurityAnswer = securityAnswer;
    }

    public Boolean getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.mIsAdmin = isAdmin;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        this.mDob = dob;
    }

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        this.mContact = contact;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        this.mZipCode = zipCode;
    }

}
