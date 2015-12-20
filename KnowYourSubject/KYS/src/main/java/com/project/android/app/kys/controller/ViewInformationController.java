package com.project.android.app.kys.controller;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.db.DBMgr;
import com.project.android.app.kys.helper.Constants.IDs;
import com.project.android.app.kys.json.JSONRequester;

import java.util.List;

public class ViewInformationController {
    private static Context mContext = null;

    public ViewInformationController(Context context) {
        mContext = context;

    }

    public static ViewInformationController createInstance(Context context) {
        return new ViewInformationController(context);
    }

    public List<Discipline> getDisciplines() {
        DBMgr.getInstance().getDispsList();
        return null;
    }

    public List<Department> getDepartments(Integer dispID) {
        DBMgr.getInstance().getDeptsList4(dispID);
        return null;
    }

    public List<Major> getMajors(Integer deptmentID) {
        DBMgr.getInstance().getMajorsList4(deptmentID);
        return null;
    }

    public List<Course> getCourses(Integer majorID) {
        DBMgr.getInstance().getCoursesList4(majorID);
        return null;
    }

    public List<Feedback> getFeebdbacksForCourse(Integer courseID) {
        DBMgr.getInstance().getFeedbackList4(IDs.COURSE_ID, courseID, -1);
        return null;
    }

    public Feedback getCourseFeedbackForUser(Integer userID, Integer courseID) {
        DBMgr.getInstance().getCourseFeedbackForUser(userID, courseID);
        return null;
    }

    public List<Professor> getProfessorForCourse(Integer courseID) {
        DBMgr.getInstance().getProfessorsList4(courseID);
        return null;
    }

    /***************************************** POOJA ****************************************/
    public List<Major> getMajorForHome(Integer deptmentID) {
        DBMgr.getInstance().getMajorsList4Home(deptmentID);
        return null;
    }

    public List<Feedback> getFeebdbacksForHome(Integer userID) {
        DBMgr.getInstance().getFeedbackList4Home(userID);
        return null;
    }

    public void getAllProfessors() {
        DBMgr.getInstance().getAllProfessors();
    }

    public void getFeedbackForProfessor(int profId, Integer courseID) {
        DBMgr.getInstance().getFeedbackList4(IDs.PROF_ID, courseID, profId);
    }
}
