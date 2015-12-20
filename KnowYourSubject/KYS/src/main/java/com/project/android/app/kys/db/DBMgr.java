package com.project.android.app.kys.db;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.business.University;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.helper.Constants.IDs;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.AuthenticationPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.CoursePath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.DepartmentPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.DisciplinePath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.FeedbackPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.MajorPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.ProfessorPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.RegistrationPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.UniversityPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.UserPath;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.json.JSONRequester;
import com.project.android.app.kys.network.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBMgr {
    private static DBMgr mSingletonInstance = null;
    private static JSONRequester mJSONRequester = null;
    private static ObjectMapper mapper;
    private static Gson gson;
    private static VolleySingleton mVollySingleton;
    private static RequestQueue mRequestQueue;
    private static Context mContext = null;

    private static String mResponse = null;

    public DBMgr(Context context) {
        mContext = context;
        mJSONRequester = JSONRequester.getInstance();
        mapper = new ObjectMapper();
        gson = new Gson();
    }

    public static DBMgr createInstance(Context context) {

        if (mSingletonInstance == null) {
            mSingletonInstance = new DBMgr(context);
        }
        return mSingletonInstance;
    }

    public static DBMgr getInstance() {

        if (mSingletonInstance == null) {
            mSingletonInstance = createInstance(mContext);
        }
        return mSingletonInstance;
    }

    public boolean saveProfessor(Professor prof) {
        String json_professor_class = null;
        try {
            json_professor_class = gson.toJson(prof);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_professor_class)) {
            mJSONRequester.saveProfessor(ProfessorPath.METHOD_ADD_PROFESSOR, json_professor_class);
        }
        return true;
    }

    public boolean saveCourse(Course course) {
        String json_course_class = null;
        try {
            json_course_class = gson.toJson(course);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_course_class)) {
            mJSONRequester.saveCourse(CoursePath.METHOD_ADD_COURSE, json_course_class);
        }
        return true;
    }

    public boolean saveMajor(Major major) {
        String json_major_class = null;
        try {
            json_major_class = gson.toJson(major);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_major_class)) {
            mJSONRequester.saveMajor(MajorPath.METHOD_ADD_MAJOR, json_major_class);
        }
        return true;
    }

    public boolean saveDepartment(Department dept) {
        String json_dept_class = null;
        try {
            json_dept_class = gson.toJson(dept);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_dept_class)) {
            mJSONRequester.saveDepartment(DepartmentPath.METHOD_ADD_DEPARTMENT, json_dept_class);
        }
        return true;
    }

    public boolean saveDiscipline(Discipline disp) {
//        Discipline discipline = new Discipline();
//        discipline.setDisciplineName("Mathematics");
//        discipline.setDisciplineInitials("MATH");
//        discipline.setDisciplineSummary("Mathematics discipline is the worst discipline amongst all the discipline.");

        String json_discipline_class = null;
        try {
            json_discipline_class = gson.toJson(disp);
            Log.d("ARJUN", "json_discipline_class :- " + json_discipline_class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_discipline_class)) {
            Log.d("ARJUN", "URL :- " + DisciplinePath.METHOD_ADD_DISCIPLINE);
            mJSONRequester.saveDiscipline(DisciplinePath.METHOD_ADD_DISCIPLINE, json_discipline_class);
        }


//        HashMap<String,String> disciplineMap = new HashMap<String,String>();
//        disciplineMap.put("mDisciplineId", "DISP_*****1");
//        disciplineMap.put("mDisciplineName", "Mathematics");
//        disciplineMap.put("mDisciplineInitials", "MATH");
//        disciplineMap.put("mDisciplineSummary", "Mathematics discipline is the worst discipline amongst all the discipline.");
//
//        mJSONRequester.saveDiscipline(DisciplinePath.METHOD_ADD_DISCIPLINE, disciplineMap);

        return true;
    }

    public boolean saveFeedback(Feedback fb) {
        String json_feedback_class = null;
        try {
            json_feedback_class = gson.toJson(fb);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_feedback_class)) {
            mJSONRequester.saveFeedback(FeedbackPath.METHOD_ADD_FEEDBACK, json_feedback_class);
        }
        return true;
    }

//    public boolean saveUser(User user) {
//        String json_user_class = null;
//        try {
//            json_user_class = gson.toJson(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (Util.isNotNull(json_user_class)) {
//            mJSONRequester.saveUser(UserPath.METHOD_UPDATE_USER, json_user_class);
//        }
//        return true;
//    }

    public Professor getProfessor(Integer profID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.PROF_ID, profID);

        mJSONRequester.getProfessor(ProfessorPath.METHOD_GET_PROFESSOR, map);
        return new Professor();
    }

    public Course getCourse(Integer courseID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.COURSE_ID, courseID);

        mJSONRequester.getCourse(CoursePath.METHOD_GET_COURSE, map);
        return new Course();
    }

    public Major getMajor(Integer majorID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.MAJOR_ID, majorID);

        mJSONRequester.getMajor(MajorPath.METHOD_GET_MAJOR, map);
        return new Major();
    }

    public Department getDepartment(Integer deptID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.DEPT_ID, deptID);

        mJSONRequester.getDepartment(DepartmentPath.METHOD_GET_DEPARTMENT, map);
        return new Department();
    }

    public Discipline getDiscipline(Integer dispID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.DISP_ID, dispID);

        mJSONRequester.getDiscipline(DisciplinePath.METHOD_GET_DISCIPLINE, map);
        return new Discipline();
    }

    public Feedback getFeedback(Integer fbID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.FEEDBACK_ID, fbID);

        mJSONRequester.getFeedback(FeedbackPath.METHOD_GET_FEEDBACK, map);
        return new Feedback();
    }

    public User getUser(Integer userID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.USER_ID, userID);

        mJSONRequester.getUser(UserPath.METHOD_GET_USER, map);
        return new User();
    }

    public University getUniversity(Integer univID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(IDs.UNIV_ID, univID);

        mJSONRequester.getUniversity(UniversityPath.METHOD_GET_UNIVERSITY, map);
        return new University();
    }

    public void getDispsList() {
        getDispsList4(1);
    }

    public ArrayList<Professor> getProfessorsList4(Integer courseID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.COURSE_ID, courseID);
        mJSONRequester.getProfessorsList4(ProfessorPath.METHOD_GET_PROFESSOR_LIST_4_COURSE, params);
        return null;
    }

    public ArrayList<Course> getCoursesList4(Integer majorID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.MAJOR_ID, majorID);
        mJSONRequester.getCoursesList4(CoursePath.METHOD_GET_COURSE_LIST_4_MAJOR, params);
        return null;
    }

    public void getMajorsList4Home(Integer deptmentID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.DEPT_ID, deptmentID);
        mJSONRequester.getMajorList4Home(MajorPath.METHOD_GET_MAJOR_LIST_4_DEPARTMENT, params);
    }

    public void getFeedbackList4Home(Integer userID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.USER_ID, userID);
        mJSONRequester.getFeedbackList4Home(FeedbackPath.METHOD_GET_FEEDBACK_LIST_4_USER, params);
    }

    public ArrayList<Major> getMajorsList4(Integer deptID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.DEPT_ID, deptID);
        mJSONRequester.getMajorList4(MajorPath.METHOD_GET_MAJOR_LIST_4_DEPARTMENT, params);
        return null;
    }

    public ArrayList<Department> getDeptsList4(Integer dispID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.DISP_ID, dispID);
        mJSONRequester.getDeptsList4(DepartmentPath.METHOD_GET_DEPARTMENT_LIST_4_DISCIPLINE, params);
        return null;
    }

    public void getDispsList4(Integer univID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.UNIV_ID, univID);
        mJSONRequester.getDisciplineList4(DisciplinePath.METHOD_GET_DISCIPLINE_LIST_4_UNIVERSITY, params);
    }

    public ArrayList<Feedback> getFeedbackList4(String key, Integer courseId, Integer profId) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        String url;
        if (key.equals(IDs.COURSE_ID)) {
            params.put(IDs.COURSE_ID, courseId);
            mJSONRequester.getFeedbackList4Course(FeedbackPath.METHOD_GET_FEEDBACK_LIST_4_COURSE, params);
        } else if (key.equals(IDs.PROF_ID)) {
            params.put(IDs.COURSE_ID, courseId);
            params.put(IDs.PROF_ID, profId);
            mJSONRequester.getFeedbackList4Professor(FeedbackPath.METHOD_GET_FEEDBACK_LIST_4_PROFESSOR, params);
        }
        return null;
    }

    public ArrayList<Feedback> getCourseFeedbackForUser(Integer userID, Integer courseID) {
        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put(IDs.USER_ID, userID);
        params.put(IDs.COURSE_ID, courseID);
        mJSONRequester.getCourseFeedback4User(FeedbackPath.METHOD_GET_FEEDBACK_LIST_4_USER, params);
        return null;
    }

    public boolean updateProfessor(String fbID) {
        return true;
    }

    public boolean updateCourse(Course course) {

        String json_course_class = null;
        try {
            json_course_class = gson.toJson(course);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("PROFESSOR", "json_course_class = " + json_course_class);
        if (Util.isNotNull(json_course_class)) {
            mJSONRequester.updateCourse(CoursePath.METHOD_UPDATE_COURSE, json_course_class);
        }


        return true;
    }

    public boolean updateMajor(String fbID) {
        return true;
    }

    public boolean updateDepartment(String fbID) {
        return true;
    }

    public boolean updateDiscipline(String fbID) {
        return true;
    }

    public boolean updateUniversity(String fbID) {
        return true;
    }

    public boolean updateFeedback(String fbID) {
        return true;
    }

    public boolean updateUser(String userID) {
        return true;
    }


    public boolean deleteProfessor(String fbID) {
        return true;
    }

    public boolean deleteCourse(String fbID) {
        return true;
    }

    public boolean deleteMajor(String fbID) {
        return true;
    }

    public boolean deleteDepartment(String fbID) {
        return true;
    }

    public boolean deleteDiscipline(String fbID) {
        return true;
    }

    public boolean deleteUniversity(String fbID) {
        return true;
    }

    public boolean deleteFeedback(String fbID) {
        return true;
    }

    public boolean deleteUser(String fbID) {
        return true;
    }


    public String login(User user) {


        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_user_class)) {
            Log.d("ARJUN", "" + json_user_class);
            mJSONRequester.login(AuthenticationPath.METHOD_LOGIN, json_user_class);
        }

        return null;
    }

    public int getSpamCount() {
        return 25;
    }

    /********************************************
     * ARJUN
     **********************************************/

    public boolean register(User user) {
        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_user_class)) {
            Log.d("ARJUN", "" + json_user_class);
            mJSONRequester.register(RegistrationPath.METHOD_REGISTER_USER, json_user_class);
        }
        return true;
    }

    public boolean updateProfile(User user) {

        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_user_class)) {
            mJSONRequester.updateProfile(UserPath.METHOD_UPDATE_USER, json_user_class);
        }
        return true;
    }

    public boolean changePassword(User user) {

        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_user_class)) {
            mJSONRequester.changePassword(UserPath.METHOD_CHANGE_PASSWORD, json_user_class);
        }
        return true;
    }


    public List<Department> getAllDepartments4UserProfile() {
        mJSONRequester.getDeptList4UserProfile(DepartmentPath.METHOD_GET_ALL_DEPARTMENT);
        return null;
    }

    public List<Department> getAllDepartments4Regiteration() {
        mJSONRequester.getDeptList4Register(DepartmentPath.METHOD_GET_ALL_DEPARTMENT);
        return null;
    }

    public void getAllProfessors() {
        mJSONRequester.getAllProfessors(ProfessorPath.METHOD_GET_ALL_PROFESSORS);
    }

    public boolean getUserData(User user) {
        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("test", "ABC: " + json_user_class);

        if (Util.isNotNull(json_user_class)) {
            mJSONRequester.getUserData(UserPath.METHOD_GET_USER, json_user_class);
        }
        return true;
    }


    public Boolean resetPassword(User user) {

        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("test", "ABC: " + json_user_class);

        if (Util.isNotNull(json_user_class)) {
            mJSONRequester.resetPassword(UserPath.METHOD_RESET_PASSWORD, json_user_class);
        }
        return true;
    }

    public boolean verifyUser(User user) {
        String json_user_class = null;
        try {
            json_user_class = gson.toJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isNotNull(json_user_class)) {
            Log.d("ARJUN", "" + json_user_class);
            mJSONRequester.verifyUser(UserPath.METHOD_VERIFY_USER, json_user_class);
        }
        return true;
    }

}