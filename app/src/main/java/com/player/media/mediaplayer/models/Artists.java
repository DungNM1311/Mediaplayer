package com.player.media.mediaplayer.models;

import java.util.ArrayList;

public class Artists {
    private int idArtist;
    private String nameArtist;
    private ArrayList<Song> arrSongArt;

    public Artists(int idArtist, String nameArtist) {
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
    }

    public Artists(int idArtist, String nameArtist, ArrayList<Song> arrSongArt) {
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
        this.arrSongArt = arrSongArt;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public ArrayList<Song> getArrSongArt() {
        return arrSongArt;
    }

    public void setArrSongArt(ArrayList<Song> arrSongArt) {
        this.arrSongArt = arrSongArt;
    }
}
