package com.example.android.igclone.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.igclone.R;
import com.example.android.igclone.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    private ImageView mProfilePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);

        // Initialize before we set image
        initImageLoader();
        setProfileImage();

        return view;
    }

    // Initialize imageloader
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());

    }

    // TODO temporary method to test that we can load images
    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile image");
        String imgURL = "a248.e.akamai.net/ib.huluim.com/video/40036427?size=476x268&region=us";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "https://");
    }

}
