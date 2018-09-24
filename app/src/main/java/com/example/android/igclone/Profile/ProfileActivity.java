package com.example.android.igclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.igclone.R;
import com.example.android.igclone.Utils.BottomNavigationViewHelper;
import com.example.android.igclone.Utils.GridImageAdapter;
import com.example.android.igclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    // Code for switch used in BottomNavigationViewHelper
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: starting");

        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();

        tempGridSetup();
    }

    // Adapt a list of images to the GridView using GridImageAdapter
    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();

        imgURLs.add("https://i.pinimg.com/originals/cb/43/4a/cb434a534a290069ea6b326d14409bc7.jpg");
        imgURLs.add("https://ourcatsworld.com/wp-content/uploads/2016/02/TuxedoCat_Mustache-e1454563887153.jpg");
        imgURLs.add("https://i.ytimg.com/vi/b0OTiGM66Zk/hqdefault.jpg");
        imgURLs.add("https://www.wwwallaboutcats.com/wp-content/uploads/2016/10/tuxedocatnames-1.jpg");
        imgURLs.add("https://www.catster.com/wp-content/uploads/2018/03/A-fluffy-tuxedo-cat.jpg");
        imgURLs.add("https://image.shutterstock.com/image-photo/cute-curious-black-white-tuxedo-260nw-516305215.jpg");

        setupImageGrid(imgURLs);
    }


    // Setup GridView
    // TODO temporary test until data is eventually pulled from Firebase
    private void setupImageGrid(ArrayList<String> imgURLs) {
        GridView gridView = findViewById(R.id.gridView);

        // Establish image size when populating view
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        // 'layout_gridimageview' is what will be recycled
        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_gridimageview, "", imgURLs);
        gridView.setAdapter(adapter);
    }

    // TODO test until data pulled from a database
    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile photo");
        String imgURL = "a248.e.akamai.net/ib.huluim.com/video/40036427?size=476x268&region=us";
        UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "https://");
    }

    // Method to initialize widgets
    private void setupActivityWidgets() {
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    /**
     * Method to set up toolbar
     */
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

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
