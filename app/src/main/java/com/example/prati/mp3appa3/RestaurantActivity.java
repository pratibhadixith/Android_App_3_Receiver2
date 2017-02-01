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

public class RestaurantActivity extends AppCompatActivity implements
    RestaurantListFragment.ListSelectionListener

    {

        public static String[] RestaurantArray={"Bavette's Bar and Boeuf \n218 W Kinzie St \nChicago, IL 60654-4908",
                "Eddie V's Prime Seafood \n521 N Rush St, Suite R01 \nChicago, IL 60611-3646",
                "Epic Burger \n517 S State St \nChicago, IL 60605-1616",
                "Goddess and the Baker \n33 S Wabash Ave \nChicago, IL 60603-3073",
                "Lou Mitchell's Restaurant \n565 W Jackson Blvd \nChicago, IL 60661-5701",
                "Mexique \n1529 W Chicago Ave \nChicago, IL 60642-5237"};

        public static int[] RestaurantImagearray={R.drawable.bavettesbarandboeuf,R.drawable.eddievprimeseafood,R.drawable.epicburger,
                R.drawable.goddessandthebaker,R.drawable.loumitchellsrestaurant,R.drawable.mexique};


        private FragmentManager myFragmentManager;
        private FrameLayout RestaurantListFrameLayout, RestaurantImageFrameLayout;

        private final RestaurantImageFragment Restaurantimage = new RestaurantImageFragment();

        private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.restaurantactivity);


        // Obtain references to the RestaurantlistFragment and RestaurantImageFragment
        RestaurantListFrameLayout = (FrameLayout) findViewById(R.id.restaurantlist);
        RestaurantImageFrameLayout = (FrameLayout) findViewById(R.id.restaurantimage);


        // Obtain reference to the FragmentManager
        myFragmentManager = getFragmentManager();


        // Start the Fragment Transaction
        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();

        RestaurantListFragment hlistf= new RestaurantListFragment();

        // Add the Restaurant list Fragment to the list fragment
        fragmentTransaction.replace(R.id.restaurantlist, hlistf);

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

        //Check if RestaurantImage fragment has been added already
        if (!Restaurantimage.isAdded()) {


            //set the Restaurant Image fragment to occupy no space
            RestaurantImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));

            //set the Restaurant list fragment to occupy full layout
            RestaurantListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));

        } else {

            //check if the orientation is Landscape
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                //set the Restaurant image layout to occupy  2/3 of the layout's entire width
                RestaurantImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));

                // set the Restaurant List layout to occupy only 1/3 of the layout's entire width
                RestaurantListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));


            }else{

                RestaurantImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));

                RestaurantListFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0, MATCH_PARENT));

            }
        }
    }

    // this method is called when an item in the Restaurants List Fragment
    @Override
    public void onListSelection(int index) {
        // If the Restaurant Image Fragment has not been added, add it now
        if (!Restaurantimage.isAdded()) {
            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            // Add the Restaurant image Fragment to the layout
            fragmentTransaction.add(R.id.restaurantimage, Restaurantimage);
            // Add this Fragment Transaction to the backstack
            fragmentTransaction.addToBackStack(null);
            // Commit the FragmentTransaction
            fragmentTransaction.commit();
            // Force Android to execute the committed Fragment Transaction
            myFragmentManager.executePendingTransactions();
        }

        // Show the Restaurant image Fragment at index selected
        Restaurantimage.showRestaurantImageAtIndex(index);

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
            Intent optionintent=new Intent(RestaurantActivity.this,HotelActivity.class);
            startActivity(optionintent);
        }
        //start RestaurantActivity
        if (id == R.id.Restaurantoption) {
            Intent optionintent=new Intent(RestaurantActivity.this,RestaurantActivity.class);
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
    //Reset layour everytime configuration is changed
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ResetFragmentLayout();
    }
}
