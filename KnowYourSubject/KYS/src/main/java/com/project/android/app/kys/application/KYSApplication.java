package com.project.android.app.kys.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.project.android.app.kys.db.DBMgr;

/**
 * Created by arjun on 11/21/15.
 */
public class KYSApplication extends Application{
    public static Context mContext = null;
    public static SharedPreferences mSharedPref;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DBMgr.createInstance(mContext);
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static SharedPreferences getAppSharedPref()
    {
        return mSharedPref;
    }
}
