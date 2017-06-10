package com.example.bechirkaddech.musicapp.Entity;

/**
 * Created by bechirkaddech on 5/30/17.
 */

public class Utils {

    static  public String genre ;

    static public String client_id="yKFXa2bCoy3VLi79n3mCkw0skDI02yc9";
    static public String top_songs_url="https://api-v2.soundcloud.com/charts?kind=top&genre=soundcloud:genres:all-music&client_id="+client_id+"&&limit=62";
    static public String trending_songs_url="https://api-v2.soundcloud.com/charts?kind=trending&genre=soundcloud:genres:all-music&client_id="+client_id+"&&limit=62";
    static public String top_audio_songs_url="https://api-v2.soundcloud.com/charts?kind=top&genre=soundcloud:genres:all-audio&client_id="+client_id+"&&limit=62";
    static public String trending_audio_songs_url="https://api-v2.soundcloud.com/charts?kind=trending&genre=soundcloud:genres:all-audio&client_id="+client_id+"&&limit=62";
    static public String category_music_one="https://api-v2.soundcloud.com/charts?kind=top&genre=soundcloud:genres:";
    static public String category_music_two="&client_id="+client_id+"&&limit=62";






}
