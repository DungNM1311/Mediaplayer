package com.player.media.mediaplayer.models;

import java.util.ArrayList;

public class MyArtists {
    private String idArtist;
    private String nameArtist;
    private ArrayList<Song> arrSongArt;
    private int totalSong;
    private boolean isSection = false;
    private String titleHeader;

    public MyArtists(String titleHeader, boolean isSection) {
        this.isSection = isSection;
        this.titleHeader = titleHeader;
    }

    public MyArtists(String idArtist, String nameArtist) {
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
    }

    public MyArtists(String idArtist, String nameArtist, int totalSong) {
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
        this.totalSong = totalSong;
    }

    public MyArtists(String idArtist, String nameArtist, int totalSong, ArrayList<Song> arrSongArt) {
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
        this.arrSongArt = arrSongArt;
        this.totalSong = totalSong;
    }

    public MyArtists(String idArtist, String nameArtist, ArrayList<Song> arrSongArt) {
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
        this.arrSongArt = arrSongArt;
    }

    public int getTotalSong() {
        return totalSong;
    }

    public void setTotalSong(int totalSong) {
        this.totalSong = totalSong;
    }

    public String getTitleHeader() {
        return titleHeader;
    }

    public void setTitleHeader(String titleHeader) {
        this.titleHeader = titleHeader;
    }

    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean section) {
        isSection = section;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
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
