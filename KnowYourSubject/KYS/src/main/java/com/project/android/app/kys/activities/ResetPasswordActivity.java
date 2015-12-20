package com.project.android.app.kys.activities;

import android.os.Bundle;

import com.project.android.app.kys.R;
import com.project.android.app.kys.fragments.ResetPasswordFragment;

public class ResetPasswordActivity extends AbstractActivity {

    private ResetPasswordFragment mResetPasswordFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        setupView();

    }

    private void setupView() {
        mResetPasswordFragment = ResetPasswordFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.resetPSWD_main, mResetPasswordFragment).commitAllowingStateLoss();
    }

}
