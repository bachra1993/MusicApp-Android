package com.example.bechirkaddech.musicapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bechirkaddech.musicapp.Alarm.MyBroadcastReceiver;
import com.example.bechirkaddech.musicapp.Entity.Song;
import com.example.bechirkaddech.musicapp.Entity.Utils;
import com.example.bechirkaddech.musicapp.Fragments.AudioCategory_fragment;
import com.example.bechirkaddech.musicapp.Fragments.Favorites_Fragment;
import com.example.bechirkaddech.musicapp.Fragments.Home_fragment;
import com.example.bechirkaddech.musicapp.Fragments.MusicCategory_fragment;
import com.example.bechirkaddech.musicapp.Fragments.MusicPlayer_fragment;
import com.example.bechirkaddech.musicapp.Fragments.Search_fragment;
import com.example.bechirkaddech.musicapp.Notification.NotificationPanel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triggertrap.seekarc.SeekArc;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mediaPlayer;
    public static int jumpPosition;
    public static String playingFrom;


    //gui
    TextView widget_song_title, widget_song_artist,  tv_timer;
    ImageView widget_song_artwork;
   public static TextView fragment_title;
    public static  ImageView  play_btn_widget,btn_back;
    TabLayout tab_layout;
    static LinearLayout song_playing_layout;
    TabItem test;
    SeekArc seekArc;
    ImageView btn_sleeper,btn_search,btn_close;
    Button btn_done;
    EditText search_input ;
    static Animation rotation ;
    SharedPreferences mPrefs;
    AppBarLayout appbar ;
    Switch switch_slepper;

    private InterstitialAd mInterstitialAd;
    AdRequest adRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(MainActivity.this, "ca-app-pub-5006866391263263~5301011236");

        widget_song_artist = (TextView) findViewById(R.id.widget_song_artist);
        widget_song_title = (TextView) findViewById(R.id.widget_song_title);
        widget_song_artwork = (ImageView) findViewById(R.id.widget_song_artwork);
        play_btn_widget = (ImageView) findViewById(R.id.play_btn_widget);
        fragment_title = (TextView) findViewById(R.id.fragment_title);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        song_playing_layout = (LinearLayout) findViewById(R.id.song_playing_layout);
        btn_sleeper = (ImageView) findViewById(R.id.btn_sleeper);
        btn_search = (ImageView) findViewById(R.id.btn_search);
        btn_close = (ImageView) findViewById(R.id.btn_close);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        search_input = (EditText) findViewById(R.id.search_input);
        appbar = (AppBarLayout) findViewById(R.id.appbar);

        appbar.setVisibility(View.VISIBLE);


        mPrefs = getSharedPreferences("mypref",MODE_PRIVATE);

        rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);


        if (isNetworkAvailable() == false) {

            noInternet();

        }
        else {
            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new Home_fragment(), "Home").commit();
            song_playing_layout.setVisibility(View.GONE);
        }


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AudioCategory_fragment myFragmentAudio = (AudioCategory_fragment)getFragmentManager().findFragmentByTag("Audio");
                MusicCategory_fragment myFragmentMusic = (MusicCategory_fragment)getFragmentManager().findFragmentByTag("Music");



                if (myFragmentAudio != null && myFragmentAudio.isVisible()) {
                    getFragmentManager().beginTransaction().replace(R.id.contentContainer, new AudioCategory_fragment(),"Audio").commit();
                    fragment_title.setText("Aduio");
                    btn_back.setVisibility(View.INVISIBLE);


                }
                else {
                    getFragmentManager().beginTransaction().replace(R.id.contentContainer, new MusicCategory_fragment(),"Music").commit();
                    fragment_title.setText("Music");
                    btn_back.setVisibility(View.INVISIBLE);

                }

            }
        });


        Gson gson = new Gson();
        String json = mPrefs.getString("MySong", "");

        if (json != ""){
            Song obj = gson.fromJson(json, Song.class);


            widget_song_title.setText(obj.getTitle().toString());
            widget_song_artist.setText(obj.getArtist().toString());
            Picasso.with(MainActivity.this).load(obj.getArtwork_url()).into(widget_song_artwork);

            play_btn_widget.setImageResource(R.drawable.ic_pause_24dp);
        }







        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_close.setVisibility(View.VISIBLE);
                search_input.setVisibility(View.VISIBLE);
                btn_search.setVisibility(View.GONE);
                btn_sleeper.setVisibility(View.GONE);
                fragment_title.setVisibility(View.GONE);
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_close.setVisibility(View.GONE);
                search_input.setVisibility(View.GONE);
                btn_search.setVisibility(View.VISIBLE);
                btn_sleeper.setVisibility(View.VISIBLE);
                fragment_title.setVisibility(View.VISIBLE);

            }
        });

        search_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
              /* Write your logic here that will be executed when user taps next button */

                    String search = search_input.getText().toString().trim();

              Search_fragment.queryString =  search ;
                    getFragmentManager().beginTransaction().replace(R.id.contentContainer, new Search_fragment()).commit();


                    View view = MainActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    handled = true;
                }

                return handled;
            }
        });








        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();


                switch (pos) {

                    case 0:


                        tab_layout.getTabAt(0).getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(2).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(3).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        if (isNetworkAvailable() == false) {
                            noInternet();
                        }else {
                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new Home_fragment(), "Home").commit();
                            fragment_title.setText("Home");
                            CheckMediaPlayerIsPlaying();
                            btn_back.setVisibility(View.INVISIBLE);
                        }

                        appbar.setVisibility(View.VISIBLE);

                        return;
                    case 1:


                        tab_layout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(1).getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(2).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(3).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                        if (isNetworkAvailable() == false) {

                            noInternet();
                        }else {
                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new Favorites_Fragment(), "Favorites").commit();
                            fragment_title.setText("Favorites");
                            CheckMediaPlayerIsPlaying();
                            btn_back.setVisibility(View.INVISIBLE);
                        }

                        appbar.setVisibility(View.VISIBLE);


                        return;
                    case 2:

                        tab_layout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(2).getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(3).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

                        if (isNetworkAvailable() == false) {

                            noInternet();
                        } else{
                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new MusicCategory_fragment(), "Music").commit();
                        fragment_title.setText("Music");
                        playingFrom = "Music";
                        CheckMediaPlayerIsPlaying();
                        btn_back.setVisibility(View.INVISIBLE);
                }

                        appbar.setVisibility(View.VISIBLE);


                        return;

                    case 3:
                        tab_layout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(2).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tab_layout.getTabAt(3).getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                        if (isNetworkAvailable() == false) {

                            noInternet();
                        }else {
                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new AudioCategory_fragment(), "Aduio").commit();

                            fragment_title.setText("Audio");
                            playingFrom = "Audio";
                            CheckMediaPlayerIsPlaying();
                            btn_back.setVisibility(View.INVISIBLE);

                        }
                        appbar.setVisibility(View.VISIBLE);



                        return;


                    default:
                        return;


                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();


                switch (pos) {

                    case 0:

                        if (isNetworkAvailable() == false) {

                            noInternet();

                        }else {
                            CheckMediaPlayerIsPlaying();

                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new Home_fragment(), "Home").commit();
                            btn_back.setVisibility(View.INVISIBLE);
                        }


                        appbar.setVisibility(View.VISIBLE);

                        return;
                    case 1:
                        if (isNetworkAvailable() == false) {

                            noInternet();
                        }else {
                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new Favorites_Fragment(), "Favorites").commit();
                            CheckMediaPlayerIsPlaying();
                            btn_back.setVisibility(View.INVISIBLE);
                        }

                        appbar.setVisibility(View.VISIBLE);

                        return;
                    case 2:
                        if (isNetworkAvailable() == false) {

                            noInternet();
                        }else {

                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new MusicCategory_fragment(), "Music").commit();
                            CheckMediaPlayerIsPlaying();
                            btn_back.setVisibility(View.INVISIBLE);
                            fragment_title.setText("Music");

                        }


                        appbar.setVisibility(View.VISIBLE);

                        return;

                    case 3:
                        if (isNetworkAvailable() == false) {

                            noInternet();
                        }
                            else {
                            getFragmentManager().beginTransaction().replace(R.id.contentContainer, new AudioCategory_fragment(), "Audio").commit();
                            CheckMediaPlayerIsPlaying();
                            btn_back.setVisibility(View.INVISIBLE);
                            fragment_title.setText("Audio");
                        }


                        appbar.setVisibility(View.VISIBLE);

                        return;


                    default:
                        return;


                }

            }
        });


        btn_sleeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genreListDialogue();


            }
        });

        play_btn_widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {

                        mediaPlayer.pause();
                        play_btn_widget.setImageResource(R.drawable.ic_play_arrow_24dp);

                    } else {
                        play_btn_widget.setImageResource(R.drawable.ic_pause_24dp);

                        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                        mediaPlayer.start();
                    }
                }
            }
        });





        song_playing_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                MusicPlayer_fragment.jumpPosition = jumpPosition;
                MusicPlayer_fragment.playingFrom = playingFrom;
                getFragmentManager().beginTransaction().replace(R.id.contentContainer, new MusicPlayer_fragment(),"Player").commit();
                song_playing_layout.setVisibility(View.GONE);
                appbar.setVisibility(View.GONE);




                adRequest = new AdRequest.Builder().build();


                mInterstitialAd = new InterstitialAd(MainActivity.this);
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

    }


    public static void loadingAnimation(Boolean animate){

        if(animate == true) {
            song_playing_layout.setEnabled(false);
            play_btn_widget.setImageResource(R.drawable.ic_camera_slamm);
            rotation.setRepeatCount(Animation.INFINITE);
            play_btn_widget.startAnimation(rotation);


        }
        else {
            song_playing_layout.setEnabled(true);

            play_btn_widget.setImageResource(R.drawable.ic_pause_24dp);

            play_btn_widget.clearAnimation();

        }
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void playSong(final Song song, final ArrayList<Song> songsList, final Boolean showPlayer) {

        //save to shared
        NotificationPanel nPanel = new NotificationPanel(MainActivity.this);


        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(song);
        prefsEditor.putString("MySong", json);
        prefsEditor.commit();

        releaseMP();
        mediaPlayer = new MediaPlayer();
        if (showPlayer == true) {
            song_playing_layout.setVisibility(View.VISIBLE);

        }
        MusicPlayer_fragment.songs_top = songsList;
        widget_song_artist.setText(song.getArtist().toString());
        widget_song_title.setText(song.getTitle().toString());
        play_btn_widget.setImageResource(R.drawable.ic_pause_24dp);


        if (song.getArtwork_url().equals("null")) {
            Picasso.with(MainActivity.this).load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png").into(widget_song_artwork);


        }
        else {

            Picasso.with(MainActivity.this).load(song.getArtwork_url()).into(widget_song_artwork);

        }





        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            loadingAnimation(true);
            mediaPlayer.setDataSource(song.getStream_url() + "/stream?client_id=" + Utils.client_id);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                    if (showPlayer == true) {
                        song_playing_layout.setVisibility(View.VISIBLE);

                    }
                    MusicPlayer_fragment.songs_top = songsList;
                    widget_song_artist.setText(song.getArtist().toString());
                    widget_song_title.setText(song.getTitle().toString());
                    play_btn_widget.setImageResource(R.drawable.ic_pause_24dp);


                    MusicPlayer_fragment myFragment = (MusicPlayer_fragment)getFragmentManager().findFragmentByTag("Player");

                    Home_fragment myFragmentHome = (Home_fragment)getFragmentManager().findFragmentByTag("Home");



                    if (myFragmentHome != null && myFragmentHome.isVisible()) {

                       song_playing_layout.setVisibility(View.VISIBLE);
                    }


                    if (myFragment != null && myFragment.isVisible()) {

                       MusicPlayer_fragment.loadingAnimationFromFragment(false);

                        // add your code here
                    }



                    loadingAnimation(false);
                }
            });
            mediaPlayer.prepareAsync();

            //check if media player ended and change image
           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    MusicPlayer_fragment myFragment = (MusicPlayer_fragment)getFragmentManager().findFragmentByTag("Player");



                    if (myFragment != null && myFragment.isVisible()) {

                        song_playing_layout.setVisibility(View.GONE);
                        Song nextSong = songsList.get(jumpPosition+1);
                        jumpPosition = jumpPosition+1 ;
                        playSong(nextSong , songsList , false );
                       MusicPlayer_fragment.autoScroll(jumpPosition);

                        // add your code here
                    }
                    else {
                        song_playing_layout.setVisibility(View.GONE);
                        Song nextSong = songsList.get(jumpPosition+1);
                        jumpPosition = jumpPosition+1 ;
                        playSong(nextSong , songsList , true );

                    }



                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }
        //click on song playing

    }


    //dialogue sleeper

    public void genreListDialogue() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_sleeper, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();


        seekArc = (SeekArc) dialogView.findViewById(R.id.seekArc);
        tv_timer = (TextView) dialogView.findViewById(R.id.timer);
        btn_done = (Button) dialogView.findViewById(R.id.btn_done);
        switch_slepper = (Switch) dialogView.findViewById(R.id.switch_sleeper);


        long timer = seekArc.getProgress() / 1000;
        long secondd = (timer / 1000) % 60;
        long minuted = (timer / (1000 * 60)) % 60;
        String timerFormat = String.format("%02d:%02d", minuted, secondd);
        tv_timer.setText(timerFormat);

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 200, intent, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);



        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        seekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {

                if (b == true) {
                    long timer = i / 1000;
                    long secondd = (i / 1000) % 60;
                    long minuted = (i / (1000 * 60)) % 60;
                    String timerFormat = String.format("%02d:%02d", minuted, secondd);
                    tv_timer.setText(timerFormat);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {

            }
        });



        Boolean switchPreferences = mPrefs.getBoolean("active",false);
        Integer progressPreferences = mPrefs.getInt("progress",0);


        if (switchPreferences == true) {
            seekArc.setProgressColor(Color.RED);

            switch_slepper.setChecked(true);
            seekArc.setProgress(progressPreferences);




            long seconddP = (progressPreferences / 1000) % 60;
            long minutedP = (progressPreferences / (1000 * 60)) % 60;
            String timerFormatP = String.format("%02d:%02d", minutedP, seconddP);
            tv_timer.setText(timerFormatP);

        }





        switch_slepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    seekArc.setProgressColor(Color.RED);


                    int sleepTime = seekArc.getProgress();


                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                            + (sleepTime), pendingIntent);

                    long seconddP = (sleepTime / 1000) % 60;
                    long minutedP = (sleepTime / (1000 * 60)) % 60;
                    String timerFormatP = String.format("%02d:%02d", minutedP, seconddP);
                    Toast.makeText(getBaseContext(), "Sleep timer set in " + sleepTime / 1000, Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor prefsEditor = mPrefs.edit();

                    prefsEditor.putBoolean("active", true);
                    prefsEditor.putInt("progress", sleepTime);
                    prefsEditor.commit();






                    System.out.println("checked");

                }
                else {
                    seekArc.setProgressColor(Color.BLACK);
                    System.out.println("not");

                    SharedPreferences.Editor prefsEditor = mPrefs.edit();

                    prefsEditor.putBoolean("active", false);
                    prefsEditor.putInt("progress", 0);

                    prefsEditor.commit();
                    alarmManager.cancel(pendingIntent);



                }
            }
        });










        alertDialog.show();
    }




    public static void  CheckMediaPlayerIsPlaying(){

        if(mediaPlayer != null ) {
            if (mediaPlayer.isPlaying() == true ) {
                song_playing_layout.setVisibility(View.VISIBLE);


            }else {
                song_playing_layout.setVisibility(View.GONE);

            }

        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void noInternet() {
        song_playing_layout.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        builder.setMessage("Connect error, please check your internet connection.")
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setBackgroundColor(Color.RED);
        pbutton.setTextColor(Color.WHITE);


    }

    @Override
    protected void onResume() {
        super.onResume();


        if (isNetworkAvailable() == false) {

            noInternet();

        }


    }


    public static void playPause () {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {

                mediaPlayer.pause();
                play_btn_widget.setImageResource(R.drawable.ic_play_arrow_24dp);

            } else {
                play_btn_widget.setImageResource(R.drawable.ic_pause_24dp);

                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                mediaPlayer.start();
            }
        }
    }
}
