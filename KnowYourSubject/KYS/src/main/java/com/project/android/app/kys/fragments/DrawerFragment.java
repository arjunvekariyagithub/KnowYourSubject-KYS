package com.project.android.app.kys.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import com.project.android.app.kys.R;
import com.project.android.app.kys.activities.HomeActivity;
import com.project.android.app.kys.adapters.DrawerAdapter;
import com.project.android.app.kys.application.KYSApplication;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.business.User;
import com.project.android.app.kys.callbacks.ResponseListener;
import com.project.android.app.kys.controller.ViewInformationController;
import com.project.android.app.kys.helper.Constants.Action;
import com.project.android.app.kys.helper.Util;
import com.project.android.app.kys.preferences.KYSSharedPreferences;

import java.util.ArrayList;

public class DrawerFragment extends Fragment {

    static DrawerFragment fragment = null;
    public RecyclerView mRecyclerView = null;
    public DrawerAdapter mAdapter;
    public ViewInformationController mViewInfoController;
    private DrawerLayout mDrawerLayout;
    private View mContainer;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;


    TextView tv_nav_header_initials;
    TextView tv_nav_header_name;
    TextView tv_nav_header_email;

    public static DrawerFragment newInstance() {
        fragment = new DrawerFragment();
        return fragment;
    }

    public DrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "DrawerFragment() - onCreate()");
        mViewInfoController = ViewInformationController.createInstance(getActivity());
        mUserLearnedDrawer = false;
        mFromSavedInstanceState = savedInstanceState != null ? true : false;
        registerBroadcastReciever();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_navigation_drawer, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("test", "DrawerFragment() - onViewCreated()");

        tv_nav_header_initials = (TextView)view.findViewById(R.id.nav_header_tv_initials);
        tv_nav_header_name = (TextView)view.findViewById(R.id.nav_header_tv_name);
        tv_nav_header_email = (TextView)view.findViewById(R.id.nav_header_tv_email);

        setHeaderData();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.drawerListView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                ((HomeActivity) getActivity()).onDrawerItemClicked(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        requesAdapter();
    }

    private void setHeaderData() {
        User user = KYSSharedPreferences.getUserDataFromSharedPref();
        tv_nav_header_initials.setText(Util.getInitials(user.getFirstName() + " "+user.getLastName()));
        tv_nav_header_name.setText(user.getFirstName() + " "+user.getLastName());
        tv_nav_header_email.setText(user.getEmailId());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void requesAdapter() {

        Log.d("test","request Adapter");

     if(mViewInfoController != null){
         ResponseListener.getInstnace().setDisciplineDataListener(fragment);
         mViewInfoController.getDisciplines();
     }
    }

    public void onResponse(ArrayList<Discipline> dispList, boolean isError, String error) {
        Log.d("ARJUN", "DrawerFragment() - onResponse()");
        if (isError) {
        } else {
            if (dispList.size() <= 0) {
                //showErrorView("No data.");
            } else {
                ((HomeActivity) getActivity()).setInitialView(dispList.get(0));


                setAdapter(dispList);
            }
        }


        //setAdapter(dispList);
    }

    private void setAdapter(ArrayList<Discipline> dispList) {
        /*ArrayList<Discipline> dummyDispList = new ArrayList<Discipline>();
        Discipline discipline1 = new Discipline();
        discipline1.setDisciplineId(100);
        discipline1.setDisciplineName("Engieering");
        discipline1.setDisciplineInitials("ENG");
        discipline1.setDisciplineSummary("Mathematics discipline is the worst discipline amongst all the discipline.");
        dummyDispList.add(discipline1);

        Discipline discipline2 = new Discipline();
        discipline2.setDisciplineId(100);
        discipline2.setDisciplineName("Mathematics");
        discipline2.setDisciplineInitials("MATH");
        discipline2.setDisciplineSummary("Mathematics discipline is the worst discipline amongst all the discipline.");
        dummyDispList.add(discipline2);

        Discipline discipline3 = new Discipline();
        discipline3.setDisciplineId(100);
        discipline3.setDisciplineName("Science");
        discipline3.setDisciplineInitials("SC");
        discipline3.setDisciplineSummary("Mathematics discipline is the worst discipline amongst all the discipline.");
        dummyDispList.add(discipline3);

        Discipline discipline4 = new Discipline();
        discipline4.setDisciplineId(100);
        discipline4.setDisciplineName("Management");
        discipline4.setDisciplineInitials("MG");
        discipline4.setDisciplineSummary("Mathematics discipline is the worst discipline amongst all the discipline.");
        dummyDispList.add(discipline4);*/

        mAdapter = new DrawerAdapter(getActivity(), dispList);
        if(mRecyclerView != null && isResumed()) {
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void registerBroadcastReciever() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.REFRESH_DISCIPLINE_LIST);
        KYSApplication.getAppContext().registerReceiver(mStatusReceiver, new IntentFilter(intentFilter));
    }

    private final BroadcastReceiver mStatusReceiver = new BroadcastReceiver() {
        public void onReceive(final Context context, final Intent intent) {
            Log.d("ARJUN", "mStatusReceiver - " + intent.getAction());
           if (intent.getAction().equals(Action.REFRESH_DISCIPLINE_LIST)) {
               requesAdapter();
           }
        }

    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setUp(Context context, View container, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainer = container;
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle((HomeActivity)context, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                requesAdapter();

                Log.d("ARJUN", "onDrawerOpened");
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    //MyApplication.saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer);
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d("ARJUN", "onDrawerClosed");
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                ((HomeActivity)getActivity()).onDrawerSlide(slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
//                if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
//                    mDrawerLayout.openDrawer(mContainer);
//                }
            }
        });
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