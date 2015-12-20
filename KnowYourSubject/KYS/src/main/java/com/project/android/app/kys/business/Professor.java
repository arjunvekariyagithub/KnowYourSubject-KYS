package com.project.android.app.kys.business;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Professor {

    private Integer mProfessorId;
    private String mProfessorName;
    private String mContact;
    private String mEmail;
    private String mUrl;
    //private ArrayList<Course> mCourseList = new ArrayList<Course>();

    public Integer getProfessorId() {
        return mProfessorId;
    }

    public void setProfessorId(Integer professorId) {
        this.mProfessorId = professorId;
    }

    public String getProfessorName() {
        return mProfessorName;
    }

    public void setProfessorName(String professorName) {
        this.mProfessorName = professorName;
    }

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        this.mContact = contact;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

//    public List<Course> getCourseList() {
//        return mCourseList;
//    }

//    //public void setCourseList(ArrayList<Course> courseList) {
//        this.mCourseList = courseList;
//    }

    public String getWebUrl() {
        return mUrl;
    }

    public void setWebUrl(String profUrl) {
        mUrl = profUrl;
    }


}
