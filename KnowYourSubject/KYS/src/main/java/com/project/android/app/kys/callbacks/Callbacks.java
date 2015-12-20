package com.project.android.app.kys.callbacks;

import android.content.Context;

import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.fragments.LoginFragment;

import java.util.ArrayList;
import java.util.List;


public interface Callbacks {
    public void onLoginResponse(User user, boolean isError, String error);
    public void onDisciplineDataResponse(ArrayList<Discipline> dispList, boolean isError, String error);
    public void onDepartmentDataResponse(ArrayList<Department> deptList, boolean isError, String error);
    public void onMajorDataResponse(ArrayList<Major> majorList, boolean isError, String error);
    public void onCourseDataResponse(ArrayList<Course> courseList, boolean isError, String error);
    public void onAllFeedbackDataResponse(ArrayList<Feedback> fbList, boolean isError, String error);
    public void onUserFeedbackDataResponse(Feedback fb, boolean isError, String error);
    public void onCourseProfessorDataResponse(ArrayList<Professor> profList, boolean isError, String error);
    public void onAllProfessorsForCourseLinkResponse(ArrayList<Professor> profList, boolean isError, String error);

    public void addProfessorResponse(String response, boolean isError);
    public void addCourseResponse(String response, boolean isError);
    public void addMajorResponse(String response, boolean isError);
    public void addDepartmentResponse(String response, boolean isError);
    public void addDisciplineResponse(String response, boolean isError);
    public void addFeedbackResponse(String response, boolean isError);

    public void onUpdateCourseResponse(String response, boolean isError);



    /********************************** POOJA *************************************/

    public void onRegisterResponse(User user, boolean isError, String error);
    public void onUpdateProfileResponse(User user, boolean isError, String error);
    public void onDepartmentRegisterResponse(List<Department> dept, boolean isError, String error);
    public void onDepartmentUpdateResponse(List<Department> dept, boolean isError, String error);
    public void onUserDataResponse(User user, boolean isError, String error);
    public void onChangePasswordResponse(User user, boolean isError, String error);
    public void onVerifyUserResponse(User user, boolean isError, String error);
    public void onResetPasswordResponse(User user, boolean isError, String error);
    public void onMajorDataForHomeResponse(ArrayList<Major> majorList, boolean isError, String error);
    public void onFeedbackDataForHomeResponse(ArrayList<Feedback> fbList, boolean isError, String error);
}
