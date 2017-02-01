package com.example.prati.mp3appa3;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by prati on 10/29/2016.
 */

public class RestaurantListFragment extends ListFragment {
    private RestaurantListFragment.ListSelectionListener mListener = null;


    // Callback interface that allows this Fragment to notify the HotelActivity when
    // user clicks on a List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);
        //  Global_pos=pos;
        // Inform the HotelActivity that the item in position pos has been selected
        mListener.onListSelection(pos);
    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        try {
            // Set the ListSelectionListener for communicating with the HotelActivity
            mListener = (RestaurantListFragment.ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.restaurant_list,RestaurantActivity.RestaurantArray));
        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

}
