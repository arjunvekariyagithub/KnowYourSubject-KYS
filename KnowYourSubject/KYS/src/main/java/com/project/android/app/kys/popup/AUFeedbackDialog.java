package com.project.android.app.kys.popup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.project.android.app.kys.R;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.AddInfoController;
import com.project.android.app.kys.fragments.CourseFragment;
import com.project.android.app.kys.helper.Constants.Pattern;
import com.project.android.app.kys.helper.Constants.Tag.BusinessTag;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.preferences.KYSSharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AUFeedbackDialog extends DialogFragment {

    View mView;


    Integer userId;
    Integer courseId;
    Integer professorId;
    String userName;
    String courseName;
    String professorName;
    EditText mTitle;
    EditText mComment;
    RatingBar mRatingBar;
    String date;
    CheckBox mIsAnonymousCheck;
    Spinner mProfSpinner;

    public CourseFragment mCourseFragment;
    ArrayList<String> mProfNameArray;
    public static AUFeedbackDialog mFragment;

    public static AUFeedbackDialog newInstance(int courseId, String courseName) {
        mFragment = new AUFeedbackDialog();
        Bundle args = new Bundle();
        args.putInt(BusinessTag.COURSE_ID, courseId);
        args.putString(BusinessTag.COURSE_NAME, courseName);
        mFragment.setArguments(args);

        return mFragment;
    }


    public void setParentFragment(CourseFragment fragment) {
        this.mCourseFragment = fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.addfeedback, null);
        initViews(mView);


        courseId = getArguments().getInt(BusinessTag.COURSE_ID);
        courseName = getArguments().getString(BusinessTag.COURSE_NAME);

        mProfNameArray = mCourseFragment.getCourseProfessorNameList();

        if (mProfSpinner != null && mProfNameArray.size() > 0) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, mProfNameArray);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            mProfSpinner.setAdapter(dataAdapter);
        }

        builder.setView(mView)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        DateFormat dateFormat = new SimpleDateFormat(Pattern.DATE_PATTERN);
                        Date curdate = new Date();
                        date = dateFormat.format(curdate);
                        String title = null;
                        String comment = null;

                        if(mTitle.getText() != null)
                             title = mTitle.getText().toString();

                        if(mComment.getText() != null)
                            comment = mComment.getText().toString();

                        Float ratings = mRatingBar.getRating();
                        Boolean isAnonymous = mIsAnonymousCheck.isChecked();

                       /* Integer profPosition = mProfSpinner.getSelectedItemPosition();
                        professorId = 1;
                        professorName = "John Robb";*/

                        Integer profPosition = mProfSpinner.getSelectedItemPosition();
                        professorId = mCourseFragment.getCourseProfIDAt(profPosition);
                        professorName = (String) mProfSpinner.getSelectedItem();



                        //courseId = 3;
                        //courseName = "Software Engineering: Analysis, Design, and Testing";
                        //courseId = bundle.getInt(BusinessTag.COURSE_ID);
                        //courseName = bundle.getString(BusinessTag.COURSE_NAME);

                        userId = KYSSharedPreferences.getUserDataFromSharedPref().getUserID();
                        userName = KYSSharedPreferences.getUserDataFromSharedPref().getFirstName() + KYSSharedPreferences.getUserDataFromSharedPref().getLastName();

                        ResponseListener.getInstnace().setAddFeedbackListener(mFragment);
                        AddInfoController.createInstance(getActivity()).addFeedback(userId, courseId, professorId, userName, courseName,
                                professorName, title, comment, ratings, date, isAnonymous);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AUFeedbackDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void initViews(View view) {
        mTitle = (EditText) view.findViewById(R.id.add_feedback_title);
        mComment = (EditText) view.findViewById(R.id.add_feedback_comment);
        mRatingBar = (RatingBar) view.findViewById(R.id.add_feedback_ratingbar);
        mIsAnonymousCheck = (CheckBox) view.findViewById(R.id.add_feedback_cb_anonymous);
        mProfSpinner = (Spinner) view.findViewById(R.id.add_feedback_spinner_professor);

      }

    public void onResponse(String response, boolean isError) {
        try
        {
            Util.showLongToast(KYSApplication.getAppContext(), response);
            mCourseFragment.onResume();
            Log.d("test", "on response");
        }
        catch (Exception E)
        {
            Log.d("test", "on response"+E);
        }
        //AUDisciplineDialog.this.getDialog().cancel();
    }
}
