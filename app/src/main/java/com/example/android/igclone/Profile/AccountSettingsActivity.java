package com.example.android.igclone.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.android.igclone.R;
import com.example.android.igclone.Utils.BottomNavigationViewHelper;
import com.example.android.igclone.Utils.SectionsStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";

    // Code for switch used in BottomNavigationViewHelper
    private static final int ACTIVITY_NUM = 4;

    private Context mContext;

    private SectionsStatePagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started");
        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);

        setupSettingsList();
        setupBottomNavigationView();
        setupFragments();

        // Setup back arrow for navigating back to 'ProfileActivity'
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 'navigating back to ProfileActivity'");
                finish();
            }
        });
    }

    private void setupFragments(){
        mPagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); // fragment 0
        mPagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); // fragment 1
    }

    private void setViewPager(int fragmentNumber){
        // once fragment is brought up, RelativeLayout disappears
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment #: " + fragmentNumber);
        // Instantiates ViewPager
        mViewPager.setAdapter(mPagerAdapter);
        // Navigates to the desired fragment
        mViewPager.setCurrentItem(fragmentNumber);
    }
    
    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: initializing 'Account Settings' list");
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); // fragment 0
        options.add(getString(R.string.sign_out_fragment)); // fragment 0

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment #: " + position);
                setViewPager(position);
            }
        });
    }

    /**
     * Customize navigation bar using BottomNavigationViewEx
     * Being set up as a method so it can be reused in other activities
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx =
                (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

        // This ensures that the selected nav icon is highlighted
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    
}
