package com.project.android.app.kys.preferences;


import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.helper.Constants.Tag.SharedPrefTag;

public class KYSSharedPreferences {

    public static void setUserDataToSharedPref(User user) {
        Log.d("ARJUN", "KYSSharedPreferences set - deptID = " + user.getDepartmentId());
        Editor prefsEditor = KYSApplication.getAppSharedPref().edit();
        Gson gson = new Gson();
        String json = gson.toJson(user); // myObject - instance of MyObject
        prefsEditor.putString(SharedPrefTag.USER, json);
        prefsEditor.commit();
    }

    public static User getUserDataFromSharedPref() {

        Gson gson = new Gson();
        String json = KYSApplication.getAppSharedPref().getString(SharedPrefTag.USER, "");
        Log.d("ARJUN", "KYSSharedPreferences get - deptID = " + gson.fromJson(json, User.class).getDepartmentId());
        return gson.fromJson(json, User.class);
    }

    public static void LogoutUser() {
;
        Editor prefsEditor = KYSApplication.getAppSharedPref().edit();
        prefsEditor.putString(SharedPrefTag.USER, null);
        prefsEditor.commit();
    }

    public static void invalidatePassword() {
        User user = getUserDataFromSharedPref();
        user.setPassword(null);

        setUserDataToSharedPref(user);
    }
}
