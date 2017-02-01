package com.example.prati.mp3appa3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by prati on 10/29/2016.
 */

public class RestaurantImageFragment extends Fragment {
    private ImageView ImgView = null;
    private int mCurrIdx = -1;
    private int mCImgArrLen;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Web Page at position newIndex
    void showRestaurantImageAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mCImgArrLen)
            return;
        mCurrIdx = newIndex;

        ImgView.setImageResource(RestaurantActivity.RestaurantImagearray[mCurrIdx]);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout defined in Hotel image fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.hotel_image, container, false);
    }

    // Set up some information about the Hotel Image View
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImgView = (ImageView) getActivity().findViewById(R.id.hotelimagefragment);
        mCImgArrLen = HotelActivity.HotelImagearray.length;
    }

}
