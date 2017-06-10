package com.example.bechirkaddech.musicapp.Entity;

/**
 * Created by bechirkaddech on 5/30/17.
 */

public class Song {

    public int id ;
    public String title;
    public String artist;
    public String artwork_url;
    public Long duration;
    public String stream_url;
    public Long likes_count;
    public Long playback_count;
    public Boolean clicked ;


    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

    public Boolean getClicked() {

        return clicked;
    }

    public Song() {

    }

    public Song(int i, String string, String string1, String string2, String string3, String string4, String string5) {

    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setArtwork_url(String artwork_url) {
        this.artwork_url = artwork_url;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
    }

    public void setLikes_count(Long likes_count) {
        this.likes_count = likes_count;
    }

    public void setPlayback_count(Long playback_count) {
        this.playback_count = playback_count;
    }

    public String getTitle() {

        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getArtwork_url() {
        return artwork_url;
    }

    public Long getDuration() {
        return duration;
    }

    public String getStream_url() {
        return stream_url;
    }

    public Long getLikes_count() {
        return likes_count;
    }

    public Long getPlayback_count() {
        return playback_count;
    }

    public Song(int i, String string, String cursorString, String s, String string1) {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public Song(int id, String title, String artist, String artwork_url, Long duration, String stream_url, Long likes_count, Long playback_count) {

        this.id = id;
        this.title = title;
        this.artist = artist;
        this.artwork_url = artwork_url;
        this.duration = duration;
        this.stream_url = stream_url;
        this.likes_count = likes_count;
        this.playback_count = playback_count;
    }
}