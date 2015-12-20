package com.project.android.app.kys.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.project.android.app.kys.R;
import com.project.android.app.kys.fragments.ChangePasswordFragment;

public class ChangePasswordActivity extends AppCompatActivity {

    private ChangePasswordFragment mChangePasswordFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setupView();
    }

    private void setupView() {
        mChangePasswordFragment = ChangePasswordFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.changePSWD_main, mChangePasswordFragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
