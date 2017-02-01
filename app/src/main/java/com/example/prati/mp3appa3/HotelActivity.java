package com.example.prati.mp3appa3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by prati on 10/23/2016.
 */

public class HotelActivity extends AppCompatActivity implements
        HotelListFragment.ListSelectionListener

    {

        public static String[] HotelArray={"The Langham Chicago \n330 North Wabash Avenue \nChicago, IL 60611-3586",
                "The Peninsula Chicago \n108 E Superior St, at North Michigan Avenue \nChicago, IL 60611-2645",
                "The Talbott Hotel \n20 East Delaware Place \nChicago, IL 60611-4938",
                "Four Seasons Hotel Chicago \n120 E. Delaware Pl.\nChicago, IL 60611-1443",
                "Park Hyatt Chicago \n800 N Michigan Ave, on Water Tower Square \nChicago, IL 60611-2105",
                "Thompson Chicago, a Thompson Hotel \n21 E. Bellevue Place \nChicago, IL 60611-1174"};
        
        public static int[] HotelImagearray={R.drawable.langham,R.drawable.peninsula,R.drawable.talbott,
                                            R.drawable.fourseasonshotelchicago,R.drawable.parkhyattchicago,R.drawable.thompsonchicagoathompsonhotel};
        
        
        private FragmentManager myFragmentManager;
        private FrameLayout HotelListFrameLayout, HotelImageFrameLayout;

        private final HotelImageFragment hotelimage = new HotelImageFragment();

        private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.hotelactivity);


            // Obtain references to the HotellistFragment and HotelImageFragment
            HotelListFrameLayout = (FrameLayout) findViewById(R.id.hotellist);
            HotelImageFrameLayout = (FrameLayout) findViewById(R.id.hotelimage);


            // Obtain reference to the FragmentManager
            myFragmentManager = getFragmentManager();


            // Start the Fragment Transaction
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();

            HotelListFragment hlistf= new HotelListFragment();

            // Add the Hotel list Fragment to the list fragment
            fragmentTransaction.replace(R.id.hotellist, hlistf);

            // Commit the transaction
            fragmentTransaction.commit();

            // Layout has to be reset whenever the back stack changes
            // Add a OnBackStackChangedListener for that
            myFragmentManager
                    .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                        public void onBackStackChanged() {
                            ResetFragmentLayout();
                        }
                    });
        }

    private void ResetFragmentLayout() {

        //Check if HotelImage fragment has been added already
        if (!hotelimage.isAdded()) {


            //set the Hotel Image fragment to occupy no space
            HotelImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));

            //set the Hotel list fragment to occupy full layout
            HotelListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));

        } else {

            //check if the orientation is Landscape
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                //set the Hotel image layout to occupy  2/3 of the layout's entire width
                HotelImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));

                // set the Hotel List layout to occupy only 1/3 of the layout's entire width
                HotelListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));


            }else{

                HotelImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));

                HotelListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0, MATCH_PARENT));

            }
        }
    }

    // this method is called when an item in the Hotels List Fragment
    @Override
    public void onListSelection(int index) {
        // If the Hotel Image Fragment has not been added, add it now
        if (!hotelimage.isAdded()) {
            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            // Add the Hotel image Fragment to the layout
            fragmentTransaction.add(R.id.hotelimage, hotelimage);
            // Add this Fragment Transaction to the backstack
            fragmentTransaction.addToBackStack(null);
            // Commit the FragmentTransaction
            fragmentTransaction.commit();
            // Force Android to execute the committed Fragment Transaction
            myFragmentManager.executePendingTransactions();
        }

            // Show the Hotel image Fragment at index selected
            hotelimage.showHotelImageAtIndex(index);

    }






    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add items to action bar;
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check on which item the user clicks and call corresponding activity
        int id = item.getItemId();
        //start HotelActivity
        if (id == R.id.hoteloption) {
            Intent optionintent=new Intent(HotelActivity.this,HotelActivity.class);
            startActivity(optionintent);
        }
        //start RestaurantActivity
        if (id == R.id.Restaurantoption) {
            Intent optionintent=new Intent(HotelActivity.this,RestaurantActivity.class);
            startActivity(optionintent);
        }
        return super.onOptionsItemSelected(item);
    }

        //if user clicks back, pop the top most Fragment
    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }
        //Reset layout everytime configuration is changed
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ResetFragmentLayout();
    }
}
