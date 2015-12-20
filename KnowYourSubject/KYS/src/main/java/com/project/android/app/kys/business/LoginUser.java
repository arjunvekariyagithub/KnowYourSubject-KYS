package com.project.android.app.kys.business;

import com.project.android.app.kys.helper.Constants;
import com.project.android.app.kys.helper.Type.UserType;

public class LoginUser extends User {
    public int getUserType() {
        return UserType.LOGIN;
    }
}
