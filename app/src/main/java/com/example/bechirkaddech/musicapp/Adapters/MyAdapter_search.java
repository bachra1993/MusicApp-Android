package com.example.bechirkaddech.musicapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;
import com.example.bechirkaddech.musicapp.Sqlight.MusicDao;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by bechirkaddech on 5/30/17.
 */


public class MyAdapter_search extends RecyclerView.Adapter<MyAdapter_search.RecycleView_Holder> {

    Context context;
    int ressource;
    ArrayList<Song> songsList;
    AdRequest adRequest;
    private InterstitialAd mInterstitialAd;



    public MyAdapter_search(Context context, int ressource, ArrayList<Song> songsList) {
        this.context = context;
        this.ressource = ressource;
        this.songsList = songsList;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_all_item, parent, false);

        RecycleView_Holder vh = new RecycleView_Holder(view);
        MobileAds.initialize(context, "ca-app-pub-5006866391263263~5301011236");
        adRequest = new AdRequest.Builder().build();
        return vh;


    }

    VideoView a;


    @Override
    public void onBindViewHolder(final RecycleView_Holder holder, final int position) {

        final Song song = songsList.get(position);


        holder.all_song_title.setText(song.getTitle());
        holder.all_song_artist.setText(song.getArtist());
        holder.all_likes_count.setText("\u2605"+" "+String.valueOf(song.getLikes_count()));
        holder.all_play_count.setText("\u25b7"+" "+String.valueOf(song.getPlayback_count()));
        if (song.getArtwork_url() != "null") {

            Picasso.with(context).load(song.getArtwork_url()).into(holder.all_artwork);

        }
        else {

            Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png").into(holder.all_artwork);

        }





        holder.adView.setVisibility(View.GONE);

        if (position % 7 == 0) {
            holder.adView.setVisibility(View.VISIBLE);
            holder.adView.loadAd(adRequest);


        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAdd();
                System.out.println(song.getTitle());
                ((MainActivity) context).playSong(song, songsList,true);
                MainActivity.jumpPosition = position;
            }
        });


        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    holder.like.setImageResource(R.drawable.ic_favorite_full_24dp);

                final MusicDao musicDao = new MusicDao(context);

                musicDao.addMusic(song);
                Toast.makeText(context, "Music added to favorites....", Toast.LENGTH_SHORT).show();

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


    class RecycleView_Holder extends RecyclerView.ViewHolder {

        ImageView all_artwork,like;
        TextView all_song_title,all_song_artist,all_likes_count,all_play_count;
        NativeExpressAdView adView ;

        public RecycleView_Holder(View itemView) {
            super(itemView);

            all_artwork = (ImageView) itemView.findViewById(R.id.all_artwork);
            all_song_title = (TextView) itemView.findViewById(R.id.all_song_title);
            all_song_artist = (TextView) itemView.findViewById(R.id.all_song_artist);
            all_likes_count = (TextView) itemView.findViewById(R.id.all_likes_count);
            all_play_count = (TextView) itemView.findViewById(R.id.all_play_count);
            like = (ImageView) itemView.findViewById(R.id.like);
            adView = (NativeExpressAdView) itemView.findViewById(R.id.adView) ;



            itemView.setTag(this);

        }


    }

    public void showAdd() {



        adRequest = new AdRequest.Builder().build();


        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-5006866391263263/8254477632");
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }


        });


    }


}
