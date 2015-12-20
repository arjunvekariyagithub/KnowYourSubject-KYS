package com.project.android.app.kys.controller;

import android.content.Context;

import com.project.android.app.kys.R;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.db.DBMgr;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.helper.Constants.Result;

public class UserController {

  public static String login(String email_id, String pass) {

      //TO DO - CHANGE THIS DUMMY CODE
      String result = null;
      if(!Util.validate(email_id)) {
          result = Result.FAIL;
          return result;
      } else {
          User user = new User();
          user.setEmailId(email_id);
          user.setPassword(pass);
          DBMgr.getInstance().login(user);
          result = Result.PENDING;
      }
      return result;
  }

    /******************************** POOJA ***********************************/

    private static Context mContext = null;

    public UserController(Context context) {
        mContext = context;

    }

    public static UserController createInstance(Context context) {
        return new UserController(context);
    }

    public boolean register(String firstname,String lastname,String email, String password, String DOB,String secQue,String answer,int deptId, String contact,String zipcode) {

        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmailId(email);
        user.setPassword(password);
        user.setDob(DOB);
        user.setSecurityQuestion(secQue);
        user.setSecurityAnswer(answer);
        user.setDepartmentId(deptId);
        user.setContact(contact);
        user.setIsAdmin(false);
        user.setZipCode(zipcode);

        DBMgr.getInstance().register(user);
        return true;
    }

    public boolean updateProfile(Integer userID, String password, String firstname,String lastname,String email, String DOB,String secQue,String answer,int deptId, String contact,String zipcode) {

        User user = new User();
        user.setUserID(userID);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmailId(email);
        user.setDob(DOB);
        user.setSecurityQuestion(secQue);
        user.setSecurityAnswer(answer);
        user.setDepartmentId(deptId);
        user.setContact(contact);
        user.setZipCode(zipcode);

        DBMgr.getInstance().updateProfile(user);
        return true;

    }
    /******************************** POOJA ***********************************/

}
