package com.example.bechirkaddech.musicapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.Fragments.MusicPlayer_fragment;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by bechirkaddech on 5/30/17.
 */


public class MyAdapter_list extends RecyclerView.Adapter<MyAdapter_list.RecycleView_Holder> {

    Context context;
    int ressource;
    ArrayList<Song> songsList ;
    AdRequest adRequest;


    public MyAdapter_list(Context context, int ressource, ArrayList<Song> songsList) {
        this.context = context;
        this.ressource = ressource;
        this.songsList = songsList;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        final RecycleView_Holder vh = new RecycleView_Holder(view);

        ViewGroup.LayoutParams params = parent.getLayoutParams();

        System.out.println("widht: " +String.valueOf(vh.item_layout.getWidth()));
        MobileAds.initialize(context, "ca-app-pub-5006866391263263~5301011236");



        return vh;


    }

    VideoView a ;


    @Override
    public void onBindViewHolder(final RecycleView_Holder holder, final int position) {

        final Song song = songsList.get(position);



        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((MainActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        int devicewidth = displaymetrics.widthPixels ;

        //if you need 4-5-6 anything fix imageview in height
        int deviceheight = displaymetrics.heightPixels ;




       // holder.item_layout.getLayoutParams().

        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height


        // Gets linearlayout
// Gets the layout params that will allow you to resize the layout
// Changes the height and width to the specified *pixels*



      //  holder.item_song_name.setText(song.getTitle());



        int next = position-1;
        int previous = position+1 ;





        if (songsList.size()==1 || position -1 == -1) {

            if (position - 1 == -1 && songsList.size()>1 ) {
                Picasso.with(context).load(songsList.get(position + 1).getArtwork_url()).into(holder.image_next);

            }

        }
        else {


            if (songsList.size() == 2 && position!=1) {
                Picasso.with(context).load(songsList.get(position + 1).getArtwork_url()).into(holder.image_next);

            }

            else if (position+1 != songsList.size()) {

                Picasso.with(context).load(songsList.get(position + 1).getArtwork_url()).into(holder.image_next);



            }


            Picasso.with(context).load(songsList.get(position - 1).getArtwork_url()).into(holder.image_previous);


        }






        /*

        if (songsList.size()<1) {

        }
        else {


            if (position - 1 == 1 && position != songsList.size()) {
                Picasso.with(context).load(songsList.get(position + 1).getArtwork_url()).into(holder.image_next);
                holder.image_previous.setVisibility(View.INVISIBLE);

            } else {
                Picasso.with(context).load(songsList.get(position + 1).getArtwork_url()).into(holder.image_next);
                Picasso.with(context).load(songsList.get(position - 1).getArtwork_url()).into(holder.image_previous);


            }

            if (position + 1 == songsList.size() + 1) {
                Picasso.with(context).load(songsList.get(position - 1).getArtwork_url()).into(holder.image_previous);
                holder.image_next.setVisibility(View.INVISIBLE);

            }
        }
*/
/*
        holder.image_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).playSong(songsList.get(position+1),songsList,false);
                MainActivity.jumpPosition = position+1;
                MusicPlayer_fragment.autoScroll(position+1);

            }
        });


        holder.image_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).playSong(songsList.get(position-1),songsList,false);
                MainActivity.jumpPosition = position-1;
                MusicPlayer_fragment.autoScroll(position-1);
            }
        });




*/




        if (song.getArtwork_url().equals("null")) {

            Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png").into(holder.item_song_picture_list);


        }
        else {

            Picasso.with(context).load(song.getArtwork_url()).into(holder.item_song_picture_list);

        }



        holder.artist_item.setText(song.getArtist().toString());
        holder.title_item.setText(song.getTitle().toString());



holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        System.out.println(song.getTitle());

            MusicPlayer_fragment.loadingAnimationFromFragment(true);
        ((MainActivity)context).playSong(song,songsList,false);
        MainActivity.jumpPosition = position;
    }
});

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
    @Override
    public int getItemViewType(int position) {


        return position;
    }




    class RecycleView_Holder extends RecyclerView.ViewHolder{

        ImageView item_song_picture_list,image_previous,image_next,circle ;
        RelativeLayout item_layout ;
        TextView title_item ,artist_item;
       // TextView item_song_name;

        public RecycleView_Holder(View itemView)  {
            super(itemView);

            item_song_picture_list = (ImageView) itemView.findViewById(R.id.item_song_picture_list);
            image_previous = (ImageView) itemView.findViewById(R.id.image_previous);
            image_next = (ImageView) itemView.findViewById(R.id.image_next);
            circle = (ImageView) itemView.findViewById(R.id.circle);
            item_layout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
            artist_item = (TextView) itemView.findViewById(R.id.artist_item);
            title_item = (TextView) itemView.findViewById(R.id.title_item);
           // item_song_name = (TextView) itemView.findViewById(R.id.item_song_name);










            itemView.setTag(this);

        }



    }








}
