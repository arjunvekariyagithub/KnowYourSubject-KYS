package com.project.android.app.kys.business;

public class Discipline {

    private Integer mDisciplineId;
    private String mDisciplineName;
    private String mDisciplineInitials;
    private String mDisciplineSummary;
    private Integer mUniversityId;


    public Integer getDisciplineId() {
        return mDisciplineId;
    }

    public void setDisciplineId(Integer mydisciplineId) {
        this.mDisciplineId = mydisciplineId;
    }

    public Integer getUniversityId() {
        return mUniversityId;
    }

    public void setUniversityId(Integer univId) {
        this.mUniversityId = univId;
    }

    public String getDisciplineName() {
        return mDisciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.mDisciplineName = disciplineName;
    }

    public String getDisciplineInitials() {
        return mDisciplineInitials;
    }

    public void setDisciplineInitials(String disciplineInitials) {
        this.mDisciplineInitials = disciplineInitials;
    }

    public String getDisciplineSummary() {
        return mDisciplineSummary;
    }

    public void setDisciplineSummary(String disciplineSummary) {
        this.mDisciplineSummary = disciplineSummary;
    }


}
