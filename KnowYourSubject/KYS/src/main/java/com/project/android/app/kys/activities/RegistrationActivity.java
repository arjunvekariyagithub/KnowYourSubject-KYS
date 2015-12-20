package com.project.android.app.kys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.project.android.app.kys.R;
import com.project.android.app.kys.fragments.RegistrationFragment;
import com.project.android.app.kys.fragments.UpdateProfileFragment;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationFragment mRegistrationFragment;
    private UpdateProfileFragment mUpdateProfileFragment;

    private final int SOURCE_REGISTER = 0;
    private final int SOURCE_UPDATEPROFILE = 1;

    Intent and_myLocalIntent;
    Bundle and_myBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        and_myLocalIntent = getIntent();
        and_myBundle = and_myLocalIntent.getExtras();

        setupView();


        //after registration
        //launchVarifyEmail();
    }

    private void setupView() {

        if(and_myBundle != null && and_myBundle.getInt("SOURCE") == SOURCE_REGISTER) {

            mRegistrationFragment = RegistrationFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.registration_main, mRegistrationFragment).commitAllowingStateLoss();
        }
        else {
            mUpdateProfileFragment = UpdateProfileFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.registration_main, mUpdateProfileFragment).commitAllowingStateLoss();
        }
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


    public void launchVarifyEmail() {
        startActivity(new Intent(RegistrationActivity.this, EmailVarificationActivity.class));
    }
}
