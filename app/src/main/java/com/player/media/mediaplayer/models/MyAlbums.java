package com.player.media.mediaplayer.models;

import java.util.ArrayList;

public class MyAlbums {

    private int idAlbum;
    private String imgAlbum;
    private String nameAlbum;
    private String nameArt;
    private int totalSongs;
    private ArrayList<Song> arrSongAlbum;

    public MyAlbums(int idAlbum, String nameAlbum, String nameArt, String imgAlbum, int totalSongs, ArrayList<Song> arrSongAlbum) {
        this.idAlbum = idAlbum;
        this.imgAlbum = imgAlbum;
        this.nameAlbum = nameAlbum;
        this.nameArt = nameArt;
        this.totalSongs = totalSongs;
        this.arrSongAlbum = arrSongAlbum;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getImgAlbum() {
        return imgAlbum;
    }

    public void setImgAlbum(String imgAlbum) {
        this.imgAlbum = imgAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameArt() {
        return nameArt;
    }

    public void setNameArt(String nameArt) {
        this.nameArt = nameArt;
    }

    public int getTotalSongs() {
        return totalSongs;
    }

    public void setTotalSongs(int totalSongs) {
        this.totalSongs = totalSongs;
    }

    public ArrayList<Song> getArrSongAlbum() {
        return arrSongAlbum;
    }

    public void setArrSongAlbum(ArrayList<Song> arrSongAlbum) {
        this.arrSongAlbum = arrSongAlbum;
    }
}
