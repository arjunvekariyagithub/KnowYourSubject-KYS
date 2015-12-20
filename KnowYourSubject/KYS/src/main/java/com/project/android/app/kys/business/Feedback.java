package com.project.android.app.kys.business;

import java.util.Date;


public class Feedback {
    private Integer mFeedbackId;
    private Integer mUserId;
    private Integer mCourseId;
    private Integer mProfessorId;
    private String mUserName;
    private String mCourseName;
    private String mProfessorName;
    private String mTitle;
    private String mComment;
    private Float mRating;
    private String mDate;
    private Integer mHelpfulnessCount;
    private Integer mUnhelpfulnessCount;
    private Boolean mIsSpam;
    private Boolean mIsAnonymous;

    public Integer getFeedBackId() {
        return mFeedbackId;
    }

    public void setFeedBackId(Integer feedBackId) {
        this.mFeedbackId = feedBackId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        this.mComment = comment;
    }

    public Float getRating() {
        return mRating;
    }

    public void setRating(Float rating) {
        this.mRating = rating;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public Integer getHelpfulnessCount() {
        return mHelpfulnessCount;
    }

    public void setHelpfulnessCount(Integer helpfulnessCount) {
        this.mHelpfulnessCount = helpfulnessCount;
    }

    public Integer getUnhelpfulnessCount() {
        return mUnhelpfulnessCount;
    }

    public void setUnhelpfulnessCount(Integer unhelpfulnessCount) {
        this.mUnhelpfulnessCount = unhelpfulnessCount;
    }

    public Boolean getIsSpam() {
        return mIsSpam;
    }

    public void setIsSpam(Boolean isSpam) {
        this.mIsSpam = isSpam;
    }

    public Boolean getIsAnonymous() {
        return mIsAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.mIsAnonymous = isAnonymous;
    }

    public Integer getUserId() {
        return mUserId;
    }

    public void setUserId(Integer userId) {
        this.mUserId = userId;
    }

    public Integer getCourseId() {
        return mCourseId;
    }

    public void setCourseId(Integer courseId) {
        this.mCourseId = courseId;
    }

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

    public String getCourseName() {
        return mCourseName;
    }

    public void setCourseName(String courseName) {
        this.mCourseName = courseName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }
}
