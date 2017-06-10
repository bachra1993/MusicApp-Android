package com.example.bechirkaddech.musicapp.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;

/**
 * Created by bechirkaddech on 6/3/17.
 */

public class NotificationPanel {


    private Context parent;
    private NotificationManager nManager;
    private NotificationCompat.Builder nBuilder;
    private RemoteViews remoteView;

    public NotificationPanel(Context parent) {
        // TODO Auto-generated constructor stub
        this.parent = parent;
        nBuilder = new NotificationCompat.Builder(parent)
                .setContentTitle("Parking Meter")
                .setContentTitle("lkagleglkjzeg,zegzg")
                .setSmallIcon(R.drawable.ic_music_video_black_24dp)
                .setOngoing(true);

        remoteView = new RemoteViews(parent.getPackageName(), R.layout.notificationview);


        //set the button listeners
        setListeners(remoteView);
        nBuilder.setContent(remoteView);


        nManager = (NotificationManager) parent.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(2, nBuilder.build());
    }

    public void setListeners(RemoteViews view){


        //listener 1
        Intent play = new Intent(parent,NotificationReturnSlot.class);
        play.putExtra("DO", "play");
        PendingIntent btn1 = PendingIntent.getActivity(parent, 0, play, 0);
        view.setOnClickPendingIntent(R.id.btn1, btn1);

        

        //listener 2
        Intent previous = new Intent(parent, NotificationReturnSlot.class);
        previous.putExtra("DO","previous");
        PendingIntent btn2 = PendingIntent.getActivity(parent, 1, previous, 0);
        view.setOnClickPendingIntent(R.id.btn2, btn2);




        //listener 2
        Intent next = new Intent(parent, NotificationReturnSlot.class);
        next.putExtra("DO", "next");
        PendingIntent btn3 = PendingIntent.getActivity(parent, 2, next, 0);
        view.setOnClickPendingIntent(R.id.btn3, btn3);













        //listener 2

    }

    public void notificationCancel() {
        nManager.cancel(2);
    }
}
