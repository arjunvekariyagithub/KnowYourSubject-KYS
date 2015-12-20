package com.project.android.app.kys.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.ResetPasswordActivity;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.PasswordController;
import com.project.android.app.kys.helper.Constants;
import com.project.android.app.kys.helper.Util;


public class ForgetPasswordFragment extends Fragment {

    private View mForgetPassword_view;
    private static ForgetPasswordFragment mFragment;

    EditText et_email;
    Spinner sp_secQue;
    EditText et_answer;
    Button bt_sendCode;
    Button bt_resetPassword;
    EditText et_verificationCode;

    TextView tv_error;

    String mEmail;
    String mAnswer;
    String mSecQue;
    String mVerificationCode;

    public static ForgetPasswordFragment newInstance() {
        mFragment = new ForgetPasswordFragment();
        return mFragment;
    }

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mForgetPassword_view = inflater.inflate(R.layout.fragment_forget_password, null);

        et_email = (EditText) mForgetPassword_view.findViewById(R.id.forgetPSWD_et_registered_email);
        sp_secQue = (Spinner) mForgetPassword_view.findViewById(R.id.forgetPSWD_sp_securityQuestion);
        et_answer = (EditText) mForgetPassword_view.findViewById(R.id.forgetPSWD_et_answer);
        et_verificationCode = (EditText) mForgetPassword_view.findViewById(R.id.forgetPSWD_et_verfication_code);

        bt_sendCode = (Button) mForgetPassword_view.findViewById(R.id.forgetPSWD_bt_sendCode);
        bt_sendCode.setOnClickListener(mSendCodeListener);

        bt_resetPassword = (Button) mForgetPassword_view.findViewById(R.id.forgetPSWD_bt_resetPassword);
        bt_resetPassword.setOnClickListener(mResetPasswordListener);


        tv_error = (TextView) mForgetPassword_view.findViewById(R.id.forgetPSWD_tv_requiredField);
        ResponseListener.getInstnace().setForgetPasswordListenerListener(mFragment);

        return mForgetPassword_view;

    }

    View.OnClickListener mSendCodeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (et_email == null || et_answer == null)
                return;

            mEmail = et_email.getText().toString();
            mAnswer = et_answer.getText().toString();
            mSecQue = sp_secQue.getSelectedItem().toString();

            if (Util.isNull(mEmail) || Util.isNull(mAnswer)) {
                tv_error.setVisibility(View.VISIBLE);
                return;
            }

            Boolean result = PasswordController.createInstance(getActivity()).verifyUser(mEmail, mSecQue, mAnswer);
        }
    };

    View.OnClickListener mResetPasswordListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (et_email == null || et_answer == null || et_verificationCode == null)
                return;

            mEmail = et_email.getText().toString();
            mAnswer = et_answer.getText().toString();
            mSecQue = sp_secQue.getSelectedItem().toString();
            mVerificationCode = et_verificationCode.getText().toString();

            if (Util.isNull(mEmail) || Util.isNull(mAnswer) || Util.isNull(mVerificationCode)) {
                tv_error.setVisibility(View.VISIBLE);
                tv_error.setText(getActivity().getResources().getString(R.string.all_field_must_be_filled));
                return;
            }

            // TODO VERIFY CODE WITH DATABASE

            if (mVerificationCode.equalsIgnoreCase("123456")) {
                Intent intent_resetPSWD = new Intent(getActivity(), ResetPasswordActivity.class);
                startActivity(intent_resetPSWD);
            } else {
                tv_error.setVisibility(View.VISIBLE);
                tv_error.setText(getActivity().getResources().getString(R.string.mag_enter_correct_verif_code));
                return;
            }

        }
    };

    public void onVerifyUserResponse(User user, boolean isError, String error) {
        if (isError) {
            tv_error.setVisibility(View.VISIBLE);
            tv_error.setText(error);
        } else {
                // TODO SEND EMAIL FOR VERIFICATION CODE....
                bt_resetPassword.setEnabled(true);
        }
    }
}
