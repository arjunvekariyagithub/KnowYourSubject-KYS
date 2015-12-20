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
import com.project.android.app.kys.activities.MajorActivity;
import com.project.android.app.kys.adapters.FeedbackListAdapter;
import com.project.android.app.kys.adapters.MajorListAdapter;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Major;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.ViewInformationController;
import com.project.android.app.kys.helper.Constants;
import com.project.android.app.kys.preferences.KYSSharedPreferences;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private View mHome_view;
    private static HomeFragment mFragment;

    public RecyclerView mRecyclerView_majorList = null;
    public RecyclerView mRecyclerView_feedbackList = null;

    public LinearLayout mLoadingView_MajorList = null;
    public LinearLayout mLoadingView_FeedbackList = null;
    public TextView mErrorText_MajorList = null;
    public TextView mErrorText_FeedbackList = null;

    public RelativeLayout mErrorView_MajorList = null;
    public RelativeLayout mErrorView_FeedbackList = null;

    public FeedbackListAdapter mFeedbackListAdpater;
    public MajorListAdapter mMajorListAdapter;


    public ViewInformationController mViewInfoController;



    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        mFragment = new HomeFragment();
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
     }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        mViewInfoController = ViewInformationController.createInstance(getActivity());
        ResponseListener.getInstnace().setHomeListener(mFragment);

        showMajorLoadingView();
        showFeedbackLoadingView();

        mRecyclerView_majorList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView_majorList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                onMajorListItemClicked(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mRecyclerView_feedbackList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView_feedbackList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                onFeedbackListItemClicked(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        requestMajorListAdapter();
        requestFeedbackListAdapter();
    }

    private void requestFeedbackListAdapter() {
        if (mViewInfoController != null) {
            mViewInfoController.getFeebdbacksForHome(KYSSharedPreferences.getUserDataFromSharedPref().getUserID());
        }
    }

    private void requestMajorListAdapter() {
        if (mViewInfoController != null) {
           mViewInfoController.getMajorForHome(KYSSharedPreferences.getUserDataFromSharedPref().getDepartmentId());
        }
    }

    private void initViews(View view) {

        // MAJOR
        mRecyclerView_majorList = (RecyclerView)view.findViewById(R.id.home_majorlist_list_view);
        mRecyclerView_majorList.requestDisallowInterceptTouchEvent(true);
        mRecyclerView_majorList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView_majorList.requestDisallowInterceptTouchEvent(true);
        mErrorText_MajorList = (TextView) view.findViewById(R.id.home_majorlist_tv_error);
        mLoadingView_MajorList = (LinearLayout)view.findViewById(R.id.home_majorlist_loading_view);
        mErrorView_MajorList = (RelativeLayout)view.findViewById(R.id.home_majorlist_error_view);

        // FEEDBACK
        mRecyclerView_feedbackList = (RecyclerView)view.findViewById(R.id.home_feedbacklist_list_view);
        mRecyclerView_feedbackList.requestDisallowInterceptTouchEvent(true);
        mRecyclerView_feedbackList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView_feedbackList.requestDisallowInterceptTouchEvent(true);
        mErrorText_FeedbackList = (TextView) view.findViewById(R.id.home_feedbacklist_tv_error);
        mLoadingView_FeedbackList = (LinearLayout)view.findViewById(R.id.home_feedbacklist_loading_view);

        mErrorView_FeedbackList = (RelativeLayout)view.findViewById(R.id.home_feedbacklist_error_view);
    }

    private void showMajorRecyclerView() {
        if (mRecyclerView_majorList != null) {
            mRecyclerView_majorList.setVisibility(View.VISIBLE);
        }
    }
    private void hideMajorNoDataView() {
        hideMajorListErrorView();
        hideMajorLoadingView();
    }

    private void hideFeedbackNoDataView() {
        hideFeedbackListErrorView();
        hideFeedbackLoadingView();
    }

    private void hideFeedbackLoadingView(){
        if (mLoadingView_FeedbackList != null) {
            mLoadingView_FeedbackList.setVisibility(View.INVISIBLE);
        }
    }

    private void showFeedbackRecyclerView() {
        if (mRecyclerView_feedbackList != null) {
            mRecyclerView_feedbackList.setVisibility(View.VISIBLE);
        }
    }

    private void showMajorLoadingView() {
        if (mLoadingView_MajorList != null) {
            mLoadingView_MajorList.setVisibility(View.VISIBLE);
        }
        hideRecyclerViewMajorList();
        hideMajorListErrorView();
    }

    private void hideMajorLoadingView() {
        if (mLoadingView_MajorList != null) {
            mLoadingView_MajorList.setVisibility(View.INVISIBLE);
        }
    }

    private void showFeedbackLoadingView() {
        if (mLoadingView_FeedbackList != null) {
            mLoadingView_FeedbackList.setVisibility(View.VISIBLE);
        }
        hideRecyclerViewFeedbackList();
        hideFeedbackListErrorView();
    }

    private void hideRecyclerViewMajorList() {
        if (mRecyclerView_majorList != null) {
            mRecyclerView_majorList.setVisibility(View.INVISIBLE);
        }
    }

    private void hideRecyclerViewFeedbackList() {
        if (mRecyclerView_feedbackList != null) {
            mRecyclerView_feedbackList.setVisibility(View.INVISIBLE);
        }
    }

    private void showRecyclerViewMajorList() {
        if (mRecyclerView_majorList != null) {
            mRecyclerView_majorList.setVisibility(View.VISIBLE);
        }
    }

    private void showRecyclerViewFeedbackList() {
        if (mRecyclerView_feedbackList != null) {
            mRecyclerView_feedbackList.setVisibility(View.VISIBLE);
        }
    }

    private void hideMajorListErrorView() {
        if (mErrorView_MajorList != null) {
            mErrorView_MajorList.setVisibility(View.INVISIBLE);
        }
    }

    private void hideFeedbackListErrorView() {
        if (mErrorView_FeedbackList != null) {
            mErrorView_FeedbackList.setVisibility(View.INVISIBLE);
        }
    }

    private void showFeedbackListErrorView(String error) {
        if (mErrorView_FeedbackList != null) {
            mErrorView_FeedbackList.setVisibility(View.VISIBLE);
            mErrorText_FeedbackList.setText(error);
        }
    }

    private void showMajorListErrorView(String error) {
        if (mErrorView_MajorList != null) {
            mErrorView_MajorList.setVisibility(View.VISIBLE);
            mErrorText_MajorList.setText(error);
        }
    }


    private void onMajorListItemClicked(View view, int position) {
        if (view.getTag() == null) return;

        if (view.getTag() instanceof Major) {

            Intent intent = new Intent(getActivity(), MajorActivity.class);
            intent.putExtra(Constants.Tag.BusinessTag.MAJOR_ID, ((Major) view.getTag()).getMajorId());
            intent.putExtra(Constants.Tag.BusinessTag.MAJOR_NAME, ((Major) view.getTag()).getMajorName());
            getActivity().startActivity(intent);
        }
    }

    private void onFeedbackListItemClicked(View view, int position) {

    }

    public void onMajorListForHomeResponse(ArrayList<Major> majorList, boolean isError, String error) {
        if (isError) {
            showMajorListErrorView(error);
        } else {
            if(majorList == null) {
                showMajorListErrorView("No data");
            } else {
                setMajorAdapter(majorList);
            }
        }

    }

    private void setMajorAdapter(ArrayList<Major> majorList) {
        mMajorListAdapter = new MajorListAdapter(getActivity(), majorList);
        if (mRecyclerView_majorList != null && mMajorListAdapter != null && mMajorListAdapter.getItemCount() > 0 && isResumed()) {
            showMajorRecyclerView();
            hideMajorNoDataView();
            mRecyclerView_majorList.setAdapter(mMajorListAdapter);
        }
    }

    public void onFeedbackListForHomeResponse(ArrayList<Feedback> fbList, boolean isError, String error) {
        Log.d("ARJUN", "onFeedbackListForHomeResponse() : " + fbList);
        Log.d("ARJUN", "onFeedbackListForHomeResponse() : " + fbList.size());
        if (isError) {
            showFeedbackListErrorView(error);
        } else {
            if(fbList == null) {
                showFeedbackListErrorView("No data");
            } else {
                setFeedbackAdapter(fbList);
            }
        }

    }

    private void setFeedbackAdapter(ArrayList<Feedback> fbList) {
        mFeedbackListAdpater = new FeedbackListAdapter(getActivity(), fbList);
        if (mRecyclerView_feedbackList != null && mFeedbackListAdpater != null && mFeedbackListAdpater.getItemCount() > 0 && isResumed()) {
            showFeedbackRecyclerView();
            hideFeedbackNoDataView();
            mRecyclerView_feedbackList.setAdapter(mFeedbackListAdpater);
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


}
