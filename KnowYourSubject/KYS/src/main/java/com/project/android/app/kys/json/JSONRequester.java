package com.project.android.app.kys.json;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.helper.Constants.Response.ResponseMessage;
import com.project.android.app.kys.helper.Constants.Response.ResponseTag;
import com.project.android.app.kys.helper.Constants.Response.ResponseTag.Map;
import com.project.android.app.kys.helper.Constants.Response.Status;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.DepartmentPath;
import com.project.android.app.kys.helper.Constants.RestResourcePaths.UserPath;
import com.project.android.app.kys.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONRequester {
    private static JSONRequester mSingletonInstance = null;
    private static VolleySingleton mVollySingleton;
    private static RequestQueue mRequestQueue;
    private static Gson gson;
    private static Context mContext = null;


    public JSONRequester(Context context) {
        mContext = context;
        mVollySingleton = VolleySingleton.getInstance();
        mRequestQueue = mVollySingleton.getRequestQueue();
        gson = new Gson();
    }

    public static JSONRequester getInstance() {

        if (mSingletonInstance == null) {
            mSingletonInstance = new JSONRequester(mContext);
        }
        return mSingletonInstance;
    }

    public static boolean hasTag(JSONObject response, String tag) {
        return response.has(tag);
    }

    public static String getStatusCode(JSONObject response, String tag) {
        try {
            return response.getString(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void login(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            User user = gson.fromJson(response.getJSONObject(Map.USER_MAP).toString(), User.class);
                                            ResponseListener.getInstnace().onLoginResponse(user, false, null);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onLoginResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onLoginResponse(null, true, null);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onLoginResponse(null, true, ResponseMessage.UNKNOWN_ERROR);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onLoginResponse(null, true, ResponseMessage.NETWORK_ERROR);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void saveDiscipline(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));


                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            String status = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addDisciplineResponse(status, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addDisciplineResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().addDisciplineResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().addDisciplineResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "saveDiscipline() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().addDisciplineResponse(ResponseMessage.NETWORK_ERROR, true);


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void saveProfessor(String url, String params) {
        JsonObjectRequest request = null;
        Log.d("ARJUN", "saveProfessor() url :" + url);
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "saveProfessor - onResponse() : " + response.toString());

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            String status = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addProfessorResponse(status, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addProfessorResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().addProfessorResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().addProfessorResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "saveDiscipline() - onErrorResponse() : " + error.getMessage());
                    ResponseListener.getInstnace().addProfessorResponse(ResponseMessage.NETWORK_ERROR, true);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);

    }

    public void saveCourse(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));


                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            String status = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addCourseResponse(status, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addCourseResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().addCourseResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().addCourseResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "saveDiscipline() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().addCourseResponse(ResponseMessage.NETWORK_ERROR, true);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void saveMajor(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));


                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            String status = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addMajorResponse(status, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addMajorResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().addMajorResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().addMajorResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "saveDiscipline() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().addMajorResponse(ResponseMessage.NETWORK_ERROR, true);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void saveDepartment(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));


                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            String status = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addDepartmentResponse(status, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addDepartmentResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().addDepartmentResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().addDepartmentResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "saveDiscipline() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().addDepartmentResponse(ResponseMessage.NETWORK_ERROR, true);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void saveFeedback(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "saveFeedback - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            String status = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addFeedbackResponse(status, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().addFeedbackResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().addFeedbackResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().addFeedbackResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "saveFeedback() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().addFeedbackResponse(ResponseMessage.NETWORK_ERROR, true);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void getAllProfessors(String url) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getAllProfessors() - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));


                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Professor>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.PROFESSOR_MAP);
                                ArrayList<Professor> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onAllProfessorsForCourseLinkResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onAllProfessorsForCourseLinkResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onAllProfessorsForCourseLinkResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onAllProfessorsForCourseLinkResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getProfessorsList4(String url, HashMap<String, Integer> params) {
        Log.d("ARJUN", "getProfessorsList4()");
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getDeptsList4() - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));


                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Professor>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.PROFESSOR_MAP);
                                ArrayList<Professor> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onCourseProfessorDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onCourseProfessorDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onCourseProfessorDataResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onCourseProfessorDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getCoursesList4(String url, HashMap<String, Integer> params) {
        Log.d("ARJUN", "getCoursesList4()");
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getCoursesList4() - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Course>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.COURSE_MAP);
                                ArrayList<Course> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onCourseDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onCourseDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onCourseDataResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onCourseDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getMajorList4(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Major>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.MAJOR_MAP);
                                ArrayList<Major> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onMajorDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onMajorDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onMajorDataResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onMajorDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getDeptsList4(String url, HashMap<String, Integer> params) {
        Log.d("ARJUN", "getDeptsList4()");
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getDeptsList4() - onResponse() : " + response.toString());

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Department>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.DEPARTMENT_MAP);
                                ArrayList<Department> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onDepartmentDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onDepartmentDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onDepartmentDataResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onDepartmentDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getDisciplineList4(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Discipline>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.DISCIPLINE_MAP);
                                ArrayList<Discipline> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onDisciplineDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onDisciplineDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onDisciplineDataResponse(null, true, null);
                                        break;
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());

                ResponseListener.getInstnace().onDisciplineDataResponse(null, true, ResponseMessage.NETWORK_ERROR);
            }
        });
        mRequestQueue.add(request);
    }


    public void getFeedbackList4Course(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getDeptsList4() - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));


                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Feedback>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.FEEDBACK_MAP);
                                ArrayList<Feedback> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onAllFeedbackDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onAllFeedbackDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onAllFeedbackDataResponse(null, true, null);
                                        break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onAllFeedbackDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getFeedbackList4Professor(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getFeedbackList4Professor() - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Feedback>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.FEEDBACK_MAP);
                                ArrayList<Feedback> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onAllFeedbackDataResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onAllFeedbackDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onAllFeedbackDataResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "getFeedbackList4Professor() - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onAllFeedbackDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getCourseFeedback4User(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getDeptsList4() - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));


                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Feedback>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.FEEDBACK_MAP);
                                   ArrayList<Feedback> list = gson.fromJson(array.toString(), listType);
                                if(list.size()>0){

                                    Feedback fb = list.get(0);
                                    ResponseListener.getInstnace().onUserFeedbackDataResponse(fb, false, null);

                                }

                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onUserFeedbackDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onUserFeedbackDataResponse(null, true, null);
                                        break;
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onUserFeedbackDataResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }


    /*********************************************
     * ARJUN
     ******************************************/

    public void register(String url, String params) {

        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "register - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            User user = gson.fromJson(response.getJSONObject(Map.USER_MAP).toString(), User.class);
                                            ResponseListener.getInstnace().onRegisterResponse(user, false, null);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onRegisterResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onRegisterResponse(null, true, null);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onRegisterResponse(null, true, ResponseMessage.UNKNOWN_ERROR);
                                }


                            } catch (JSONException e) {
                                Log.d("ARJUN", "register() - onErrorResponse() : " + e.toString());
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "register() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onRegisterResponse(null, true, ResponseMessage.NETWORK_ERROR);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void updateProfile(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "update - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            User user = gson.fromJson(response.getJSONObject(Map.USER_MAP).toString(), User.class);
                                            ResponseListener.getInstnace().onUpdateProfileResponse(user, false, null);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onUpdateProfileResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onUpdateProfileResponse(null, true, null);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onUpdateProfileResponse(null, true, ResponseMessage.UNKNOWN_ERROR);
                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "update - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onUpdateProfileResponse(null, true, ResponseMessage.NETWORK_ERROR);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void verifyUser(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "register - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            User user = gson.fromJson(response.getJSONObject(Map.USER_MAP).toString(), User.class);
                                            ResponseListener.getInstnace().onVerifyUserResponse(user, false, null);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onVerifyUserResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onVerifyUserResponse(null, true, null);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onVerifyUserResponse(null, true, ResponseMessage.UNKNOWN_ERROR);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "register() - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onVerifyUserResponse(null, true, ResponseMessage.NETWORK_ERROR);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void changePassword(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "update - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            User user = gson.fromJson(response.getJSONObject(Map.USER_MAP).toString(), User.class);
                                            ResponseListener.getInstnace().onChangePasswordResponse(user, false, null);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onChangePasswordResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onChangePasswordResponse(null, true, null);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onChangePasswordResponse(null, true, ResponseMessage.UNKNOWN_ERROR);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "update - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onChangePasswordResponse(null, true, ResponseMessage.NETWORK_ERROR);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }


    public void getDeptList4Register(String url) {
        JsonObjectRequest request = null;

        request = new JsonObjectRequest(url, new JSONObject(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Department>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.DEPARTMENT_MAP);
                                ArrayList<Department> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onDepartmentRegisterResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onDepartmentRegisterResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onDepartmentRegisterResponse(null, true, null);
                                        break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onDepartmentRegisterResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }


    public void getDeptList4UserProfile(String url) {
        JsonObjectRequest request = null;

        request = new JsonObjectRequest(url, new JSONObject(),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Department>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.DEPARTMENT_MAP);
                                ArrayList<Department> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onDepartmentUpdateResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onDepartmentUpdateResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onDepartmentUpdateResponse(null, true, null);
                                        break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onDepartmentUpdateResponse(null, true, ResponseMessage.NETWORK_ERROR);
            }
        });
        mRequestQueue.add(request);
    }


    public void getUserData(String url, String param) {
        JsonObjectRequest request = null;
        Log.d("ARJUN", "JSOn Requester : " + param);
        try {
            request = new JsonObjectRequest(url, new JSONObject(param),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (isGetSuccess(response)) {
                                    User user = gson.fromJson(response.toString(), User.class);
                                    ResponseListener.getInstnace().onUserDataResponse(user, false, null);
                                } else {
                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onUserDataResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onUserDataResponse(null, true, null);
                                            break;
                                    }
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onUserDataResponse(null, true, ResponseMessage.NETWORK_ERROR);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void resetPassword(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                                VolleyLog.v("Response:%n %s", response.toString(4));

                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            User user = gson.fromJson(response.getJSONObject(Map.USER_MAP).toString(), User.class);
                                            ResponseListener.getInstnace().onResetPasswordResponse(user, false, null);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onResetPasswordResponse(null, true, data_error);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onResetPasswordResponse(null, true, null);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onResetPasswordResponse(null, true, ResponseMessage.UNKNOWN_ERROR);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                    ResponseListener.getInstnace().onResetPasswordResponse(null, true, ResponseMessage.NETWORK_ERROR);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }

    public void getMajorList4Home(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "DBMgr - onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Major>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.MAJOR_MAP);
                                ArrayList<Major> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onMajorDataForHomeResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onMajorDataForHomeResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onMajorDataForHomeResponse(null, true, null);
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onMajorDataForHomeResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    public void getFeedbackList4Home(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("ARJUN", "getFeedbackList4Home() onResponse() : " + response.toString());
                            VolleyLog.v("Response:%n %s", response.toString(4));

                            if (isGetSuccess(response)) {
                                Type listType = new TypeToken<ArrayList<Feedback>>() {
                                }.getType();
                                JSONArray array = response.getJSONArray(Map.FEEDBACK_MAP);
                                ArrayList<Feedback> list = gson.fromJson(array.toString(), listType);
                                ResponseListener.getInstnace().onFeedbackDataForHomeResponse(list, false, null);
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onMajorDataResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onMajorDataResponse(null, true, null);
                                        break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "getFeedbackList4Home() onErrorResponse() : " + error.toString());
                VolleyLog.e("Error: ", error.getMessage());
                ResponseListener.getInstnace().onFeedbackDataForHomeResponse(null, true, ResponseMessage.NETWORK_ERROR);

            }
        });
        mRequestQueue.add(request);
    }

    //************************** TO-DO *******************************************************

    public void getDiscipline(String url, HashMap<String, Integer> params) {
        JsonObjectRequest request = null;
        request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (isGetSuccess(response)) {
                                Discipline disp = gson.fromJson(response.toString(), Discipline.class);
                                //TO DO - add response listener
                            } else {
                                switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                    case Status.OK:
                                        User user = gson.fromJson(response.getJSONObject(Map.DISCIPLINE_MAP).toString(), User.class);
                                        ResponseListener.getInstnace().onLoginResponse(user, true, null);
                                        break;

                                    case Status.DATA_ERROR:
                                        String data_error = response.getString(ResponseTag.STATUS);
                                        ResponseListener.getInstnace().onLoginResponse(null, true, data_error);
                                        break;

                                    case Status.DB_ERROR:
                                        ResponseListener.getInstnace().onLoginResponse(null, true, null);
                                        break;
                                }
                            }
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                VolleyLog.e("Error: ", error.getMessage());

            }
        });
        mRequestQueue.add(request);
    }

    public void getProfessor(String url, HashMap<String, Integer> map) {
    }

    public void getCourse(String url, HashMap<String, Integer> map) {
    }

    public void getMajor(String url, HashMap<String, Integer> map) {
    }

    public void getDepartment(String url, HashMap<String, Integer> map) {
    }

    public void getFeedback(String url, HashMap<String, Integer> map) {
    }

    public void getUser(String url, HashMap<String, Integer> map) {
    }

    public void getUniversity(String url, HashMap<String, Integer> map) {
    }


    public static boolean isGetSuccess(JSONObject response) {
        return !response.has(ResponseTag.STATUS_CODE);
    }

    public void updateCourse(String url, String params) {
        JsonObjectRequest request = null;
        try {
            request = new JsonObjectRequest(url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (hasTag(response, ResponseTag.STATUS_CODE)) {

                                    switch (getStatusCode(response, ResponseTag.STATUS_CODE)) {
                                        case Status.OK:
                                            ResponseListener.getInstnace().onUpdateCourseResponse(null, false);
                                            break;

                                        case Status.DATA_ERROR:
                                            String data_error = response.getString(ResponseTag.STATUS);
                                            ResponseListener.getInstnace().onUpdateCourseResponse(data_error, true);
                                            break;

                                        case Status.DB_ERROR:
                                            ResponseListener.getInstnace().onUpdateCourseResponse(null, true);
                                            break;
                                    }
                                } else {
                                    ResponseListener.getInstnace().onUpdateCourseResponse(ResponseMessage.UNKNOWN_ERROR, true);
                                }
                                VolleyLog.v("Response:%n %s", response.toString(4));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ARJUN", "DBMgr - onErrorResponse() : " + error.getMessage());
                    VolleyLog.e("Error: ", error.getMessage());
                    ResponseListener.getInstnace().onUpdateCourseResponse(ResponseMessage.NETWORK_ERROR, true);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRequestQueue.add(request);
    }
}
