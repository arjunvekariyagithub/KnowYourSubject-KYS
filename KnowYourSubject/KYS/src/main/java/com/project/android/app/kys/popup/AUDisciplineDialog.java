package com.project.android.app.kys.popup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.project.android.app.kys.R;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.AddInfoController;
import com.project.android.app.kys.fragments.DisciplineFragment;
import com.project.android.app.kys.helper.Util;


public class AUDisciplineDialog extends DialogFragment {

    public View mView;
    public EditText mName;
    public EditText mInitials;
    public EditText mSummary;

    public DisciplineFragment mDisciplineFragment;
    public static AUDisciplineDialog mFragment;

    public static AUDisciplineDialog newInstance() {
        mFragment = new AUDisciplineDialog();
        return mFragment;
    }

    public void setParentFragment(DisciplineFragment fragment) {
        this.mDisciplineFragment = fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mView = inflater.inflate(R.layout.activity_add_discipline, null);
        initViews(mView);

        builder.setView(mView)
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {



                        if ((mName.getText() == null) || (mInitials.getText() == null) || (mSummary.getText() == null)) {
                            String msg = getResources().getString(R.string.all_field_must_be_filled);
                            Util.showLongToast(getActivity(), msg);
                        } else {
                            String name = mName.getText().toString();
                            String init = mInitials.getText().toString();
                            String summary = mSummary.getText().toString();
                            Integer univID = 1;

                            ResponseListener.getInstnace().setAddDisciplineListener(mFragment);
                            AddInfoController.createInstance(getActivity()).addDiscipline(univID ,name, init, summary);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AUDisciplineDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private void initViews(View view) {
        mName = (EditText) view.findViewById(R.id.add_disp_name);
        mInitials = (EditText) view.findViewById(R.id.add_disp_initials);
        mSummary = (EditText) view.findViewById(R.id.add_disp_summary);
    }

    public void onResponse(String response, boolean isError) {
        Util.showLongToast(KYSApplication.getAppContext(), response);
        //AUDisciplineDialog.this.getDialog().cancel();

        Log.d("test", "on Response");
    }
}

