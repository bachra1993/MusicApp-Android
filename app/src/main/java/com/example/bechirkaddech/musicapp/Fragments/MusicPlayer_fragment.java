package com.example.bechirkaddech.musicapp.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bechirkaddech.musicapp.Adapters.MyAdapter;
import com.example.bechirkaddech.musicapp.Adapters.MyAdapter_list;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.Entity.Utils;
import com.example.bechirkaddech.musicapp.MainActivity;
import com.example.bechirkaddech.musicapp.R;

import java.util.ArrayList;


public class MusicPlayer_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Song> songs_top = new ArrayList<>();
    public static int jumpPosition = 0;
    public static String playingFrom;


    public MyAdapter_list myAdapter_list ;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int visiblePosition ;
    static Animation rotation ;

    //Gui

    static RecyclerView recycle_list;
    SeekBar seekBar;
    TextView tv_start, tv_end,tv_playing_from;
    ImageView media_previous;
    ImageView media_next;
    static ImageView media_play;
    ImageView close_view;
    ImageView media_share;


    public MusicPlayer_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicPlayer_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicPlayer_fragment newInstance(String param1, String param2) {
        MusicPlayer_fragment fragment = new MusicPlayer_fragment();
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

    ImageView image_next ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_player_fragment, container, false);

        recycle_list = (RecyclerView) view.findViewById(R.id.recycle_list);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        tv_start = (TextView) view.findViewById(R.id.tv_start);
        tv_playing_from = (TextView) view.findViewById(R.id.tv_playing_from);
        tv_end = (TextView) view.findViewById(R.id.tv_end);
        media_next = (ImageView) view.findViewById(R.id.media_next);
        media_previous = (ImageView) view.findViewById(R.id.media_previous);
        media_play = (ImageView) view.findViewById(R.id.media_paly);
        close_view = (ImageView) view.findViewById(R.id.close_view);
        media_share = (ImageView) view.findViewById(R.id.media_share);




        media_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, songs_top.get(visiblePosition).getStream_url()+ "/stream?client_id=" + Utils.client_id);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


                tv_playing_from.setText(playingFrom);

        //format duration time
        long durationTime = MainActivity.mediaPlayer.getDuration();
        long second = (durationTime / 1000) % 60;
        long minute = (durationTime / (1000 * 60)) % 60;
        String durationTimeFormat = String.format("%02d:%02d", minute, second);
        tv_end.setText(durationTimeFormat);
        //seek bar max duration
        seekBar.setMax(MainActivity.mediaPlayer.getDuration() / 1000);
        //update seek bar

        setRecyclePaging(recycle_list);

updateProgress();


        //seek bar toutch
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //
                if (b == true) {
                    MainActivity.mediaPlayer.seekTo(i * 1000);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //play / pause
        media_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkPlaying();
            }
        });



media_next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (visiblePosition + 1 == songs_top.size()+1) {


        } else {
            recycle_list.post(new Runnable() {
                @Override
                public void run() {
                    // Call smooth scroll
                    visiblePosition = visiblePosition + 1;
                    recycle_list.smoothScrollToPosition(visiblePosition);
                }
            });
        }

    }
});


        media_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (visiblePosition - 1 == -1) {


                } else {

                    recycle_list.post(new Runnable() {
                        @Override
                        public void run() {
                            // Call smooth scroll

                            visiblePosition = visiblePosition - 1;
                            recycle_list.smoothScrollToPosition(visiblePosition);

                        }
                    });


                }
            }
        });




        close_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.contentContainer,new Home_fragment()).commit();
                LinearLayout song_playing_layout =(LinearLayout) getActivity().findViewById(R.id.song_playing_layout);
                TabLayout tab_layout =(TabLayout) getActivity().findViewById(R.id.tab_layout);


                tab_layout.getTabAt(0).getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                tab_layout.getTabAt(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                tab_layout.getTabAt(2).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                tab_layout.getTabAt(3).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                TabLayout.Tab tab = tab_layout.getTabAt(0);
                tab.select();
                MainActivity.CheckMediaPlayerIsPlaying();


            }
        });

        rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);


        return view;
    }


    public void setRecyclePaging(final RecyclerView paging) {

        final LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        paging.setLayoutManager(lm);
        myAdapter_list =  new MyAdapter_list(getActivity(), R.layout.recycle_item, songs_top);
        paging.setAdapter(myAdapter_list);




        autoScroll(jumpPosition);

        paging.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean scrollingUp;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollingUp = dx < 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                 visiblePosition = scrollingUp ? lm.findFirstVisibleItemPosition() : lm.findLastVisibleItemPosition();



                System.out.println("okokok"+String.valueOf(lm.getWidth()));


                int completelyVisiblePosition = scrollingUp ? lm
                        .findFirstCompletelyVisibleItemPosition() : lm
                        .findLastCompletelyVisibleItemPosition();
                if (visiblePosition != completelyVisiblePosition) {
                    recyclerView.smoothScrollToPosition(visiblePosition);
                    return;
                }
            }



        });


    }

    public static void autoScroll(final int position){
        //Auto Scroll to the new Position
        recycle_list.post(new Runnable() {
            @Override
            public void run() {
                // Call smooth scroll
                recycle_list.smoothScrollToPosition(position);
            }
        });

    }

    private Handler mHandler = new Handler();


    public void updateProgress() {
            mHandler.postDelayed(mUpdateTimeTask, 100);



    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {


            if(MainActivity.mediaPlayer.isPlaying()) {


                long currentDuration = MainActivity.mediaPlayer.getCurrentPosition();


                seekBar.setProgress((int) currentDuration / 1000);


                long second = (currentDuration / 1000) % 60;
                long minute = (currentDuration / (1000 * 60)) % 60;
                String currentTimeFormat = String.format("%02d:%02d", minute, second);
                tv_start.setText(currentTimeFormat);


                //format duration time
                long durationTime = MainActivity.mediaPlayer.getDuration();
                long secondd = (durationTime / 1000) % 60;
                long minuted = (durationTime / (1000 * 60)) % 60;
                String durationTimeFormat = String.format("%02d:%02d", minuted, secondd);
                tv_end.setText(durationTimeFormat);

            }
                // Running this thread after 100 milliseconds
                mHandler.postDelayed(this, 100);

        }
    };


    public void checkPlaying() {

        if (MainActivity.mediaPlayer.isPlaying()) {

            MainActivity.mediaPlayer.pause();
            media_play.setImageResource(R.drawable.ic_play_arrow_24dp);

        }else {
            media_play.setImageResource(R.drawable.ic_pause_24dp);

            MainActivity.mediaPlayer.seekTo(MainActivity.mediaPlayer.getCurrentPosition());
            MainActivity.mediaPlayer.start();
        }

    }

    public  static void loadingAnimationFromFragment(Boolean animate){

        if(animate == true) {
            media_play.setImageResource(R.drawable.ic_camera_slamm);
            rotation.setRepeatCount(Animation.INFINITE);
            media_play.startAnimation(rotation);


        }
        else {

            media_play.setImageResource(R.drawable.ic_pause_24dp);

            media_play.clearAnimation();

        }
    }



}
