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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_all;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_search;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.Entity.Utils;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Search_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Song> songs_search = new ArrayList<>();


    //Gui
    RecyclerView recycle_search ;

    public static  String queryString ;

    ImageView loading_search ;
    Animation rotation ;



    public Search_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Search_fragment newInstance(String param1, String param2) {
        Search_fragment fragment = new Search_fragment();
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
        View view =  inflater.inflate(R.layout.fragment_search_fragment, container, false);

        recycle_search = (RecyclerView) view.findViewById(R.id.recycle_search);
        loading_search = (ImageView) view.findViewById(R.id.loading_search);


        //start animation
        rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        loading_search.setVisibility(View.VISIBLE);

        rotation.setRepeatCount(Animation.INFINITE);
        loading_search.startAnimation(rotation);



        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recycle_search.setLayoutManager(lm);


        fetch_songs("http://api.soundcloud.com/tracks/?q="+queryString+"&client_id=yKFXa2bCoy3VLi79n3mCkw0skDI02yc9&&limit=62");


        return view ;
    }

    public void fetch_songs(String url) {
        final RequestQueue queue = Volley.newRequestQueue(getActivity());

// Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {



                        //collection array
                        for (int i = 0; i < response.length(); i++) {

                            Song song = new Song();

                            try {

                                //song info
                                JSONObject Data = response.getJSONObject(i);
                                song.setTitle(Data.getString("title"));
                                song.setArtwork_url(Data.getString("artwork_url"));
                                song.setDuration(Data.getLong("duration"));
                                song.setPlayback_count(Data.getLong("playback_count"));
                                song.setLikes_count(Data.getLong("likes_count"));
                                song.setStream_url(Data.getString("uri"));
                                JSONObject userInfo = Data.getJSONObject("user");
                                song.setArtist(userInfo.getString("username"));

                                //check what array list to add

                                songs_search.add(song);


                                recycle_search.setAdapter(new MyAdapter_search(getActivity(), R.layout.recycle_item, songs_search));

                                loading_search.setVisibility(View.GONE);

                                loading_search.clearAnimation();




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
