package com.project.android.app.kys.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.DepartmentActivity;
import com.project.android.app.kys.activities.HomeActivity;
import com.project.android.app.kys.adapters.DepartmentListAdapter;
import com.project.android.app.kys.business.Department;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.ViewInformationController;
import com.project.android.app.kys.helper.Constants.Tag.BusinessTag;

import java.util.ArrayList;

public class DisciplineFragment extends Fragment {

    public RecyclerView mRecyclerView = null;
    public ViewInformationController mViewInfoController;
    static DisciplineFragment fragment;
    public DepartmentListAdapter mAdapter;
    public LinearLayout mLoadingView;
    public RelativeLayout mErrorView;
    public TextView mErrorTextView;
    public RelativeLayout mListHeaderView;
    public LinearLayout mListLayout;

    public String mDisciplineName;
    public int mDisciplinID;

    public int flag = 1;



    public DisciplineFragment() {
        // Required empty public constructor
    }

    public static DisciplineFragment newInstance() {
        fragment = new DisciplineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDisciplinID = getArguments().getInt(BusinessTag.DISCP_ID);
            mDisciplineName = getArguments().getString(BusinessTag.DISP_NAME);
        }
        setTitle(mDisciplineName);

        mViewInfoController = ViewInformationController.createInstance(getActivity());
    }

    private void setTitle(String disciplineName) {
        if(disciplineName != null) {
            ((HomeActivity)getActivity()).getSupportActionBar().setTitle(disciplineName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discipline, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ARJUN", "DisciplineFragment() - onViewCreated()");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.disp_frag_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mListLayout = (LinearLayout) view.findViewById(R.id.disp_frag_list_layout);
        mListHeaderView = (RelativeLayout) view.findViewById(R.id.disp_frag_list_header);
        mLoadingView = (LinearLayout) view.findViewById(R.id.disp_frag_loading_view);
        mErrorView = (RelativeLayout) view.findViewById(R.id.disp_frag_error_view);
        mErrorTextView = (TextView) view.findViewById(R.id.disp_frag_tv_error);

        showLoadingView();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                onListItemClicked(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("testDSP","Discipline onResume");
        requesAdapter();
    }




    private void showLoadingView() {
        if(mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        }

        hideRecyclerView();
        hideErrorView();
    }

    private void hideLoadingView() {
        if(mLoadingView != null) {
            mLoadingView.setVisibility(View.INVISIBLE);
        }
    }

    private void showErrorView(String errorMsg) {
        if(mErrorView != null && mErrorTextView != null){
            mErrorView.setVisibility(View.VISIBLE);
            mErrorTextView.setText(errorMsg);

            hideRecyclerView();
            hideLoadingView();
        }
    }

    public void showRecyclerView() {
        if (mRecyclerView != null) {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    public void hideRecyclerView() {
        if (mRecyclerView != null) {
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    private void hideErrorView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.INVISIBLE);
        }
    }

    private void showHeaderView() {
        if(mListHeaderView != null) {
            mListHeaderView.setVisibility(View.VISIBLE);
        }
    }

    private void hideNoDataView() {
        hideLoadingView();
        hideErrorView();
    }

    private void onListItemClicked(View view, int position) {
        if(view.getTag() == null) return;

       if (view.getTag() instanceof Department) {

           Intent intent = new Intent(getActivity(), DepartmentActivity.class);
           intent.putExtra(BusinessTag.DEPT_ID, ((Department)view.getTag()).getDepartmentId());
           intent.putExtra(BusinessTag.DEPT_NAME, ((Department) view.getTag()).getDepartmentName());
           getActivity().startActivity(intent);
       }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void requesAdapter() {
        if(mViewInfoController != null){
            ResponseListener.getInstnace().setDepartmentDataListener(fragment);
            mViewInfoController.getDepartments(mDisciplinID);
        }
    }

    public void onResponse(ArrayList<Department> deptList, boolean isError, String error) {
        Log.d("test", "DisciplineFragment() - onResponse()"+isError);
        if (isError) {
            hideLoadingView();
            showErrorView("No data");
        } else {
            if(deptList == null || deptList.size() <= 0) {
                hideLoadingView();
                showErrorView("No data");
            } else {
                setAdapter(deptList);
            }
        }
        //setAdapter(deptList);
    }

    private void setAdapter(ArrayList<Department> deptList) {
       /* ArrayList<Department> dummyDeptList = new ArrayList<Department>();
        Department dept1 = new Department();
        Department dept2 = new Department();
        Department dept3 = new Department();
        Department dept4 = new Department();

        flag = 1 - flag;

        if(flag == 0) {

            dept1.setDepartmentId(1);
            dept1.setDisciplineId(1);
            dept1.setDepartmentName("Computer Science & Engineering");
            dept1.setDepartmentInitials("CS");
            dept1.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept1);


            dept2.setDepartmentId(2);
            dept2.setDisciplineId(2);
            dept2.setDepartmentName("Civil Engineering");
            dept2.setDepartmentInitials("CL");
            dept2.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept2);


            dept3.setDepartmentId(3);
            dept3.setDisciplineId(1);
            dept3.setDepartmentName("Electrical Engineering");
            dept3.setDepartmentInitials("EE");
            dept3.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept3);


            dept4.setDepartmentId(4);
            dept4.setDisciplineId(1);
            dept4.setDepartmentName("Mechanical Engineering");
            dept4.setDepartmentInitials("ME");
            dept4.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept4);
        } else {

            dept4.setDepartmentId(4);
            dept4.setDisciplineId(1);
            dept4.setDepartmentName("Mechanical Engineering");
            dept4.setDepartmentInitials("ME");
            dept4.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept4);

            dept2.setDepartmentId(2);
            dept2.setDisciplineId(2);
            dept2.setDepartmentName("Civil Engineering");
            dept2.setDepartmentInitials("CL");
            dept2.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept2);


            dept3.setDepartmentId(3);
            dept3.setDisciplineId(1);
            dept3.setDepartmentName("Electrical Engineering");
            dept3.setDepartmentInitials("EE");
            dept3.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept3);

            dept1.setDepartmentId(1);
            dept1.setDisciplineId(1);
            dept1.setDepartmentName("Computer Science & Engineering");
            dept1.setDepartmentInitials("CS");
            dept1.setDepartmentSummary("CS is a evergreen branch of science.");
            dummyDeptList.add(dept1);

        }*/




        mAdapter = new DepartmentListAdapter(getActivity(), deptList);
        Log.d("ARJUN", "DisciplineFragment() - isResumed() : " + isResumed());
        Log.d("ARJUN", "DisciplineFragment() - mRecyclerView : " + mRecyclerView);
        Log.d("ARJUN", "DisciplineFragment() - mAdapter : " + mAdapter);
        if(mRecyclerView != null && mAdapter != null && mAdapter.getItemCount() > 0) {
            Log.d("ARJUN", "DisciplineFragment() - setting adapter for listview...");
            showRecyclerView();
            hideNoDataView();
            showHeaderView();
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }

    public void changeDiscipline(Integer dispID, String dispName) {
        mDisciplinID = dispID;
        mDisciplineName = dispName;
        setTitle(mDisciplineName);
        showLoadingView();
        requesAdapter();
    }

}