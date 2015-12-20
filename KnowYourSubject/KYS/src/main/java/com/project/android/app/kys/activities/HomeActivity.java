package com.project.android.app.kys.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.project.android.app.kys.R;
import com.project.android.app.kys.business.Discipline;
import com.project.android.app.kys.fragments.DepartmentFragment;
import com.project.android.app.kys.fragments.DrawerFragment;
import com.project.android.app.kys.fragments.HomeFragment;
import com.project.android.app.kys.helper.Constants.Tag.BusinessTag;
import com.project.android.app.kys.helper.Constants.Tag.DrawerItemTag;
import com.project.android.app.kys.fragments.DisciplineFragment;
import com.project.android.app.kys.helper.Type;
import com.project.android.app.kys.helper.Type.UserType;
import com.project.android.app.kys.popup.AUCourseDialog;
import com.project.android.app.kys.popup.AUDepartmentDialog;
import com.project.android.app.kys.popup.AUProfessorDialog;
import com.project.android.app.kys.preferences.GlobalSettings;
import com.project.android.app.kys.preferences.KYSSharedPreferences;

public class HomeActivity extends AppCompatActivity {

    public Context mContext;

    Toolbar mToolbar = null;
    private DrawerFragment mDrawerFragment;
    private DisciplineFragment mDisciplineFragment = null;
    private DepartmentFragment mDepartmentFragment = null;
    private HomeFragment mHomeFragment = null;
    NavigationView navigationView;
    String title = "";

    Discipline mDiscipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = this;


        setupDrawer();

        loadInitialFragment();
    }

    private void loadInitialFragment() {

        if(UserType.isLoginUser()) {
          mHomeFragment = HomeFragment.newInstance();
            if (mHomeFragment != null) {
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.content, mHomeFragment).addToBackStack(null).commitAllowingStateLoss();
                getSupportActionBar().setTitle("YOU");

            }
        } else  {
            mDisciplineFragment = DisciplineFragment.newInstance();
            if (mDisciplineFragment != null) {
                FragmentManager fragmentManager = getFragmentManager();

               Bundle args = new Bundle();
                args.putInt(BusinessTag.DISCP_ID, 1);
                args.putString(BusinessTag.DISP_NAME, "Engineering");
                mDisciplineFragment.setArguments(args);

                fragmentManager.beginTransaction().replace(R.id.content, mDisciplineFragment).addToBackStack(null).commitAllowingStateLoss();

                getSupportActionBar().setTitle("Engineering");

            }
        }
    }



    private void setupDrawer() {
        Log.d("ARJUN", "HomeActivity() - setupDrawer()");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //set the Toolbar as ActionBar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //setup the NavigationDrawer
        mDrawerFragment = DrawerFragment.newInstance();
        if (mDrawerFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_navigation_drawer, mDrawerFragment).addToBackStack(null).commitAllowingStateLoss();

        }
        mDrawerFragment.setUp(mContext, findViewById(R.id.fragment_navigation_drawer), (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(true);
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
                AUDepartmentDialog newFragment = AUDepartmentDialog.newInstance();
                newFragment.setParentFragment(mDisciplineFragment);
                newFragment.show(((HomeActivity)mContext).getFragmentManager(), "missiles");
                break;

            case R.id.action_search:

                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void setInitialView(Discipline disp)
    {
        Log.d("test",""+disp.getDisciplineName());
           mDiscipline = disp;
        Log.d("test",""+mDiscipline.getDisciplineName());

    }

    public void onDrawerItemClicked(View view, int position) {
        if (view.getTag() == null) return;

        if(view.getTag() instanceof Discipline) {
            Discipline disp = ((Discipline) view.getTag());
            if (mDisciplineFragment == null) {
                Toast.makeText(mContext , "Discipline : " + disp.getDisciplineName() + " clicked", Toast.LENGTH_SHORT).show();
                mDisciplineFragment = DisciplineFragment.newInstance();
                Bundle b = new Bundle();
                b.putInt(BusinessTag.DISCP_ID, disp.getDisciplineId());
                b.putString(BusinessTag.DISP_NAME, disp.getDisciplineName());
                mDisciplineFragment.setArguments(b);
                replaceFragment(R.id.content, mDisciplineFragment, null);
            } else {
                mDisciplineFragment.changeDiscipline(disp.getDisciplineId(), disp.getDisciplineName());
            }
        } else {
            //Toast.makeText(mContext , "Option : " + view.getTag().toString() + " clicked", Toast.LENGTH_SHORT).show();
             switch(view.getTag().toString()) {
                 case DrawerItemTag.ADD_PROFESSOR:
                     AUProfessorDialog newFragment = AUProfessorDialog.newInstance();
                     newFragment.setParentFragment(mDisciplineFragment);
                     newFragment.show(((HomeActivity)mContext).getFragmentManager(), "missiles");
                     break;

                 case DrawerItemTag.SPAM:

                     break;

                 case DrawerItemTag.SETTINGS:
                     Intent i = new Intent(mContext, GlobalSettings.class);
                     i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(i);

                     break;

                 case DrawerItemTag.LOGOUT:
                     KYSSharedPreferences.LogoutUser();
                     Intent intent = new Intent(mContext, LoginActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                     finish();

                     break;

                 default:
                     break;
            }
        }
    }

    public void replaceFragment(int target, DisciplineFragment fragment, String backStack) {
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(target, fragment).addToBackStack(backStack).commitAllowingStateLoss();
        }
    }

    public void onDrawerSlide(float slideOffset) {

    }
}
