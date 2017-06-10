package com.example.bechirkaddech.musicapp.Notification;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.bechirkaddech.musicapp.MainActivity;

public class NotificationReturnSlot extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        String action = (String) getIntent().getExtras().get("DO");
        if (action.equals("play")) {
            Log.i("NotificationReturnSlot", "play");

            if (MainActivity.mediaPlayer != null) {

                MainActivity.playPause();
            }


            //Your code
        } else if (action.equals("previous")) {
            //Your code
            Log.i("NotificationReturnSlot", "previous");
        }

                else if (action.equals("next")){
            Log.i("NotificationReturnSlot", "next");
        }



        finish();
    }
}