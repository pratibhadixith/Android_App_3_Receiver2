package com.example.prati.mp3appa3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by prati on 10/23/2016.
 */

public class Receiver extends BroadcastReceiver {
    Intent selectedi;
    @Override
    // Override onReceive method to check which option the user has selected and
    // call the corresponding Activity class by using explicit intent
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.app.SendHotel")){
            selectedi = new Intent(context.getApplicationContext(), HotelActivity.class);
        }else{
            selectedi = new Intent(context.getApplicationContext(), RestaurantActivity.class);
        }
        selectedi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(selectedi);

    }

}

