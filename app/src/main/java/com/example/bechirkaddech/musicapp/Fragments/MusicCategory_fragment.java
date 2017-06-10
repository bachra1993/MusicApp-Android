package com.example.bechirkaddech.musicapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_all;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.Entity.Utils;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MusicCategory_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AdRequest adRequest;
    private InterstitialAd mInterstitialAd;

    ArrayList<Song> songs_category_music = new ArrayList<>();


    //Gui
    RecyclerView recycle_category_music;
    ScrollView music_genre ;
    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20, tv21, tv22;
    TextView tv23, tv24, tv25, tv26, tv27;


    public MusicCategory_fragment() {
        // Required empty public constructor
    }

    public static MusicCategory_fragment newInstance(String param1, String param2) {
        MusicCategory_fragment fragment = new MusicCategory_fragment();
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
        View view = inflater.inflate(R.layout.fragment_music_category_fragment, container, false);
        MobileAds.initialize(getActivity(), "ca-app-pub-5006866391263263~5301011236");


        tv0 = (TextView) view.findViewById(R.id.tv0);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv4 = (TextView) view.findViewById(R.id.tv4);
        tv5 = (TextView) view.findViewById(R.id.tv5);
        tv6 = (TextView) view.findViewById(R.id.tv6);
        tv7 = (TextView) view.findViewById(R.id.tv7);
        tv8 = (TextView) view.findViewById(R.id.tv8);
        tv9 = (TextView) view.findViewById(R.id.tv9);
        tv10 = (TextView) view.findViewById(R.id.tv10);
        tv11 = (TextView) view.findViewById(R.id.tv11);
        tv12 = (TextView) view.findViewById(R.id.tv12);
        tv13 = (TextView) view.findViewById(R.id.tv13);
        tv14 = (TextView) view.findViewById(R.id.tv14);
        tv15 = (TextView) view.findViewById(R.id.tv15);
        tv16 = (TextView) view.findViewById(R.id.tv16);
        tv17 = (TextView) view.findViewById(R.id.tv17);
        tv18 = (TextView) view.findViewById(R.id.tv18);
        tv19 = (TextView) view.findViewById(R.id.tv19);
        tv20 = (TextView) view.findViewById(R.id.tv20);
        tv21 = (TextView) view.findViewById(R.id.tv21);
        tv22 = (TextView) view.findViewById(R.id.tv22);
        tv23 = (TextView) view.findViewById(R.id.tv23);
        tv24 = (TextView) view.findViewById(R.id.tv24);
        tv25 = (TextView) view.findViewById(R.id.tv25);
        tv26 = (TextView) view.findViewById(R.id.tv26);
        tv27 = (TextView) view.findViewById(R.id.tv27);


        recycle_category_music = (RecyclerView) view.findViewById(R.id.recycle_category_music);
        music_genre = (ScrollView) view.findViewById(R.id.music_genre);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycle_category_music.setLayoutManager(lm);


        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv0.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv2.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv2.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv3.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv4.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv5.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv6.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv7.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv8.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv9.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv10.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv11.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv12.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv13.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv14.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv15.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv16.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv17.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv18.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv19.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv20.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv21.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv22.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv23.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv24.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv25.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv26.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });
        tv27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = tv27.getText().toString();
                fetch_songs(Utils.category_music_one+category+Utils.category_music_two);
                ((MainActivity)getActivity()).fragment_title.setText(category);
                ((MainActivity)getActivity()).btn_back.setVisibility(View.VISIBLE);
                showAdd();


            }
        });









        return view;
    }


    public void fetch_songs(String url) {
        final RequestQueue queue = Volley.newRequestQueue(getActivity());

// Request a string response from the provided URL.
        final JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //Collection array
                        JSONArray collection = null;
                        try {
                            collection = response.getJSONArray("collection");
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        //collection array
                        for (int i = 0; i < collection.length(); i++) {

                            Song song = new Song();

                            try {

                                //song info
                                JSONObject Data = collection.getJSONObject(i).getJSONObject("track");
                                song.setTitle(Data.getString("title"));
                                song.setArtwork_url(Data.getString("artwork_url"));
                                song.setDuration(Data.getLong("duration"));
                                song.setPlayback_count(Data.getLong("playback_count"));
                                song.setLikes_count(Data.getLong("likes_count"));
                                song.setStream_url(Data.getString("uri"));
                                JSONObject userInfo = Data.getJSONObject("user");
                                song.setArtist(userInfo.getString("username"));

                                //check what array list to add

                                songs_category_music.add(song);


                                recycle_category_music.setAdapter(new MyAdapter_all(getActivity(), R.layout.recycle_item, songs_category_music));
                                music_genre.setVisibility(View.GONE);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


    public void genreListDialogue() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_genre_list, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();


        final RadioGroup radiogroup = (RadioGroup) dialogView.findViewById(R.id.radiogroup);
        Button btn_search = (Button) dialogView.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int radioButtonID = radiogroup.getCheckedRadioButtonId();
                View radioButton = radiogroup.findViewById(radioButtonID);
                String category = radioButton.getResources().getResourceEntryName(radioButtonID);
                fetch_songs(Utils.category_music_one + category + Utils.category_music_two);
                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }


    public void showAdd() {



        adRequest = new AdRequest.Builder().build();


        mInterstitialAd = new InterstitialAd(getActivity());
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
