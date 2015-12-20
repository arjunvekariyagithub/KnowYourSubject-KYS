package com.project.android.app.kys.controller;

import android.content.Context;

import com.project.android.app.kys.R;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.db.DBMgr;
import com.project.android.app.kys.helper.Constants;

public class PasswordController {

    private static Context mContext = null;

    public PasswordController(Context context) {
        mContext = context;
    }

    public static PasswordController createInstance(Context context) {
        return new PasswordController(context);
    }

    public String verify(User curUser, String currentPSWD, String newPSWD, String confirmPSWD) {
        // VERIFY CURRENT PASSWORD WITH USER

        User user = new User();
        user.setUserID(curUser.getUserID());
        user.setPassword(newPSWD);

        if (verifyCurrentPassword(curUser.getPassword(), currentPSWD)) {

            if (newPSWD.equalsIgnoreCase(confirmPSWD)) {
                DBMgr.getInstance().changePassword(user);
                return Constants.Result.PENDING;
            } else {
                return KYSApplication.getAppContext().getResources().getString(R.string.text_password_mismatch);
            }
        } else {
            return KYSApplication.getAppContext().getResources().getString(R.string.text_currentPassword_isWrong);
        }
    }

    private boolean verifyCurrentPassword(String originalPSWD, String currentPSWD) {

        if (originalPSWD.equals(currentPSWD))
            return true;
        else
            return false;
    }

    public Boolean resetPassword(String email, String newPassword) {

        User user = new User();
        user.setEmailId(email);
        user.setPassword(newPassword);
        DBMgr.getInstance().resetPassword(user);
        return null;
    }

    public Boolean verifyUser(String mEmail, String mSecQue, String mAnswer) {
        User user = new User();
        user.setEmailId(mEmail);
        user.setSecurityQuestion(mSecQue);
        user.setSecurityAnswer(mAnswer);
        DBMgr.getInstance().verifyUser(user);
        return null;
    }
}
