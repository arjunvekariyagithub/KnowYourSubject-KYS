package com.project.android.app.kys.callbacks;

import android.content.Context;

import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.fragments.ChangePasswordFragment;
import com.project.android.app.kys.fragments.CourseFragment;
import com.project.android.app.kys.fragments.DepartmentFragment;
import com.project.android.app.kys.fragments.DisciplineFragment;
import com.project.android.app.kys.fragments.DrawerFragment;
import com.project.android.app.kys.fragments.ForgetPasswordFragment;
import com.project.android.app.kys.fragments.HomeFragment;
import com.project.android.app.kys.fragments.LoginFragment;
import com.project.android.app.kys.fragments.MajorFragment;
import com.project.android.app.kys.fragments.RegistrationFragment;
import com.project.android.app.kys.fragments.ResetPasswordFragment;
import com.project.android.app.kys.fragments.UpdateProfileFragment;
import com.project.android.app.kys.popup.AUCourseDialog;
import com.project.android.app.kys.popup.AUDepartmentDialog;
import com.project.android.app.kys.popup.AUDisciplineDialog;
import com.project.android.app.kys.popup.AUFeedbackDialog;
import com.project.android.app.kys.popup.AUMajorDialog;
import com.project.android.app.kys.popup.AUProfessorDialog;

import java.util.ArrayList;
import java.util.List;

public class ResponseListener implements Callbacks {
    private static ResponseListener mSingletonInstance;
    private static Context mContext;


    public static LoginFragment loginListener;
    public static CourseFragment courseFeedbackDataListener;
    public static MajorFragment courseDataListener;
    public static DepartmentFragment majorDataListener;
    public static DisciplineFragment departmentDataListener;
    public static DrawerFragment disciplineDataListener;

    public static AUProfessorDialog addProfessorListener;
    public static AUCourseDialog addCourseListener;
    public static AUMajorDialog addMajorListener;
    public static AUDepartmentDialog addDepartmentListener;
    public static AUDisciplineDialog addDisciplineListener;
    public static AUFeedbackDialog addFeedbackListener;

    /**************** POOJA *****************/
    public static RegistrationFragment registerListener;
    public static UpdateProfileFragment updateListener;
    public static ChangePasswordFragment changePasswordListener;
    public static ForgetPasswordFragment forgetPasswordListener;
    public static ResetPasswordFragment resetPasswordListener;
    public static HomeFragment homeListener;
    /**************** POOJA *****************/


    private ResponseListener(Context context) {
        mContext = context;
    }

    public static ResponseListener createInstance(Context context) {
        return new ResponseListener(context);
    }

    public static ResponseListener getInstnace() {
        if (mSingletonInstance == null) {
            mSingletonInstance = createInstance(mContext);
        }
        return mSingletonInstance;
    }

    @Override
    public void onLoginResponse(User user, boolean isError, String error) {
        if (loginListener != null) {
            loginListener.onResponse(user, isError, error);
        }
    }

    @Override
    public void onDisciplineDataResponse(ArrayList<Discipline> dispList, boolean isError, String error) {
        if (disciplineDataListener != null) {
            disciplineDataListener.onResponse(dispList, isError, error);
        }
    }

    @Override
    public void onDepartmentDataResponse(ArrayList<Department> deptList, boolean isError, String error) {
        if (departmentDataListener != null) {
            departmentDataListener.onResponse(deptList, isError, error);
        }
    }

    @Override
    public void onMajorDataResponse(ArrayList<Major> majorList, boolean isError, String error) {
        if (majorDataListener != null) {
            majorDataListener.onResponse(majorList, isError, error);
        }
    }

    @Override
    public void onCourseDataResponse(ArrayList<Course> courseList, boolean isError, String error) {
        if (courseDataListener != null) {
            courseDataListener.onResponse(courseList, isError, error);
        }
    }

    @Override
    public void onAllFeedbackDataResponse(ArrayList<Feedback> fbList, boolean isError, String error) {
        if (courseFeedbackDataListener != null) {
            courseFeedbackDataListener.onAllFeedbackResponse(fbList, isError, error);
        }
    }

    @Override
    public void onUserFeedbackDataResponse(Feedback fb, boolean isError, String error) {
        if (courseFeedbackDataListener != null) {
            courseFeedbackDataListener.onUserFeedbackResponse(fb, isError, error);
        }
    }

    @Override
    public void onCourseProfessorDataResponse(ArrayList<Professor> profList, boolean isError, String error) {
        if (courseFeedbackDataListener != null) {
            courseFeedbackDataListener.onCourseProfessorDataResponse(profList, isError, error);
        }
    }

    @Override
    public void onAllProfessorsForCourseLinkResponse(ArrayList<Professor> profList, boolean isError, String error) {
        if (courseFeedbackDataListener != null) {
            courseFeedbackDataListener.onAllProfessorsResponse(profList, isError, error);
        }
    }


    @Override
    public void addProfessorResponse(String response, boolean isError) {
        if (addProfessorListener != null) {
            addProfessorListener.onResponse(response, isError);
        }
    }

    @Override
    public void addCourseResponse(String response, boolean isError) {
        if (addCourseListener != null) {
            addCourseListener.onResponse(response, isError);
        }
    }

    @Override
    public void addMajorResponse(String response, boolean isError) {
        if (addMajorListener != null) {
            addMajorListener.onResponse(response, isError);
        }
    }

    @Override
    public void addDepartmentResponse(String response, boolean isError) {
        if (addDepartmentListener != null) {
            addDepartmentListener.onResponse(response, isError);
        }
    }

    @Override
    public void addDisciplineResponse(String response, boolean isError) {
        if (addDisciplineListener != null) {
            addDisciplineListener.onResponse(response, isError);
        }
    }

    @Override
    public void addFeedbackResponse(String response, boolean isError) {
        if (addFeedbackListener != null) {
            addFeedbackListener.onResponse(response, isError);
        }
    }

    @Override
    public void onUpdateCourseResponse(String response, boolean isError) {
        if (courseFeedbackDataListener != null) {
            courseFeedbackDataListener.onUpdateCourseResponce(response, isError);
        }


    }

    public void setLoginResponseListener(LoginFragment listener) {
        loginListener = listener;
    }

    public void setDisciplineDataListener(DrawerFragment listener) {
        disciplineDataListener = listener;
    }

    public void setCourseDataListener(MajorFragment listener) {
        courseDataListener = listener;
    }

    public void setCourseFeedbackDataListener(CourseFragment listener) {
        courseFeedbackDataListener = listener;
    }

    public void setMajorDataListener(DepartmentFragment listener) {
        majorDataListener = listener;
    }

    public void setDepartmentDataListener(DisciplineFragment listener) {
        departmentDataListener = listener;
    }

    public void setAddProfessorListener(AUProfessorDialog listener) {
        addProfessorListener = listener;
    }

    public void setAddCourseListener(AUCourseDialog listener) {
        addCourseListener = listener;
    }

    public void setAddMajorListener(AUMajorDialog listener) {
        addMajorListener = listener;
    }

    public void setAddDepartmentListener(AUDepartmentDialog listener) {
        addDepartmentListener = listener;
    }

    public void setAddDisciplineListener(AUDisciplineDialog listener) {
        addDisciplineListener = listener;
    }

    public void setAddFeedbackListener(AUFeedbackDialog listener) {
        addFeedbackListener = listener;
    }

    /******************************** POOJA ***********************************/


    public void setRegisterResponseListener(RegistrationFragment listener) {
        registerListener = listener;
    }

    @Override
    public void onRegisterResponse(User user, boolean isError, String error) {
        if (registerListener != null) {
            registerListener.onResponse(user, isError, error);
        }
    }

    public void setUpdateResponseListener(UpdateProfileFragment listener) {
        updateListener = listener;
    }

    @Override
    public void onUpdateProfileResponse(User user, boolean isError, String error) {
        if (updateListener != null) {
            updateListener.onResponse(user, isError, error);
        }
    }

    @Override
    public void onDepartmentUpdateResponse(List<Department> dept, boolean isError, String error) {
        if (updateListener != null) {
            updateListener.onGetDepetment(dept, isError, error);
        }
    }

    @Override
    public void onUserDataResponse(User user, boolean isError, String error) {
        if (updateListener != null) {
            updateListener.getUserData(user, isError, error);
        }
    }


    @Override
    public void onDepartmentRegisterResponse(List<Department> dept, boolean isError, String error) {
        if (registerListener != null) {
            registerListener.onGetDepetment(dept, isError, error);
        }
    }

    public void setChangePasswordListener(ChangePasswordFragment listener) {
        changePasswordListener = listener;
    }

    @Override
    public void onChangePasswordResponse(User user, boolean isError, String error) {
        if (changePasswordListener != null) {
            changePasswordListener.onResponse(user, isError, error);
        }
    }


    public void setForgetPasswordListenerListener(ForgetPasswordFragment listener) {
        forgetPasswordListener = listener;
    }


    @Override
    public void onVerifyUserResponse(User user, boolean isError, String error) {
        if (forgetPasswordListener != null) {
            forgetPasswordListener.onVerifyUserResponse(user, isError, error);
        }
    }


    public void setResetPasswordListenerListener(ResetPasswordFragment listener) {
        resetPasswordListener = listener;
    }


    @Override
    public void onResetPasswordResponse(User user, boolean isError, String error) {
        if (resetPasswordListener != null) {
            resetPasswordListener.onResetPasswordResponse(user, isError, error);
        }
    }

    public void setHomeListener(HomeFragment listener) {
        homeListener = listener;
    }

    @Override
    public void onMajorDataForHomeResponse(ArrayList<Major> majorList, boolean isError, String error) {
        if (homeListener != null) {
            homeListener.onMajorListForHomeResponse(majorList, isError, error);
        }
    }

    @Override
    public void onFeedbackDataForHomeResponse(ArrayList<Feedback> fbList, boolean isError, String error) {
        if (homeListener != null) {
            homeListener.onFeedbackListForHomeResponse(fbList, isError, error);
        }
    }




    /******************************** POOJA ***********************************/
}
