package com.project.android.app.kys.json;

import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.callbacks.Callbacks;
import com.project.android.app.kys.callbacks.ResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    //add methods here to parse server response and create application specific data

    public static ArrayList<Department> parseDepartmentList(JSONObject response) throws JSONException {
        ArrayList<Department> deptList = new ArrayList<Department>();

        JSONArray deptArray = response.getJSONArray("departmentMap");

        
        for(int i=0; i < deptArray.length(); i++) {
          JSONObject currentDept = deptArray.getJSONObject(i);
          String deptId = currentDept.getString("mDepartmentId");
          String deptName = currentDept.getString("mDepartmentName");
          String dispId = currentDept.getString("mDepartmentId");
          String deptInitials = currentDept.getString("mDepartmentId");
          String deptSummary = currentDept.getString("mDepartmentId");
        }

        //Check commit

        return deptList;

    }

    public static void parseLoginResponse(JSONObject response) {
        String result = null;
        try {
            result = response.getString("ERR_MSG");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResponseListener.getInstnace().onLoginResponse(null, false, null);
    }
}
