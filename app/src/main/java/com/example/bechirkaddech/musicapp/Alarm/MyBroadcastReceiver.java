package com.example.bechirkaddech.musicapp.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.bechirkaddech.musicapp.MainActivity;

import org.json.JSONException;

/**
 * Created by bechirkaddech on 6/1/17.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    SharedPreferences mPrefs ;
    @Override
    public void onReceive(Context context, Intent intent) {



        try {
            if (MainActivity.mediaPlayer.isPlaying()) {
                MainActivity.mediaPlayer.stop();
                MainActivity.CheckMediaPlayerIsPlaying();
            }        }

        catch (NullPointerException e) {
        }
        Toast.makeText(context, "Music Stopped....", Toast.LENGTH_LONG).show();

        SharedPreferences  mPrefs = context.getSharedPreferences("mypref",context.MODE_PRIVATE);


        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putBoolean("active", false);
        prefsEditor.putInt("progress", 0);
        prefsEditor.commit();




    }
}
