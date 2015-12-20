package com.project.android.app.kys.business;

public class Department {

    private Integer mDepartmentId;
    private String mDepartmentName;
    private String mDepartmentInitials;
    private String mDepartmentSummary;
    private Integer mDisciplineId;

    public Integer getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.mDepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return mDepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.mDepartmentName = departmentName;
    }

    public String getDepartmentInitials() {
        return mDepartmentInitials;
    }

    public void setDepartmentInitials(String departmentInitials) {
        this.mDepartmentInitials = departmentInitials;
    }

    public String getDepartmentSummary() {
        return mDepartmentSummary;
    }

    public void setDepartmentSummary(String departmentSummary) {
        this.mDepartmentSummary = departmentSummary;
    }

    public Integer getDisciplineId() {
        return mDisciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.mDisciplineId = disciplineId;
    }


}
