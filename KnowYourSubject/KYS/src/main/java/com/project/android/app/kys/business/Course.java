package com.project.android.app.kys.business;

import java.util.List;

public class Course {
    private Integer mCourseId;
    private String mCourseCode;
    private String mCourseName;
    private String mCourseInitials;
    private String mCourseSummary;
    private Integer mFeedbackCount;
    private Float mCourseRating;
    private Integer mMajorId;
    private List<Integer> mProfessorIds;

    public Integer getCourseId() {
        return mCourseId;
    }

    public void setCourseId(Integer courseId) {
        this.mCourseId = courseId;
    }

    public String getCourseCode() {
        return mCourseCode;
    }

    public void setCourseCode(String courseCode) {
        this.mCourseCode = courseCode;
    }

    public String getCourseName() {
        return mCourseName;
    }

    public void setCourseName(String courseName) {
        this.mCourseName = courseName;
    }

    public String getCourseInitials() {
        return mCourseInitials;
    }

    public void setCourseInitials(String courseInitials) {
        this.mCourseInitials = courseInitials;
    }

    public String getCourseSummary() {
        return mCourseSummary;
    }

    public void setCourseSummary(String courseSummary) {
        this.mCourseSummary = courseSummary;
    }

    public Integer getFeedbackCount() {
        return mFeedbackCount;
    }

    public void setFeedbackCount(Integer noOfFeedback) {
        this.mFeedbackCount = noOfFeedback;
    }


    public Float getCourseRating() {
        return mCourseRating;
    }

    public void setCourseRating(Float courseRating) {
        this.mCourseRating = courseRating;
    }

    public Integer getMajorId() {
        return mMajorId;
    }

    public void setMajorId(Integer majorId) {
        this.mMajorId = majorId;
    }

    public List<Integer> getProfessorIds() {
        return mProfessorIds;
    }

    public void setProfessorIds(List<Integer> professorIds) {
        this.mProfessorIds = professorIds;
    }

}
