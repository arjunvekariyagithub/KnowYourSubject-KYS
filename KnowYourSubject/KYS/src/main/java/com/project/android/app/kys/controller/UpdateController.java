package com.project.android.app.kys.controller;

import android.content.Context;

import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.db.DBMgr;

/**
 * Created by arjun on 12/2/15.
 */
public class UpdateController {
    private static Context mContext = null;

    public UpdateController(Context context) {
        mContext = context;

    }

    public static UpdateController createInstance(Context context) {
        return new UpdateController(context);
    }

    public void updateCourse(Course course) {
        DBMgr.getInstance().updateCourse(course);
    }



}



