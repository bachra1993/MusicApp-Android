package com.example.bechirkaddech.musicapp.Sqlight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bechirkaddech.musicapp.Entity.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bechirkaddech on 10/25/16.
 */
public class MusicDao extends DatabaseHandler {

    public MusicDao(Context context) {
        super(context);
    }

    public void addMusic(Song favoriteMusic) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_SONG, favoriteMusic.getTitle());
        values.put(DatabaseHandler.KEY_SINGER, favoriteMusic.getArtist());

        values.put(DatabaseHandler.KEY_PICTURE, favoriteMusic.getArtwork_url());
       values.put(DatabaseHandler.KEY_PREVIEW, favoriteMusic.getStream_url());
        values.put(DatabaseHandler.KEY_LIKES, favoriteMusic.getLikes_count());
        values.put(DatabaseHandler.KEY_PLAY, favoriteMusic.getPlayback_count());


        System.out.println("music added");



        //inserting row
        db.insert(DatabaseHandler.TABLE_MUSIC,null,values);
        db.close();
    }


    public Song getMusic(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHandler.TABLE_MUSIC,new String[]{
                        DatabaseHandler.KEY_ID, DatabaseHandler.KEY_SONG,DatabaseHandler.KEY_SINGER,DatabaseHandler.KEY_PICTURE,
                DatabaseHandler.KEY_PREVIEW,DatabaseHandler.KEY_LIKES,DatabaseHandler.KEY_PLAY
                },KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        Song  musicF = new Song(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6)




        );
        return musicF;
    }





    // Getting All Restaurent
    public ArrayList<Song> getAllRMusic() {


        ArrayList<Song> musicList = new ArrayList<Song>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_MUSIC;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Song music = new Song();
                music.setId(Integer.parseInt(cursor.getString(0)));
                music.setTitle(cursor.getString(1));
                music.setArtist(cursor.getString(2));
                music.setArtwork_url(cursor.getString(3));
                music.setStream_url(cursor.getString(4));



                // Adding contact to list
                musicList.add(music);
            } while (cursor.moveToNext());
        }

        // return contact list
        return musicList;
    }









    public int getMusicCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_MUSIC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }




    public void deleteMusic(Song music) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_MUSIC, KEY_ID + " = ?", new String[] { String.valueOf(music.getId()) });
        db.close();
    }







}
