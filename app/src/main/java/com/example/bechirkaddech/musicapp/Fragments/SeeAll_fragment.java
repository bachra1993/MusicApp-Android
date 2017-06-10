package com.example.bechirkaddech.musicapp.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bechirkaddech.musicapp.Adapters.MyAdapter;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_all;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


public class SeeAll_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Song> songs_all = new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AdView adView ;
    AdRequest adRequest;

    //Gui
    RecyclerView recycle_all ;


    public SeeAll_fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SeeAll_fragment newInstance(String param1, String param2) {
        SeeAll_fragment fragment = new SeeAll_fragment();
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
        View view =  inflater.inflate(R.layout.fragment_see_all_fragment, container, false);



        recycle_all=(RecyclerView) view.findViewById(R.id.recycle_all);


        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycle_all.setLayoutManager(lm);

        recycle_all.setAdapter(new MyAdapter_all(getActivity(), R.layout.recycle_item, songs_all));






        return view ;
    }




}
