package com.project.android.app.kys.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.android.app.kys.R;
import com.project.android.app.kys.fragments.CourseFragment;
import com.project.android.app.kys.fragments.MajorFragment;
import com.project.android.app.kys.helper.Constants.Tag.BusinessTag;
import com.project.android.app.kys.helper.Type;
import com.project.android.app.kys.popup.AUFeedbackDialog;
import com.project.android.app.kys.popup.AUProfessorDialog;

public class CourseActivity extends AppCompatActivity {

    private CourseFragment mCourseFragment;
    private Toolbar mToolbar = null;
    public String mCourseName;
    public String mCourseInitials;
    public String mCourseCode;
    public String mCourseSummary;
    public Integer mCourseID;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course);


        mContext = this;

        Intent intent = getIntent();
        if (intent != null) {
            mCourseID = intent.getIntExtra(BusinessTag.COURSE_ID, -1);
            mCourseName = intent.getStringExtra(BusinessTag.COURSE_NAME);
            mCourseSummary = intent.getStringExtra(BusinessTag.COURSE_SUMMARY);
            mCourseCode = intent.getStringExtra(BusinessTag.COURSE_CODE);
            mCourseInitials = intent.getStringExtra(BusinessTag.COURSE_INITIALS);
        }

        setupView();
    }

    private void setupView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mCourseName != null)
            setTitle(mCourseName);

        mCourseFragment = CourseFragment.newInstance(mCourseID, mCourseName, mCourseSummary, mCourseCode, mCourseInitials);
        getFragmentManager().beginTransaction().replace(R.id.content, mCourseFragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        if(Type.UserType.isAdmin()) {
            menu.findItem(R.id.action_add).setVisible(true);
        } else {
            menu.findItem(R.id.action_add).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_add:
                if (mCourseFragment != null) {
                    mCourseFragment.handleLinkProfessor();
                }
                break;

            case R.id.action_search:

                break;

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}