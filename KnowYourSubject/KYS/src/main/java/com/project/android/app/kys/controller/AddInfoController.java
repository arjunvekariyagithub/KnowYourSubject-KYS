package com.project.android.app.kys.controller;

import android.content.Context;

import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.db.DBMgr;

public class AddInfoController {

    private static Context mContext = null;

    public AddInfoController(Context context) {
        mContext = context;

    }

    public static AddInfoController createInstance(Context context) {
        return new AddInfoController(context);
    }

    public boolean addProfessor(String name, String email, String contact, String url) {
        Professor professor = new Professor();
        professor.setProfessorName(name);
        professor.setEmail(email);
        professor.setContact(contact);
        professor.setWebUrl(url);
        DBMgr.getInstance().saveProfessor(professor);
        return true;
    }

    public boolean addCourse(Integer majorID, String name, String code, String initials, String summary) {
        Course course = new Course();
        course.setCourseName(name);
        course.setCourseCode(code);
        course.setCourseInitials(initials);
        course.setCourseSummary(summary);
        course.setMajorId(majorID);
        DBMgr.getInstance().saveCourse(course);
        return true;
    }

    public boolean addMajor(Integer deptID, String name, String initials, String summary) {
        Major major = new Major();
        major.setMajorName(name);
        major.setMajorInitials(initials);
        major.setMajorSummary(summary);
        major.setDepartmentId(deptID);
        DBMgr.getInstance().saveMajor(major);
        return true;
    }

    public boolean addDepartment(Integer dispID, String name, String initials, String summary) {
        Department dept = new Department();
        dept.setDepartmentName(name);
        dept.setDepartmentInitials(initials);
        dept.setDepartmentSummary(summary);
        dept.setDisciplineId(dispID);
        DBMgr.getInstance().saveDepartment(dept);
        return true;
    }

    public boolean addDiscipline(Integer univID, String name, String initials, String summary) {
        Discipline disp = new Discipline();
        disp.setDisciplineName(name);
        disp.setDisciplineInitials(initials);
        disp.setDisciplineSummary(summary);
        disp.setUniversityId(univID);
        DBMgr.getInstance().saveDiscipline(disp);
        return true;
    }

    public boolean addFeedback(Integer userId, Integer courseId, Integer professorId, String userName, String courseName,
                               String professorName, String title, String comment, Float rating, String date,
                               Boolean isAnonymous) {

        Feedback fb = new Feedback();
        fb.setUserId(userId);
        fb.setCourseId(courseId);
        fb.setProfessorId(professorId);
        fb.setUserName(userName);
        fb.setCourseName(courseName);
        fb.setProfessorName(professorName);
        fb.setTitle(title);
        fb.setComment(comment);
        fb.setRating(rating);
        fb.setDate(date);
        fb.setIsAnonymous(isAnonymous);

        DBMgr.getInstance().saveFeedback(fb);
        return true;
    }

}
