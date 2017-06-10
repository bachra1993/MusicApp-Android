package com.example.bechirkaddech.musicapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;
import com.example.bechirkaddech.musicapp.Sqlight.MusicDao;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by bechirkaddech on 5/30/17.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecycleView_Holder> {

    Context context;
    int ressource;
    ArrayList<Song> songsList;


    public MyAdapter(Context context, int ressource, ArrayList<Song> songsList) {
        this.context = context;
        this.ressource = ressource;
        this.songsList = songsList;
    }

    @Override
    public RecycleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item, parent, false);

        RecycleView_Holder vh = new RecycleView_Holder(view);

        return vh;


    }

    VideoView a;


    @Override
    public void onBindViewHolder(final RecycleView_Holder holder, final int position) {

        final Song song = songsList.get(position);


        holder.item_song_name.setText(song.getTitle());

        System.out.println("test url image");
        System.out.println(song.getArtwork_url());


        if (song.getArtwork_url() != "null") {

            Picasso.with(context).load(song.getArtwork_url()).into(holder.item_song_picture);

        }
        else {

            Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png").into(holder.item_song_picture);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(song.getTitle());
                ((MainActivity) context).playSong(song, songsList,true);
                MainActivity.jumpPosition = position;
                MainActivity.playingFrom = "Home";

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

        ImageView item_song_picture;
        TextView item_song_name;

        public RecycleView_Holder(View itemView) {
            super(itemView);

            item_song_picture = (ImageView) itemView.findViewById(R.id.item_song_picture);
            item_song_name = (TextView) itemView.findViewById(R.id.item_song_name);


            itemView.setTag(this);

        }


    }


}
