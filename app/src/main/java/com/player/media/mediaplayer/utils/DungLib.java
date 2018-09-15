package com.player.media.mediaplayer.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.player.media.mediaplayer.models.Artists;
import com.player.media.mediaplayer.models.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DungLib {

//    private ArrayList<Song> arrSong;

    /*TODO: Set type show RecyclerView*/
    public static void SetDataRCShow(Context context, RecyclerView rc) {
        rc.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rc.setLayoutManager(layoutManager);

        /*gạch chia dòng*/
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,layoutManager.getOrientation());
        rc.addItemDecoration(dividerItemDecoration);
    }

    public static ArrayList<Song> getSongList(Context context) {
        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor musicCursor = musicResolver.query(musicUri, null, selection, null, sortOrder);
        ArrayList<Song> arrSong = new ArrayList<>();
        if (musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int timeColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int dataColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int albumIdColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                int time = musicCursor.getInt(timeColumn);
                String data = musicCursor.getString(dataColumn);
                String album = musicCursor.getString(albumColumn);
                String albumId = musicCursor.getString(albumIdColumn);

//                Uri uri = MediaStore.Audio.Genres.getContentUriForAudioId("external", (int) thisId);
//                Cursor genresCursor = context.getContentResolver().query(uri, genresProjection, null, null, null);
//                int genreIdColumn = genresCursor.getColumnIndex(MediaStore.Audio.Genres._ID);
//                int genreColumn = genresCursor.getColumnIndex(MediaStore.Audio.Genres.NAME);
//                int genreId = 0;
//                String genreName = "";
//                if(genresCursor.moveToFirst()){
//                    do{
//                        Log.e("First", "true");
//                        genreId = genresCursor.getInt(genreIdColumn);
//                        Log.e("First", genreId + "");
//                        genreName = genresCursor.getString(genreColumn);
//                        Log.e("First", genreName);
//                    }while (genresCursor.moveToNext() && genreName.equals(""));
//                }
                arrSong.add(new Song(thisId, data, thisTitle, time, thisArtist, album, albumId));

            } while (musicCursor.moveToNext());
            musicCursor.close();
        }
        Collections.sort(arrSong, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o1.getNameSong().toLowerCase().compareTo(o2.getNameSong().toLowerCase());
            }
        });
        return  arrSong;
    }

    public static ArrayList<Artists> getArtistsList(Context context){


        ContentResolver musicResolver = context.getContentResolver();

        Uri musicExUri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.ArtistColumns.ARTIST;
        String sortOder = "LOWER(" + MediaStore.Audio.ArtistColumns.ARTIST + ") ASC";

        ArrayList<Artists>  arrArts = new ArrayList<>();
        Cursor mArtCursor = musicResolver.query(musicExUri, null, null, null, sortOder);

        if (mArtCursor.moveToFirst()) {
            int nameArt = mArtCursor.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST);
            int keyArt = mArtCursor.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST_KEY);
            int numOfAlbums = mArtCursor.getColumnIndex(MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS);
            int numOfTracks = mArtCursor.getColumnIndex(MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS);

            do {
                String name = mArtCursor.getString(nameArt);
                int  id  = mArtCursor.getInt(keyArt);
                int numAlbums = mArtCursor.getInt(numOfAlbums);
                int numTracks = mArtCursor.getInt(numOfTracks);
                Log.e("12635", "getArtistsList: " + numAlbums  +  " " + name);
                arrArts.add(new Artists(id, name));

            } while (mArtCursor.moveToNext());
        }

        mArtCursor.close();

        return arrArts;
    }

}
