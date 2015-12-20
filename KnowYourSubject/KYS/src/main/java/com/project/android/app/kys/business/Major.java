package com.project.android.app.kys.business;

public class Major {
    private Integer mMajorId;
    private String mMajorName;
    private String mMajorInitials;
    private String mMajorSummary;
    private Integer mDepartmentId;


    public Integer getMajorId() {
        return mMajorId;
    }

    public void setMajorId(Integer majorId) {
        this.mMajorId = majorId;
    }

    public String getMajorName() {
        return mMajorName;
    }

    public void setMajorName(String majorName) {
        this.mMajorName = majorName;
    }

    public String getMajorInitials() {
        return mMajorInitials;
    }

    public void setMajorInitials(String majorInitials) {
        this.mMajorInitials = majorInitials;
    }

    public String getMajorSummary() {
        return mMajorSummary;
    }

    public void setMajorSummary(String majorSummary) {
        this.mMajorSummary = majorSummary;
    }

    public Integer getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.mDepartmentId = departmentId;
    }
}
