package com.project.android.app.kys.helper;

public class Constants {

    public class Pattern {
        public static final String DATE_PATTERN = "yyyy-MM-dd";
    }

    public class Tag {

        public class Choice {
            public static final int FROM_SETTINGS = 1;
            public static final int FROM_SIGN_UP = 0;
        }

        public class BusinessTag {
            public static final String UNIV_ID = "UNIV_ID";
            public static final String UNIV_NAME = "UNIV_NAME";
            public static final String DISCP_ID = "DISCP_ID";
            public static final String DISP_NAME = "DISP_NAME";
            public static final String DEPT_ID = "DEPT_ID";
            public static final String DEPT_NAME = "DEPT_NAME";
            public static final String MAJOR_ID = "MAJOR_ID";
            public static final String MAJOR_NAME = "MAJOR_NAME";
            public static final String COURSE_ID = "COURSE_ID";
            public static final String COURSE_NAME = "COURSE_NAME";
            public static final String COURSE_SUMMARY = "COURSE_SUMMARY";
            public static final String COURSE_CODE = "COURSE_CODE";
            public static final String COURSE_INITIALS = "COURSE_INITIALS";
            public static final String FEEDBACK_ID = "FEEDBACK_ID";
            public static final String FEEDBACK_NAME = "FEEDBACK_NAME";
            public static final String USER_ID = "USER_ID";
            public static final String USER_NAME = "USER_NAME";
        }


        public class DrawerItemTag {
            public static final String SETTINGS = "SETTINGS";
            public static final String ADD_PROFESSOR = "ADD_PROFESSOR";
            public static final String SPAM = "SPAM";
            public static final String LOGOUT = "LOGOUT";
        }

        public class ChoiceTag {
            public static final String SOURCE = "SOURCE";
        }

        public class SharedPrefTag {
            public static final String USER = "USER";
        }
    }

    public class Action {
        public static final String REFRESH_DISCIPLINE_LIST = "com.android.app.kys.REFRESH_DISCIPLINE_LIST";
        public static final String REFRESH_DEPARTMENT_LIST = "com.android.app.kys.REFRESH_DEPARTMENT_LIST";
        public static final String REFRESH_MAJOR_LIST = "com.android.app.kys.REFRESH_MAJOR_LIST";
        public static final String REFRESH_COURSE_LIST = "com.android.app.kys.REFRESH_COURSE_LIST";
        public static final String REFRESH_FEEDBACK_LIST = "com.android.app.kys.REFRESH_FEEDBACK_LIST";
        public static final String REFRESH_USER_FEEDBACK = "com.android.app.kys.REFRESH_USER_FEEDBACK";
    }


    public class IDs {
        public static final String USER_ID = "mUserId";
        public static final String FEEDBACK_ID = "mFeedBackId";
        public static final String PROF_ID = "mProfessorId";
        public static final String COURSE_ID = "mCourseId";
        public static final String MAJOR_ID = "mMajorId";
        public static final String DEPT_ID = "mDepartmentId";
        public static final String DISP_ID = "mDisciplineId";
        public static final String UNIV_ID = "mUniversityId";
    }

    public class Result {
        public static final String OK = "OK";
        public static final String FAIL = "FAIL";
        public static final String PENDING = "PENDING";
    }

    public static class RestResourcePaths {
        public static final String KYS_PACKAGE_PATH = "http://562c6399.ngrok.io/KnowYourSubject/rest";

        public static class RegistrationPath {
            public static final String CLASS = "/registration";
            public static final String REGISTER_USER = "/registeruser";

            public static final String METHOD_REGISTER_USER = KYS_PACKAGE_PATH + CLASS + REGISTER_USER;

        }

        public static class AuthenticationPath {
            public static final String CLASS = "/authentication";
            public static final String LOGIN = "/login";
            public static final String RETRIEVE_PASSWORD = "/retrievepassword";

            public static final String METHOD_LOGIN = KYS_PACKAGE_PATH + CLASS + LOGIN;
            public static final String METHOD_RETRIEVE_PASSWORD = KYS_PACKAGE_PATH + CLASS + RETRIEVE_PASSWORD;

        }

        public static class ProfessorPath {
            public static final String CLASS = "/professor";
            public static final String ADD_PROFESSOR = "/addprofessor";
            public static final String UPDATE_PROFESSOR = "/updateprofessor";
            public static final String DELETE_PROFESSOR = "/deleteprofessor";
            public static final String GET_PROFESSOR = "/getprofessor";
            public static final String GET_ALL_PROFESSOR = "/getallprofessors";
            public static final String GET_PROFESSOR_LIST_4_COURSE = "/getprofessorlist4course";


            public static final String METHOD_ADD_PROFESSOR = KYS_PACKAGE_PATH + CLASS + ADD_PROFESSOR;
            public static final String METHOD_UPDATE_PROFESSOR = KYS_PACKAGE_PATH + CLASS + UPDATE_PROFESSOR;
            public static final String METHOD_DELETE_PROFESSOR = KYS_PACKAGE_PATH + CLASS + DELETE_PROFESSOR;
            public static final String METHOD_GET_PROFESSOR = KYS_PACKAGE_PATH + CLASS + GET_PROFESSOR;
            public static final String METHOD_GET_ALL_PROFESSORS = KYS_PACKAGE_PATH + CLASS + GET_ALL_PROFESSOR;
            public static final String METHOD_GET_PROFESSOR_LIST_4_COURSE = KYS_PACKAGE_PATH + CLASS + GET_PROFESSOR_LIST_4_COURSE;
        }

        public static class CoursePath {
            public static final String CLASS = "/course";
            public static final String ADD_COURSE = "/addcourse";
            public static final String UPDATE_COURSE = "/updatecourse";
            public static final String DELETE_COURSE = "/deletecourse";
            public static final String GET_COURSE = "/getcourse";
            public static final String GET_COURSE_LIST_4_MAJOR = "/getcourselist4major";
            public static final String GET_COURSE_LIST_4_PROFESSOR = "/getcourselist4professor";

            public static final String METHOD_ADD_COURSE = KYS_PACKAGE_PATH + CLASS + ADD_COURSE;
            public static final String METHOD_UPDATE_COURSE = KYS_PACKAGE_PATH + CLASS + UPDATE_COURSE;
            public static final String METHOD_DELETE_COURSE = KYS_PACKAGE_PATH + CLASS + DELETE_COURSE;
            public static final String METHOD_GET_COURSE = KYS_PACKAGE_PATH + CLASS + GET_COURSE;
            public static final String METHOD_GET_COURSE_LIST_4_MAJOR = KYS_PACKAGE_PATH + CLASS + GET_COURSE_LIST_4_MAJOR;
            public static final String METHOD_GET_COURSE_LIST_4_PROFESSOR = KYS_PACKAGE_PATH + CLASS + GET_COURSE_LIST_4_PROFESSOR;
        }

        public static class MajorPath {
            public static final String CLASS = "/major";
            public static final String ADD_MAJOR = "/addmajor";
            public static final String UPDATE_MAJOR = "/updatemajor";
            public static final String DELETE_MAJOR = "/deletemajor";
            public static final String GET_MAJOR = "/getmajor";
            public static final String GET_MAJOR_LIST_4_DEPARTMENT = "/getmajorlist4department";

            public static final String METHOD_ADD_MAJOR = KYS_PACKAGE_PATH + CLASS + ADD_MAJOR;
            public static final String METHOD_UPDATE_MAJOR = KYS_PACKAGE_PATH + CLASS + UPDATE_MAJOR;
            public static final String METHOD_DELETE_MAJOR = KYS_PACKAGE_PATH + CLASS + DELETE_MAJOR;
            public static final String METHOD_GET_MAJOR = KYS_PACKAGE_PATH + CLASS + GET_MAJOR;
            public static final String METHOD_GET_MAJOR_LIST_4_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + GET_MAJOR_LIST_4_DEPARTMENT;
        }

        public static class DepartmentPath {
            public static final String CLASS = "/department";
            public static final String ADD_DEPARTMENT = "/adddepartment";
            public static final String UPDATE_DEPARTMENT = "/updatedepartment";
            public static final String DELETE_DEPARTMENT = "/deletedepartment";
            public static final String GET_DEPARTMENT = "/getdepartment";
            public static final String GET_DEPARTMENT_LIST_4_DISCIPLINE = "/getdepartmentlist4discipline";

            /*******************************************
             * POOJA
             **********************************************/

            public static final String GET_ALL_DEPARTMENT = "/getalldepartments";

            public static final String METHOD_ADD_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + ADD_DEPARTMENT;
            public static final String METHOD_UPDATE_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + UPDATE_DEPARTMENT;
            public static final String METHOD_DELETE_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + DELETE_DEPARTMENT;
            public static final String METHOD_GET_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + GET_DEPARTMENT;
            public static final String METHOD_GET_DEPARTMENT_LIST_4_DISCIPLINE = KYS_PACKAGE_PATH + CLASS + GET_DEPARTMENT_LIST_4_DISCIPLINE;
            public static final String METHOD_GET_ALL_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + GET_ALL_DEPARTMENT;
        }

        public static class DisciplinePath {
            public static final String CLASS = "/discipline";
            public static final String ADD_DISCIPLINE = "/adddiscipline";
            public static final String UPDATE_DISCIPLINE = "/updatediscipline";
            public static final String DELETE_DISCIPLINE = "/deletediscipline";
            public static final String GET_DISCIPLINE = "/getdiscipline";
            public static final String GET_DISCIPLINE_LIST_4_UNIVERSITY = "/getdisciplinelist4university";

            public static final String METHOD_ADD_DISCIPLINE = KYS_PACKAGE_PATH + CLASS + ADD_DISCIPLINE;
            public static final String METHOD_UPDATE_DISCIPLINE = KYS_PACKAGE_PATH + CLASS + UPDATE_DISCIPLINE;
            public static final String METHOD_DELETE_DISCIPLINE = KYS_PACKAGE_PATH + CLASS + DELETE_DISCIPLINE;
            public static final String METHOD_GET_DISCIPLINE = KYS_PACKAGE_PATH + CLASS + GET_DISCIPLINE;
            public static final String METHOD_GET_DISCIPLINE_LIST_4_UNIVERSITY = KYS_PACKAGE_PATH + CLASS + GET_DISCIPLINE_LIST_4_UNIVERSITY;
        }

        public static class UniversityPath {
            public static final String CLASS = "/university";
            public static final String ADD_UNIVERSITY = "/adduniversity";
            public static final String UPDATE_UNIVERSITY = "/updateuniversity";
            public static final String DELETE_UNIVERSITY = "/deleteuniversity";
            public static final String GET_UNIVERSITY = "/getuniversity";
            public static final String GET_UNIVERSITY_LIST = "/getuniversitylist";

            public static final String METHOD_ADD_UNIVERSITY = KYS_PACKAGE_PATH + CLASS + ADD_UNIVERSITY;
            public static final String METHOD_UPDATE_UNIVERSITY = KYS_PACKAGE_PATH + CLASS + UPDATE_UNIVERSITY;
            public static final String METHOD_DELETE_UNIVERSITY = KYS_PACKAGE_PATH + CLASS + DELETE_UNIVERSITY;
            public static final String METHOD_GET_UNIVERSITY = KYS_PACKAGE_PATH + CLASS + GET_UNIVERSITY;
            public static final String METHOD_GET_UNIVERSITY_LIST = KYS_PACKAGE_PATH + CLASS + GET_UNIVERSITY_LIST;
        }

        public static class FeedbackPath {
            public static final String CLASS = "/feedback";
            public static final String ADD_FEEDBACK = "/addfeedback";
            public static final String UPDATE_FEEDBACK = "/updatefeedback";
            public static final String DELETE_FEEDBACK = "/deletefeedback";
            public static final String GET_FEEDBACK = "/getfeedback";
            public static final String GET_FEEDBACK_LIST_4_USER = "/getfeedbacklist4user";
            public static final String GET_FEEDBACK_LIST_4_COURSE = "/getfeedbacklist4course";
            public static final String GET_FEEDBACK_LIST_4_PROFESSOR = "/getfeedbacklist4professor";
            public static final String GET_FEEDBACK_LIST_4_COMMENT = "/getfeedbacklist4comment";

            public static final String METHOD_ADD_FEEDBACK = KYS_PACKAGE_PATH + CLASS + ADD_FEEDBACK;
            public static final String METHOD_UPDATE_FEEDBACK = KYS_PACKAGE_PATH + CLASS + UPDATE_FEEDBACK;
            public static final String METHOD_DELETE_FEEDBACK = KYS_PACKAGE_PATH + CLASS + DELETE_FEEDBACK;
            public static final String METHOD_GET_FEEDBACK = KYS_PACKAGE_PATH + CLASS + GET_FEEDBACK;
            public static final String METHOD_GET_FEEDBACK_LIST_4_USER = KYS_PACKAGE_PATH + CLASS + GET_FEEDBACK_LIST_4_USER;
            public static final String METHOD_GET_FEEDBACK_LIST_4_COURSE = KYS_PACKAGE_PATH + CLASS + GET_FEEDBACK_LIST_4_COURSE;
            public static final String METHOD_GET_FEEDBACK_LIST_4_PROFESSOR = KYS_PACKAGE_PATH + CLASS + GET_FEEDBACK_LIST_4_PROFESSOR;
            public static final String METHOD_GET_FEEDBACK_LIST_4_COMMENT = KYS_PACKAGE_PATH + CLASS + GET_FEEDBACK_LIST_4_COMMENT;
        }

        public static class UserPath {
            public static final String CLASS = "/user";
            public static final String UPDATE_USER = "/updateuser";
            public static final String DELETE_USER = "/deleteuser";
            public static final String GET_USER = "/getuser";
            public static final String GET_USER_LIST_4_DEPARTMENT = "/getuserlist4department";

            /******************************
             * POOJA
             ******************************/
            public static final String CHANGE_PASSWORD = "/changepassword";
            public static final String RESET_PASSWORD = "/resetpassword";
            public static final String VERIFY_USER = "/verifyuser";
            /******************************
             * POOJA
             ******************************/

            public static final String METHOD_UPDATE_USER = KYS_PACKAGE_PATH + CLASS + UPDATE_USER;
            public static final String METHOD_DELETE_USER = KYS_PACKAGE_PATH + CLASS + DELETE_USER;
            public static final String METHOD_GET_USER = KYS_PACKAGE_PATH + CLASS + GET_USER;
            public static final String METHOD_GET_USER_LIST_4_DEPARTMENT = KYS_PACKAGE_PATH + CLASS + GET_USER_LIST_4_DEPARTMENT;
            public static final String METHOD_CHANGE_PASSWORD = KYS_PACKAGE_PATH + CLASS + CHANGE_PASSWORD;
            public static final String METHOD_RESET_PASSWORD = KYS_PACKAGE_PATH + CLASS + RESET_PASSWORD;
            public static final String METHOD_VERIFY_USER = KYS_PACKAGE_PATH + CLASS + VERIFY_USER;

        }


    }

    public static class Keys {

        public static class UserTable {
            public static final String ID = "Email_ID";
            public static final String DEPT_ID = "DeptID";
            public static final String PASSWORD = "Password";
            public static final String FNAME = "FName";
            public static final String LNAME = "LName";
            public static final String SECR_QUE = "SecQue";
            public static final String SECR_ANSWR = "SecAnswr";
            public static final String IS_ADMIN = "IsAdmin";
            public static final String DOB = "DOB";
            public static final String CONTACT = "Contact";
            public static final String ZIP_CODE = "ZIPCode";

        }

        public static class ProfessorTable {
            public static final String ID = "ProfID";
            public static final String NAME = "PName";
            public static final String EMAIL = "PEmail";
            public static final String CONATCT = "PContact";
            public static final String URL = "PUrl";
        }

        public static class ProfessorCourseTable {
            public static final String ID = "ProfCourseID";
            public static final String PROF_ID = "ProfID";
            public static final String COURSE_ID = "CourseID";
        }

        public static class CourseTable {
            public static final String ID = "CourseID";
            public static final String MAJOR_ID = "MajorID";
            public static final String CODE = "CCode";
            public static final String NAME = "CName";
            public static final String INITIALS = "CInitials";
            public static final String SUMMARY = "CSummary";
            public static final String NO_OF_FEEDBACK = "CNoFeedback";
            public static final String RATINGS = "CRating";
        }

        public static class MajorTable {
            public static final String ID = "MajorID";
            public static final String DEPT_ID = "DeptID";
            public static final String NAME = "MName";
            public static final String INITIALS = "MInitials";
            public static final String SUMMARY = "MSummary";
        }

        public static class DepartmentTable {
            public static final String ID = "DeptID";
            public static final String DISP_ID = "DispID";
            public static final String NAME = "DeptName";
            public static final String INITIALS = "DeptInitials";
            public static final String SUMMARY = "DeptSummary";
        }

        public static class DisciplineTable {
            public static final String ID = "DispID";
            public static final String UID = "UID";
            public static final String NAME = "DispName";
            public static final String INITIALS = "DispInitials";
            public static final String SUMMARY = "DispSummary";
        }

        public static class UniversityTable {
            public static final String ID = "UID";
            public static final String NAME = "UName";
            public static final String INITIALS = "UInitials";
            public static final String CITY = "UCity";
            public static final String STATE = "UState";
            public static final String COUNTRY = "UCountry";
            public static final String SUMMARY = "USummary";
        }

        public static class FeedbackTable {
            public static final String ID = "FeedbackID";
            public static final String USER_ID = "UserID";
            public static final String COURSE_ID = "CourseID";
            public static final String PROF_ID = "ProfID";
            public static final String TITLE = "Title";
            public static final String COMMENT = "Comment";
            public static final String RATINGS = "Rating";
            public static final String SUBMIT_TIME = "SubmitTime";
            public static final String HELPFUL_CNT = "Helpfulness_cnt";
            public static final String UNHELPFUL_CNT = "UnHelpfulness_cnt";
            public static final String IS_SPAM = "IsSpam";
            public static final String IS_ANONYMOUS = "IsAnonymous";
        }
    }

    public class Response {

        public class ResponseMessage {
            public final static String UNKNOWN_ERROR = "Oops..Something went wrong!!";
            public final static String NETWORK_ERROR = "Network error!!";

            public final static String SUCCESS_MSG = "SUCCESS";
            public final static String INVALID_USERNAME_PASSWORD = "Username or Password is wrong";

            //Exceptions
            public final static String BUSINESS_EXCEPTION = "Business Exception";
            public final static String DB_EXCEPTION = "OOPS something went wrong";

            //University
            public final static String UNIVERSITY_NAME_EXISTS_MSG = "University Name Exists";

            //Discpline
            public final static String DISCIPLINE_ID = "DispID";
            public final static String DISCIPLINE_ID_EXISTS_MSG = "Discipline id already exists";

            //Department
            public final static String DEPARTMENT_ID_EXISTS_MSG = "Department id already exists";
            public final static String DEPARTMENT_NAME_EXISTS_MSG = "Department name already Exists";

            //Major
            public final static String MAJOR_NAME_EXISTS_MSG = "Major name already exists";

            public final static String DISCIPLINE = "discipline";

            //Professor
            public final static String PROFESSOR_EMAIL_EXISTS_MSG = "Professor email already exists";

            //Course
            public final static String COURSE_CODE_EXISTS_MSG = "Course code already exists";

            //User
            public final static String EMAIL_ID_EXISTS_MSG = "EmaiId already exists";
            public final static String USER_NOT_EXISTS = "Invalid emailid or password.";

            //Feedback
            public final static String FEEDBACK_EXISTS = "Feedback is already been added by you for the course";
        }

        public class ResponseTag {
            public static final String STATUS_CODE = "code";
            public static final String STATUS = "status";

            public class Map {
                public static final String USER_MAP = "userMap";
                public static final String PROFESSOR_MAP = "professorMap";
                public static final String FEEDBACK_MAP = "feedbackMap";
                public static final String COURSE_MAP = "courseMap";
                public static final String MAJOR_MAP = "majorMap";
                public static final String DEPARTMENT_MAP = "departmentMap";
                public static final String DISCIPLINE_MAP = "disciplineMap";
                public static final String UNIVERSITY_MAP = "universityMap";
            }

        }

        public class Status {
            public static final String SUCCESS = "SUCCESS";
            public static final String NO_DATA = "NODATA";
            public static final String DATA_ERROR = "601";
            public static final String DB_ERROR = "602";
            public static final String OK = "200";
        }
    }
}