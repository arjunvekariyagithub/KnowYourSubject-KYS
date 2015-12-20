package com.project.android.app.kys.fragments;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.CourseActivity;
import com.project.android.app.kys.adapters.FeedbackListAdapter;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.Course;
import com.project.android.app.kys.business.Feedback;
import com.project.android.app.kys.business.Professor;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.UpdateController;
import com.project.android.app.kys.controller.ViewInformationController;
import com.project.android.app.kys.helper.Constants;
import com.project.android.app.kys.helper.Constants.Tag.BusinessTag;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.popup.AUFeedbackDialog;
import com.project.android.app.kys.preferences.KYSSharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseFragment extends Fragment {

    public RecyclerView mRecyclerView = null;
    public ViewInformationController mViewInfoController;
    static CourseFragment mFragment;
    public FeedbackListAdapter mAdapter;

    public CardView mHeaderCardView;
    public TextView mHeaderCourseTitle;
    public TextView mHeaderCourseIntials;
    public TextView mHeaderCourseCode;
    public TextView mHeaderCourseSummary;

    public CardView mOverallRatingsCardView;
    public RatingBar mOverallRatingBar;
    public TextView mFeedbackCountView;

    public CardView mUserFeedbackCardView;
    public TextView mUserFeedbackUserName;
    public TextView mUserFeedbackUserIntials;
    public TextView mUserFeedDate;
    public TextView mUserFeedbackTitle;
    public TextView mUserFeedbackComment;
    public TextView mUserFeedbackProfessorName;
    public TextView mUserFeedbackCourseName;
    public RatingBar mUserFeedbackRatingBar;
    public LinearLayout mUserFeedbackLoadingView;
    public RelativeLayout mUserFeedbackErrorView;
    public TextView mUserFeedbackErrorText;


    public CardView mAllFeedbackCardView;
    public LinearLayout mAllFeedbackLoadingView;
    public RelativeLayout mAllFeedbackErrorView;
    public TextView mAllFeedbackErrorText;
    public Spinner mSortOptinSpinner;
    public Spinner mPorfNameFilterSpinner;

    public String mCourseName;
    public String mCourseCode;
    public String mCourseInitials;
    public String mCourseSummary;
    public Integer mCourseID;
    public Integer mUserID;


    private ArrayList<Professor> mCourseProfList;
    private ArrayList<Professor> mAllProfList;
    private String[] mAllProfNameList;
    private Feedback mUserFeedback;

    private int mCourseProfLinkSelectedPosition;
    ArrayList<String> profNameArray;


    public FloatingActionButton mAddFeedbackButton;


    public CourseFragment() {
        // Required empty public constructor
    }

    public static CourseFragment newInstance(Integer courseID, String courseName, String courseSummary, String courseCode, String courseInitials) {
        mFragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putInt(BusinessTag.COURSE_ID, courseID);
        args.putString(BusinessTag.COURSE_NAME, courseName);
        args.putString(BusinessTag.COURSE_SUMMARY, courseSummary);
        args.putString(BusinessTag.COURSE_CODE, courseCode);
        args.putString(BusinessTag.COURSE_INITIALS, courseInitials);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCourseID = getArguments().getInt(BusinessTag.COURSE_ID);
            mCourseName = getArguments().getString(BusinessTag.COURSE_NAME);
            mCourseSummary = getArguments().getString(BusinessTag.COURSE_SUMMARY);
            mCourseCode = getArguments().getString(BusinessTag.COURSE_CODE);
            mCourseInitials = getArguments().getString(BusinessTag.COURSE_INITIALS);
        }


        mUserID = KYSSharedPreferences.getUserDataFromSharedPref().getUserID();
        setTitle(mCourseName);

        mViewInfoController = ViewInformationController.createInstance(getActivity());
    }

    private void setTitle(String deptName) {
        if (deptName != null) {
            ((CourseActivity) getActivity()).getSupportActionBar().setTitle(deptName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ARJUN", "DrawerFragment() - onViewCreated()");

        initViews(view);

        initHeaderView();

        showUserFeedbackLoadingView();
        showAllFeedbackLoadingView();
        showAddFeedbackFloatingButton();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                onListItemClicked(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        requesFeedbackListAdapter();
        requesUserFeedbackInfo();
        requestProfessorInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        requesFeedbackListAdapter();
        requesUserFeedbackInfo();
    }

    private void initHeaderView() {
        mHeaderCourseIntials.setText(mCourseInitials);
        mHeaderCourseTitle.setText(mCourseName);
        mHeaderCourseCode.setText(mCourseCode);
        mHeaderCourseSummary.setText(mCourseSummary);
    }

    private void initViews(View view) {

        mHeaderCardView = (CardView) view.findViewById(R.id.frag_course_cv_header);
        mHeaderCourseTitle = (TextView) view.findViewById(R.id.fch_tv_course_title);
        mHeaderCourseIntials = (TextView) view.findViewById(R.id.fch_tv_course_code);
        mHeaderCourseCode = (TextView) view.findViewById(R.id.fch_tv_course_code);
        mHeaderCourseSummary = (TextView) view.findViewById(R.id.fch_tv_course_summary);

        mOverallRatingsCardView = (CardView) view.findViewById(R.id.frag_course_cv_overall_ratings);
        mOverallRatingBar = (RatingBar) view.findViewById(R.id.fcor_ratingbar);
        mFeedbackCountView = (TextView) view.findViewById(R.id.fcor_feedback_count);

        mUserFeedbackCardView = (CardView) view.findViewById(R.id.frag_course_cv_user_feedback);
        mUserFeedbackUserName = (TextView) view.findViewById(R.id.fcuf_tv_user_name);
        mUserFeedbackUserIntials = (TextView) view.findViewById(R.id.fcuf_tv_initials);
        mUserFeedDate = (TextView) view.findViewById(R.id.fcuf_tv_submit_time);
        mUserFeedbackTitle = (TextView) view.findViewById(R.id.fcuf_tv_feedback_title);
        mUserFeedbackComment = (TextView) view.findViewById(R.id.fcuf_tv_feedback_comment);
        mUserFeedbackProfessorName = (TextView) view.findViewById(R.id.fcuf_tv_prof_name);
        mUserFeedbackCourseName = (TextView) view.findViewById(R.id.fcuf_tv_course_name);
        mUserFeedbackRatingBar = (RatingBar) view.findViewById(R.id.fcuf_ratingbar);
        mUserFeedbackLoadingView = (LinearLayout) view.findViewById(R.id.fcuf_loading_view);
        mUserFeedbackErrorView = (RelativeLayout) view.findViewById(R.id.fcuf_error_view);
        mUserFeedbackErrorText = (TextView) view.findViewById(R.id.fcuf_tv_error);

        mAllFeedbackCardView = (CardView) view.findViewById(R.id.frag_course_cv_all_feedback);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fcaf_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.requestDisallowInterceptTouchEvent(true);
        mAllFeedbackLoadingView = (LinearLayout) view.findViewById(R.id.fcaf_loading_view);
        mAllFeedbackErrorText = (TextView) view.findViewById(R.id.fcaf_tv_error);
        mAllFeedbackErrorView = (RelativeLayout) view.findViewById(R.id.fcaf_error_view);
        mSortOptinSpinner = (Spinner) view.findViewById(R.id.fcaf_spinner_sort);
        mPorfNameFilterSpinner = (Spinner) view.findViewById(R.id.fcaf_spinner_filter_professor);

        mAddFeedbackButton = (FloatingActionButton) view.findViewById(R.id.floating_af_button);
        mAddFeedbackButton.setOnClickListener(mOnAddFeedbackClickListener);

    }

    public View.OnClickListener mOnAddFeedbackClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            AUFeedbackDialog fragment = AUFeedbackDialog.newInstance(mCourseID,mCourseName);
            fragment.setParentFragment(mFragment);
            fragment.show(getActivity().getFragmentManager(), "missiles");
        }
    };

    private void showAddFeedbackFloatingButton() {
        if (mAddFeedbackButton != null) {
            mAddFeedbackButton.setVisibility(View.VISIBLE);
        }
    }

    private void hideAddFeedbackFloatingButton() {
        if (mAddFeedbackButton != null) {
            mAddFeedbackButton.setVisibility(View.GONE);
        }
    }

    private void showUserFeedbackLoadingView() {
        if (mUserFeedbackLoadingView != null) {
            mUserFeedbackLoadingView.setVisibility(View.VISIBLE);
        }
        hideUserFeedbackErrorView();
    }

    private void showAllFeedbackLoadingView() {
        if (mAllFeedbackLoadingView != null) {
            mAllFeedbackLoadingView.setVisibility(View.VISIBLE);
        }
        hideRecyclerView();
        hideAllFeedbackErrorView();
    }

    private void hideUserFeedbackLoadingView() {
        if (mUserFeedbackLoadingView != null) {
            mUserFeedbackLoadingView.setVisibility(View.INVISIBLE);
        }
    }

    private void hideAllFeedbackLoadingView() {
        if (mAllFeedbackLoadingView != null) {
            mAllFeedbackLoadingView.setVisibility(View.INVISIBLE);
        }
    }

    private void showUserFeedbackErrorView(String errorMsg) {
        if (mUserFeedbackErrorView != null && mUserFeedbackErrorText != null) {
            mUserFeedbackErrorView.setVisibility(View.VISIBLE);
            mUserFeedbackErrorText.setText(errorMsg);

            hideUserFeedbackLoadingView();
        }
    }

    private void showAllFeedbackErrorView(String errorMsg) {
        if (mAllFeedbackErrorView != null && mAllFeedbackErrorText != null) {
            mAllFeedbackErrorView.setVisibility(View.VISIBLE);
            mAllFeedbackErrorText.setText(errorMsg);

            hideAllFeedbackLoadingView();
            hideRecyclerView();
        }
    }

    private void hideUserFeedbackErrorView() {
        if (mUserFeedbackErrorView != null) {
            mUserFeedbackErrorView.setVisibility(View.INVISIBLE);
        }
    }

    private void hideAllFeedbackErrorView() {
        if (mAllFeedbackErrorView != null) {
            mAllFeedbackErrorView.setVisibility(View.INVISIBLE);
        }
    }

    private void hideUserFeedbackView() {
        if(mUserFeedbackCardView != null) {
            mUserFeedbackCardView.setVisibility(View.GONE);
        }

    }

    private void showUserFeedbackView() {
        if(mUserFeedbackCardView != null) {
            mUserFeedbackCardView.setVisibility(View.VISIBLE);
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

    private void hideNoDataView() {
        hideAllFeedbackLoadingView();
        hideAllFeedbackErrorView();
    }

    private void onListItemClicked(View view, int position) {
        if (view.getTag() == null) return;

        if (view.getTag() instanceof Course) {

            Intent intent = new Intent(getActivity(), CourseActivity.class);
            intent.putExtra(BusinessTag.COURSE_ID, ((Course) view.getTag()).getCourseId());
            intent.putExtra(BusinessTag.COURSE_NAME, ((Course) view.getTag()).getCourseName());
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




    private void requesUserFeedbackInfo() {
        if (mViewInfoController != null) {
            ResponseListener.getInstnace().setCourseFeedbackDataListener(mFragment);
            mViewInfoController.getCourseFeedbackForUser(mUserID, mCourseID);
        }
    }

    private void requesFeedbackListAdapter() {
        if (mViewInfoController != null) {
            ResponseListener.getInstnace().setCourseFeedbackDataListener(mFragment);
            mViewInfoController.getFeebdbacksForCourse(mCourseID);
        }
    }

    private void requestProfessorInfo() {
        if (mViewInfoController != null) {
            ResponseListener.getInstnace().setCourseFeedbackDataListener(mFragment);
            mViewInfoController.getProfessorForCourse(mCourseID);
        }
    }

    private void requestAllProfessors() {
        if (mViewInfoController != null) {
            ResponseListener.getInstnace().setCourseFeedbackDataListener(mFragment);
            mViewInfoController.getAllProfessors();
        }
    }


    private void requestFeedbackForProfessor(int profId) {
        if (mViewInfoController != null) {
            ResponseListener.getInstnace().setCourseFeedbackDataListener(mFragment);
            mViewInfoController.getFeedbackForProfessor(profId, mCourseID);
        }
    }

    public void onAllFeedbackResponse(ArrayList<Feedback> fbList, boolean isError, String error) {
        Log.d("ARJUN", "DrawerFragment() - onResponse()");
        if (isError) {
            hideAllFeedbackLoadingView();
            showAllFeedbackErrorView("Failed to load data");
        } else {
            if(fbList == null || fbList.size() <= 0) {
                hideAllFeedbackLoadingView();
                showAllFeedbackErrorView("No data");
            } else {
                setAdapter(fbList);
            }
        }

        //setAdapter(fbList);
    }

    public void onUserFeedbackResponse(Feedback userFB, boolean isError, String error) {
        Log.d("ARJUN", "DrawerFragment() - onResponse()");
        if (isError) {
            showUserFeedbackErrorView("No data");
        } else {
            if(userFB == null) {
                hideUserFeedbackView();
             } else {
                mUserFeedback = userFB;
                showAddFeedbackFloatingButton();
                loadUserFeebdack(mUserFeedback);
                showUserFeedbackView();
            }
        }
//        loadUserFeebdack(mUserFeedback);
//        showUserFeedbackView();
    }

    public void onCourseProfessorDataResponse(ArrayList<Professor> profList, boolean isError, String error) {
        if (isError) {

        } else {
            mCourseProfList = profList;
            loadProfFilterSpinner();
        }

    }

    public void onAllProfessorsResponse(ArrayList<Professor> profList, boolean isError, String error) {
        if (isError) {

        } else {
            mAllProfList = profList;
            createLinkProfessorDialog();
        }
    }

    public void onUpdateCourseResponce(String response, boolean isError) {
        if (isError) {
            Util.showLongToast(getActivity(), response);
        } else {
            Util.showLongToast(getActivity(), "Professor linked successfully");
        }

    }

    public void handleLinkProfessor() {
        requestAllProfessors();

    }

    private void loadProfFilterSpinner() {
        profNameArray = new ArrayList<String>();

        if (mCourseProfList != null && mCourseProfList.size() > 0) {

            for (Professor prof : mCourseProfList) {
                profNameArray.add(prof.getProfessorName());
            }
        }

        if (mPorfNameFilterSpinner != null && profNameArray.size() > 0) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, profNameArray);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            mPorfNameFilterSpinner.setAdapter(dataAdapter);
            mPorfNameFilterSpinner.setOnItemSelectedListener(new SpineerOnItemSelectedListener());
        }
    }

    public ArrayList<String> getCourseProfessorNameList() {
        return profNameArray;
    }

    public class SpineerOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

            int profId = mCourseProfList.get(pos).getProfessorId();
            requestFeedbackForProfessor(profId);

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public int getCourseProfIDAt(int position) {

        return mCourseProfList.get(position).getProfessorId();
    }

    public int getAllProfIDAt(int position) {

        return mAllProfList.get(position).getProfessorId();
    }

    private void createLinkProfessorDialog() {
        if (mAllProfList == null || mAllProfList.size() <= 0) return;

        mAllProfNameList = new String[mAllProfList.size()];

        int index = 0;
        for (Professor prof : mAllProfList) {
            mAllProfNameList[index] = prof.getProfessorName();
            index++;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Link professor");
        builder.setSingleChoiceItems(mAllProfNameList, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int index) {
                Log.d("ARJUN", "The wrong button was tapped: " + mAllProfNameList[index]);

                mCourseProfLinkSelectedPosition = index;


            }
        });
        builder.setPositiveButton("LINK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Integer profId = mAllProfList.get(mCourseProfLinkSelectedPosition).getProfessorId();

                ArrayList<Integer> profIds = new ArrayList<Integer>();
                profIds.add(profId);

                Course course = new Course();
                course.setCourseId(mCourseID);
                course.setProfessorIds(profIds);

                ResponseListener.getInstnace().setCourseFeedbackDataListener(mFragment);

                UpdateController.createInstance(getActivity()).updateCourse(course);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        builder.create().show();

    }


    private void loadUserFeebdack(Feedback feedback) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String currDate = dateFormat.format(date);

        /*Feedback dummyFeedback = new Feedback();
        dummyFeedback.setFeedBackId(1);
        dummyFeedback.setCourseId(2);
        dummyFeedback.setCourseName("Artificial Intelligence");
        dummyFeedback.setProfessorId(3);
        dummyFeedback.setProfessorName("Vassilis");
        dummyFeedback.setUserId(4);
        dummyFeedback.setUserName("Arjun Vekariya");
        dummyFeedback.setDate(currDate);
        dummyFeedback.setTitle("Prof. vassilis knows his stuff well.");
        dummyFeedback.setComment("One can  learn a lot from thso professor. Course content is comprehensive and well organized. More over get to develope solutions for real life problems which is quite interesting.");
        dummyFeedback.setHelpfulnessCount(50);
        dummyFeedback.setUnhelpfulnessCount(6);
        dummyFeedback.setIsAnonymous(false);
        dummyFeedback.setIsSpam(false);
        dummyFeedback.setRating(1.5f);*/

        mUserFeedbackTitle.setText(feedback.getTitle());
        mUserFeedbackUserIntials.setText(Util.getInitials(feedback.getCourseName()));
        mUserFeedbackComment.setText(feedback.getComment());
        mUserFeedbackUserName.setText(feedback.getUserName());
        mUserFeedbackRatingBar.setRating(feedback.getRating());

        long timestamp = Long.parseLong(feedback.getDate());
        mUserFeedDate.setText(getDate(timestamp));
        mUserFeedbackCourseName.setText(feedback.getCourseName());
        mUserFeedbackProfessorName.setText(feedback.getProfessorName());

        hideUserFeedbackLoadingView();
    }

    private String getDate(long timeStamp){

        try{
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private void setAdapter(ArrayList<Feedback> fbList) {
        ArrayList<Feedback> dummyFeedbackList = new ArrayList<Feedback>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String currDate = dateFormat.format(date);

        Feedback dummyFeedback2 = new Feedback();
        dummyFeedback2.setFeedBackId(1);
        dummyFeedback2.setCourseId(2);
        dummyFeedback2.setCourseName("Artificial Intelligence");
        dummyFeedback2.setProfessorId(3);
        dummyFeedback2.setProfessorName("Vasillis");
        dummyFeedback2.setUserId(4);
        dummyFeedback2.setUserName("Arjun Vekariya");
        dummyFeedback2.setDate(currDate);
        dummyFeedback2.setTitle("He knows his stuff well.");
        dummyFeedback2.setComment("One can  learn a lot from thso professor. Course content is comprehensive and well organized. More over get to develope " +
                "solutions for real life problems which is quite interesting.");
        dummyFeedback2.setHelpfulnessCount(50);
        dummyFeedback2.setUnhelpfulnessCount(6);
        dummyFeedback2.setIsAnonymous(false);
        dummyFeedback2.setIsSpam(false);
        dummyFeedback2.setRating(1.5f);
        dummyFeedbackList.add(dummyFeedback2);

        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);
        dummyFeedbackList.add(dummyFeedback2);








        mAdapter = new FeedbackListAdapter(getActivity(), fbList);
        if (mRecyclerView != null && mAdapter != null && mAdapter.getItemCount() > 0 && isResumed()) {
            showRecyclerView();
            hideNoDataView();
            Log.d("ARJUN", "setAdapter() - " + mAdapter.getItemCount());
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

}