package com.example.bechirkaddech.musicapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.Entity.Utils;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Home_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //Array list
    ArrayList<Song> songs_top = new ArrayList<>();
    ArrayList<Song> songs_trending = new ArrayList<>();
    ArrayList<Song> songs_trending_audio = new ArrayList<>();
    ArrayList<Song> songs_top_audio = new ArrayList<>();


    //GUI
    RecyclerView recycle_trending, recycle_top,recycle_top_audio,recycle_trending_audio;
    TextView see_one,see_two,see_three,see_four ;
    ImageView laoding_home ;
    Animation rotation ;
    ScrollView home_scroll ;
    NativeExpressAdView adView ;
    AdRequest adRequest;
    private InterstitialAd mInterstitialAd;




    public Home_fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Home_fragment newInstance(String param1, String param2) {
        Home_fragment fragment = new Home_fragment();
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
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        MobileAds.initialize(getActivity(), "ca-app-pub-5006866391263263~5301011236");

        adView = (NativeExpressAdView) view.findViewById(R.id.adView) ;
         adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Text View
        see_one = (TextView) view.findViewById(R.id.see_one);
        see_two = (TextView) view.findViewById(R.id.see_two);
        see_three = (TextView) view.findViewById(R.id.see_three);
        see_four = (TextView) view.findViewById(R.id.see_four);
        laoding_home = (ImageView) view.findViewById(R.id.laoding_home);
        home_scroll = (ScrollView) view.findViewById(R.id.home_scroll);

        laoding_home.setVisibility(View.VISIBLE);
        home_scroll.setVisibility(View.GONE);

        rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);


        rotation.setRepeatCount(Animation.INFINITE);
        laoding_home.startAnimation(rotation);


        MainActivity.CheckMediaPlayerIsPlaying();


        see_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeeAll_fragment.songs_all =  songs_trending ;
                getFragmentManager().beginTransaction().replace(R.id.contentContainer,new SeeAll_fragment()).commit();

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
        });

        see_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeAll_fragment.songs_all =  songs_top ;
                getFragmentManager().beginTransaction().replace(R.id.contentContainer,new SeeAll_fragment()).commit();

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
        });

        see_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeAll_fragment.songs_all =  songs_trending_audio ;
                getFragmentManager().beginTransaction().replace(R.id.contentContainer,new SeeAll_fragment()).commit();

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
        });

        see_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeAll_fragment.songs_all =  songs_top_audio ;
                getFragmentManager().beginTransaction().replace(R.id.contentContainer,new SeeAll_fragment()).commit();
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
        });






        //Recycle view managment
        recycle_trending = (RecyclerView) view.findViewById(R.id.recycle_trending);
        recycle_top = (RecyclerView) view.findViewById(R.id.recycle_top);
        recycle_trending_audio = (RecyclerView) view.findViewById(R.id.recycle_trending_audio);
        recycle_top_audio = (RecyclerView) view.findViewById(R.id.recycle_top_audio);

        LinearLayoutManager lm_trending = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lm_top = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lm_top_audio = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lm_trending_audio = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recycle_trending.setLayoutManager(lm_trending);
        recycle_top.setLayoutManager(lm_top);
        recycle_trending_audio.setLayoutManager(lm_trending_audio);
        recycle_top_audio.setLayoutManager(lm_top_audio);




        fetch_songs(Utils.trending_songs_url, 1);
        fetch_songs(Utils.top_songs_url, 2);
        fetch_songs(Utils.trending_audio_songs_url, 3);
        fetch_songs(Utils.top_audio_songs_url, 4);

        return view;
    }


    public void fetch_songs(String url, final int hint) {
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
                                if (hint == 1) {
                                    songs_trending.add(song);


                                    recycle_trending.setAdapter(new MyAdapter(getActivity(), R.layout.recycle_item, songs_trending));

                                }

                                if (hint == 2) {
                                    songs_top.add(song);


                                    recycle_top.setAdapter(new MyAdapter(getActivity(), R.layout.recycle_item, songs_top));

                                }

                                if (hint == 3) {
                                    songs_trending_audio.add(song);


                                    recycle_trending_audio.setAdapter(new MyAdapter(getActivity(), R.layout.recycle_item, songs_trending_audio));

                                }

                                if (hint == 4) {
                                    songs_top_audio.add(song);


                                    recycle_top_audio.setAdapter(new MyAdapter(getActivity(), R.layout.recycle_item, songs_top_audio));

                                }

                                laoding_home.setVisibility(View.GONE);
                                laoding_home.clearAnimation();
                                home_scroll.setVisibility(View.VISIBLE);


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


}
