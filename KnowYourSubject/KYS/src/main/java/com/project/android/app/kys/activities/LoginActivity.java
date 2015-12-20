package com.project.android.app.kys.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.android.app.kys.R;
import com.project.android.app.kys.fragments.LoginFragment;
import com.project.android.app.kys.fragments.VerifyEmailFragment;

public class LoginActivity extends AbstractActivity {

    private LoginFragment mLoginFragment;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        setupView();
    }

    private void setupView() {
        mLoginFragment = LoginFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.login_main, mLoginFragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
