package com.project.android.app.kys.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.ForgetPasswordActivity;
import com.project.android.app.kys.activities.HomeActivity;
import com.project.android.app.kys.activities.RegistrationActivity;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.UserController;
import com.project.android.app.kys.helper.Constants.Result;
import com.project.android.app.kys.helper.Constants.Tag.Choice;
import com.project.android.app.kys.helper.Constants.Tag.ChoiceTag;
import com.project.android.app.kys.helper.Type.UserType;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.preferences.KYSSharedPreferences;

public class LoginFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText et_userID;
    EditText et_password;
    Button bt_login;
    Button bt_signup;
    Button bt_guestuser;
    TextView tv_forgotpassword;
    TextView tv_error;

    private View mLogin_view;
    private static LoginFragment mFragment;
    private String mEmailID;
    private String mPass;

    public static LoginFragment newInstance() {
        mFragment = new LoginFragment();
        return mFragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Util.showLongToast(getActivity(), "Latest code");




        mLogin_view = inflater.inflate(R.layout.fragment_login, null);

        bt_signup = (Button) mLogin_view.findViewById(R.id.login_bt_signup);
        bt_signup.setOnClickListener(mSignupClickListener);

        bt_login = (Button) mLogin_view.findViewById(R.id.login_bt_login);
        bt_login.setOnClickListener(mLoginClickListener);

        bt_guestuser = (Button) mLogin_view.findViewById(R.id.login_bt_guest_user);
        bt_guestuser.setOnClickListener(mGuestClickListener);


        tv_forgotpassword = (TextView) mLogin_view.findViewById(R.id.login_tv_forgetpassword);
        tv_forgotpassword.setOnClickListener(mForgotPasswordClickListener);

        tv_error = (TextView) mLogin_view.findViewById(R.id.login_tv_error);

        et_userID = (EditText) mLogin_view.findViewById(R.id.login_et_username);
        et_password = (EditText) mLogin_view.findViewById(R.id.login_et_password);

        ResponseListener.getInstnace().setLoginResponseListener(mFragment);

        return mLogin_view;

    }

    public View.OnClickListener mSignupClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent_Register = new Intent(getActivity(), RegistrationActivity.class);
            Bundle bundle_register = new Bundle();
            bundle_register.putInt(ChoiceTag.SOURCE, Choice.FROM_SIGN_UP);
            intent_Register.putExtras(bundle_register);
            startActivity(intent_Register);
        }
    };

    public View.OnClickListener mLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (et_userID == null || et_password == null) return;

            mEmailID = et_userID.getText().toString();
            mPass = et_password.getText().toString();

            if (Util.isNull(mEmailID) || Util.isNull(mPass)) {
                if (tv_error != null) {
                    showError(getResources().getString(R.string.all_field_must_be_filled));
                }
                return;
            }

            String result = UserController.login(mEmailID, mPass);
            if (result.equals(Result.FAIL)) {
                showError(getResources().getString(R.string.invalid_uname_pass));
            }
        }
    };


    public View.OnClickListener mGuestClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserType.setUserType(UserType.GUEST);
            Intent intent_Register = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent_Register);
        }
    };

    private void showError(String error_msg) {
        if (tv_error != null) {
            tv_error.setText(error_msg);
            tv_error.setVisibility(View.VISIBLE);
        }
    }

    public View.OnClickListener mForgotPasswordClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent_Register = new Intent(getActivity(), ForgetPasswordActivity.class);
            startActivity(intent_Register);
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onResponse(User user, boolean isError, String error) {
//        if (isError) {
//
//        } else {
//                int userType = UserType.ADMIN;
//                UserType.setUserType(userType);
//                Intent intent_Register = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent_Register);
//            }

        if (isError) {
            showError(error);
        } else {
            Log.d("ARJUN", "Login() - user = " + user.toString());
            Log.d("ARJUN", "Login() - deptID = " + user.getDepartmentId());
            KYSSharedPreferences.setUserDataToSharedPref(user);

            int userType;
            if (user.getIsAdmin()) userType = UserType.ADMIN;
            else userType = UserType.ADMIN;

            UserType.setUserType(userType);

            Intent intent_Register = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent_Register);
        }


    }


}
