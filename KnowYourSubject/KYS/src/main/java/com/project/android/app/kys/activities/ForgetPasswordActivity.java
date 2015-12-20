package com.project.android.app.kys.activities;

import android.os.Bundle;

import com.project.android.app.kys.R;
import com.project.android.app.kys.fragments.ForgetPasswordFragment;

public class ForgetPasswordActivity extends AbstractActivity {

    private ForgetPasswordFragment mForgetPasswordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        setupView();
    }

    private void setupView() {
        mForgetPasswordFragment = ForgetPasswordFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.forgetpassword_main, mForgetPasswordFragment).commitAllowingStateLoss();
    }

}
