package com.project.android.app.kys.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.ChangePasswordActivity;
import com.project.android.app.kys.activities.LoginActivity;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.PasswordController;
import com.project.android.app.kys.helper.Constants;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.preferences.KYSSharedPreferences;


public class ChangePasswordFragment extends Fragment {

    private View mChangePSWD_View;
    private static ChangePasswordFragment mFragment;

    private User mUser;

    EditText et_current_pswd;
    EditText et_new_pswd;
    EditText et_confirm_pswd;

    Button bt_pswd_change;

    private String mCurrent_Pswd;
    private String mNew_Pswd;
    private String mConfirmation_Pswd;

    TextView tv_password_Mismatch_Error;
    TextView tv_requiredField;

    Intent and_myLocalIntent;
    Bundle and_myBundle;

    private String Original_CurrentPSWD;


    public ChangePasswordFragment() {
        mUser = KYSSharedPreferences.getUserDataFromSharedPref();
    }

    public static ChangePasswordFragment newInstance() {
        mFragment = new ChangePasswordFragment();
        return mFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mChangePSWD_View = inflater.inflate(R.layout.fragment_change_password, null);

        and_myLocalIntent = getActivity().getIntent();
        and_myBundle = and_myLocalIntent.getExtras();

        //Original_CurrentPSWD = and_myBundle.getString("CURR_PSWD");

        et_current_pswd = (EditText)mChangePSWD_View.findViewById(R.id.et_current_pswd);
        et_new_pswd = (EditText)mChangePSWD_View.findViewById(R.id.et_new_pswd);
        et_confirm_pswd = (EditText)mChangePSWD_View.findViewById(R.id.et_confirm_pswd);

        bt_pswd_change = (Button)mChangePSWD_View.findViewById(R.id.bt_change_pswd);
        bt_pswd_change.setOnClickListener(mChangePasswordListener);

        tv_requiredField = (TextView)mChangePSWD_View.findViewById(R.id.changePSWD_tv_error);
        tv_password_Mismatch_Error = (TextView)mChangePSWD_View.findViewById(R.id.changePSWD_tv_pass_error);


        ResponseListener.getInstnace().setChangePasswordListener(mFragment);

        return mChangePSWD_View;

    }

    View.OnClickListener mChangePasswordListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mCurrent_Pswd = et_current_pswd.getText().toString();
            mNew_Pswd = et_new_pswd.getText().toString();
            mConfirmation_Pswd = et_confirm_pswd.getText().toString();

            if(Util.isNull(mCurrent_Pswd) || Util.isNull(mNew_Pswd) || Util.isNull(mConfirmation_Pswd))
            {
                tv_password_Mismatch_Error.setVisibility(View.GONE);
                tv_requiredField.setVisibility(View.VISIBLE);
                return;
            }

            if(!mNew_Pswd.equals(mConfirmation_Pswd))
            {
                tv_requiredField.setVisibility(View.GONE);
                tv_password_Mismatch_Error.setVisibility(View.VISIBLE);
                return;
            }


            String result = PasswordController.createInstance(getActivity()).verify(mUser, mCurrent_Pswd, mNew_Pswd, mConfirmation_Pswd);
            if (!result.equals(Constants.Result.PENDING)) {
                tv_requiredField.setText(result);
                tv_requiredField.setVisibility(View.VISIBLE);
            }

        }
    };


    public void onResponse(User user, boolean isError, String error) {
        if (isError) {
            tv_requiredField.setVisibility(View.VISIBLE);
            tv_requiredField.setText(error);
        } else {
                Util.showLongToast(getActivity(), getResources().getString(R.string.msg_change_password_success));
                Intent intent_Register = new Intent(getActivity(), LoginActivity.class);
                intent_Register.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_Register);
                ((ChangePasswordActivity)getActivity()).finish();
        }
    }


}
