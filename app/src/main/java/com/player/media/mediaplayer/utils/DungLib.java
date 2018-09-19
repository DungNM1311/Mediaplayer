package com.player.media.mediaplayer.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.player.media.mediaplayer.models.MyAlbums;
import com.player.media.mediaplayer.models.MyArtists;
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());
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
        return arrSong;
    }

    public static ArrayList<MyArtists> getArtistsList(Context context) {
        ContentResolver musicResolver = context.getContentResolver();

        Uri musicExUri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String sortOder = "LOWER(" + MediaStore.Audio.ArtistColumns.ARTIST + ") ASC";

        ArrayList<MyArtists> arrArts = new ArrayList<>();
        Cursor mArtCursor = musicResolver.query(musicExUri, null, null, null, sortOder);

        if (mArtCursor != null && mArtCursor.moveToFirst()) {
            int nameArt = mArtCursor.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST);
            int keyArt = mArtCursor.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST_KEY);

            do {
                String name = mArtCursor.getString(nameArt);
                String id = mArtCursor.getString(keyArt);

                Uri songArtUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                ArrayList<Song> arrSongArt = new ArrayList<>();
                String selectionSongArt = MediaStore.Audio.AudioColumns.ARTIST + " = " + "'" + mArtCursor.getString(nameArt) + "'" ;
                String sortOderSong = "LOWER(" + MediaStore.Audio.AudioColumns.TITLE + ") ASC";

                Cursor mSongArtCursor = musicResolver.query(songArtUri, null, selectionSongArt, null, sortOderSong);
                if (mSongArtCursor != null && mSongArtCursor.moveToFirst()) {
                    arrSongArt.clear();
                    int titleColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int idColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media._ID);
                    int artistColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int timeColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
                    int dataColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                    int albumColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                    int albumIdColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                    do {
                        long thisId =       mSongArtCursor.getLong(idColumn);
                        String thisTitle =  mSongArtCursor.getString(titleColumn);
                        String thisArtist = mSongArtCursor.getString(artistColumn);
                        int time =          mSongArtCursor.getInt(timeColumn);
                        String data =       mSongArtCursor.getString(dataColumn);
                        String album =      mSongArtCursor.getString(albumColumn);
                        String albumId =    mSongArtCursor.getString(albumIdColumn);

                        arrSongArt.add(new Song(thisId, data, thisTitle, time, thisArtist, album, albumId));
                    } while (mSongArtCursor.moveToNext());
                    mSongArtCursor.close();
                }

                arrArts.add(new MyArtists(id, name, arrSongArt.size(), arrSongArt));

            } while (mArtCursor.moveToNext());
        }

        mArtCursor.close();

        return arrArts;
    }

    public static ArrayList<MyAlbums> getAlbums(Context context){
        ContentResolver resolver = context.getContentResolver();

        ArrayList<MyAlbums> arrAlbums = new ArrayList<>();

        Uri uriAlbum = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String oder = MediaStore.Audio.AlbumColumns.ALBUM + " ASC";

        Cursor cursorAlbums = resolver.query(uriAlbum, null, null, null, oder);

        if(cursorAlbums != null && cursorAlbums.moveToFirst()){
            int colName = cursorAlbums.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM);
            int colId = cursorAlbums.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_KEY);
            int colNameArt = cursorAlbums.getColumnIndex(MediaStore.Audio.AlbumColumns.ARTIST);
            int colTotalSongs = cursorAlbums.getColumnIndex(MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS);
            int colImgAlbum = cursorAlbums.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);

            do {
                int id = cursorAlbums.getInt(colId);
                String nameAlbum = cursorAlbums.getString(colName);
                String imgAlbum = cursorAlbums.getString(colImgAlbum);
                String nameArt = cursorAlbums.getString(colNameArt);
                int totalSong = cursorAlbums.getInt(colTotalSongs);

                Log.e("3123123", "getAlbums: " + imgAlbum);

                String selection = MediaStore.Audio.AudioColumns.ALBUM + " = " + "'" + cursorAlbums.getString(colName) + "'" ;

                arrAlbums.add(new MyAlbums(id, nameAlbum, nameArt, imgAlbum, totalSong, getArrSong(context,selection)));
            } while(cursorAlbums.moveToNext());
            cursorAlbums.close();
        }

        return arrAlbums;
    }

    private static ArrayList<Song> getArrSong(Context context, String selection){

        ContentResolver resolver = context.getContentResolver();

        Uri songArtUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ArrayList<Song> arrSong = new ArrayList<>();
        String sortOderSong = "LOWER(" + MediaStore.Audio.AudioColumns.TITLE + ") ASC";

        Cursor mSongArtCursor = resolver.query(songArtUri, null, selection, null, sortOderSong);
        if (mSongArtCursor != null && mSongArtCursor.moveToFirst()) {
            arrSong.clear();
            int titleColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int timeColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int dataColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int albumIdColumn = mSongArtCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            do {
                long thisId =       mSongArtCursor.getLong(idColumn);
                String thisTitle =  mSongArtCursor.getString(titleColumn);
                String thisArtist = mSongArtCursor.getString(artistColumn);
                int time =          mSongArtCursor.getInt(timeColumn);
                String data =       mSongArtCursor.getString(dataColumn);
                String album =      mSongArtCursor.getString(albumColumn);
                String albumId =    mSongArtCursor.getString(albumIdColumn);

                arrSong.add(new Song(thisId, data, thisTitle, time, thisArtist, album, albumId));
            } while (mSongArtCursor.moveToNext());
            mSongArtCursor.close();
        }
        return arrSong;
    }

}
