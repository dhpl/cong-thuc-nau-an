package com.philong.congthucnauan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.fragment.FragmentBoSuuTap;
import com.philong.congthucnauan.fragment.FragmentHome;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private boolean closeActivity = false;


    //index page
    public static int navItemIndex = 0;

    //Tag fragment
    private static final String HOME = "HOME";
    private static final String BO_SUU_TAP = "BOSUUTAP";
    public static String CURRENT_TAG = HOME;
    private String[] titles;
    private boolean loadHomeFragmentOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = getResources().getStringArray(R.array.nav_item);
        mHandler = new Handler();
        //setup toolbar;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.app_name);
        }
        //Drawerlayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        setUpNavigation();
        loadHomeFragment();

    }

    private void loadHomeFragment(){
        selecNavigation();
        setToolbarTitle();
        if(getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null){
            mDrawerLayout.closeDrawers();
            return;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getFragmet();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if(runnable != null){
            mHandler.post(runnable);
        }
        mDrawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    private void setToolbarTitle(){
        getSupportActionBar().setTitle(titles[navItemIndex]);
    }

    private Fragment getFragmet(){
        switch (navItemIndex){
            case 0:
                FragmentHome fragmentHome = new FragmentHome();
                return fragmentHome;
            case 1:
                FragmentBoSuuTap fragmentBoSuuTap = new FragmentBoSuuTap();
                return fragmentBoSuuTap;
            default:
                return new FragmentHome();
        }
    }

    private void selecNavigation(){
        mNavigationView.getMenu().getItem(navItemIndex).setCheckable(true);
    }

    private void  setUpNavigation(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = HOME;
                        break;
                    case R.id.nav_bo_suu_tap:
                        navItemIndex = 1;
                        CURRENT_TAG = BO_SUU_TAP;
                        break;
                    default:
                        navItemIndex = 0;
                }
                if(item.isChecked()){
                    item.setChecked(false);
                }else{
                    item.setChecked(true);
                }
                item.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
            return;
        }
        if(loadHomeFragmentOnBackPress){
            if(navItemIndex != 0){
                navItemIndex = 0;
                CURRENT_TAG = HOME;
                loadHomeFragment();
                return;
            }
        }
        if(closeActivity){
            super.onBackPressed();
            return;
        }
        closeActivity = true;
        Toast.makeText(this, "Bấm thêm lần nữa để thoát", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeActivity = false;
            }
        }, 2000);
    }

}
