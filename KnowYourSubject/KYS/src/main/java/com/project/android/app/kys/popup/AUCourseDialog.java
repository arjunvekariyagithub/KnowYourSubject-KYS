package com.project.android.app.kys.popup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.project.android.app.kys.R;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.AddInfoController;
import com.project.android.app.kys.fragments.CourseFragment;
import com.project.android.app.kys.fragments.DepartmentFragment;
import com.project.android.app.kys.fragments.MajorFragment;
import com.project.android.app.kys.helper.Util;

public class AUCourseDialog extends DialogFragment {

    public View mView;
    public EditText mName;
    public EditText mCode;
    public EditText mInitials;
    public EditText mSummary;

    public MajorFragment mMajorFragment;
    public static AUCourseDialog mFragment;

    public static AUCourseDialog newInstance() {
        mFragment = new AUCourseDialog();
        return mFragment;
    }

    public void setParentFragment(MajorFragment fragment) {
        this.mMajorFragment = fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.activity_add_course, null);
        initViews(mView);

        builder.setView(mView)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {



                        if ((mName.getText() == null) || (mCode.getText() == null) || (mSummary.getText() == null) || (mInitials.getText() == null)) {
                            String msg = getResources().getString(R.string.all_field_must_be_filled);
                            Util.showLongToast(getActivity(), msg);
                        } else {
                            String name = mName.getText().toString();
                            String code = mCode.getText().toString();
                            String init = mInitials.getText().toString();
                            String summary = mSummary.getText().toString();
                            Integer majorId = mMajorFragment.mMajorID;

                            ResponseListener.getInstnace().setAddCourseListener(mFragment);
                            AddInfoController.createInstance(getActivity()).addCourse(majorId, name, code, init, summary);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AUCourseDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void initViews(View view) {
        mName = (EditText) view.findViewById(R.id.add_course_name);
        mCode = (EditText) view.findViewById(R.id.add_course_code);
        mInitials = (EditText) view.findViewById(R.id.add_course_initials);
        mSummary = (EditText) view.findViewById(R.id.add_course_summary);
    }

    public void onResponse(String response, boolean isError) {
        Util.showLongToast(KYSApplication.getAppContext(), response);
        mMajorFragment.onResume();
        //AUDisciplineDialog.this.getDialog().cancel();

    }
}
