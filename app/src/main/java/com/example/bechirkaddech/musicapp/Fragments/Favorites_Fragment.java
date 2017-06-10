package com.example.bechirkaddech.musicapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bechirkaddech.musicapp.Adapters.MyAdapter;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_all;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_favorites;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.R;
import com.example.bechirkaddech.musicapp.Sqlight.MusicDao;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class Favorites_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static MyAdapter_favorites myAdapter_favorites ;

    ArrayList<Song> songs_favorites = new ArrayList<>();
    //gui
    RecyclerView recycle_favorites ;
    TextView text_emty ;



    public Favorites_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Favorites_Fragment newInstance(String param1, String param2) {
        Favorites_Fragment fragment = new Favorites_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorites_, container, false);


        MusicDao musicDao = new MusicDao(getActivity()) ;
        songs_favorites = musicDao.getAllRMusic();







        recycle_favorites = (RecyclerView) view.findViewById(R.id.recycle_favorites);
        text_emty = (TextView) view.findViewById(R.id.text_emty);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycle_favorites.setLayoutManager(lm);
        myAdapter_favorites=new MyAdapter_favorites(getActivity(),R.layout.recycle_item, songs_favorites);

        recycle_favorites.setAdapter(myAdapter_favorites);


        if (songs_favorites.size() == 0) {

            text_emty.setVisibility(View.VISIBLE);
        }




        return view ;
    }




}
