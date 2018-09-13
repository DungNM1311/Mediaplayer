package com.player.media.mediaplayer.models;

public class Song {
    private long id;
    private String path;
    private String nameSong;
    private int timeMusic;
    private String nameArtist;
    private String album;
    private String getAlbumId;
    private String Title;
    private boolean isSelection = false;


    public Song(long id, String path, String nameSong, int timeMusic, String nameArtist, String album, String getAlbumId) {
        this.id = id;
        this.path = path;
        this.nameSong = nameSong;
        this.timeMusic = timeMusic;
        this.nameArtist = nameArtist;
        this.album = album;
        this.getAlbumId = getAlbumId;
    }

    public Song(String title, boolean isSelection) {
        Title = title;
        this.isSelection = isSelection;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isSelection() {
        return isSelection;
    }

    public void setSelection(boolean selection) {
        isSelection = selection;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public int getTimeMusic() {
        return timeMusic;
    }

    public void setTimeMusic(int timeMusic) {
        this.timeMusic = timeMusic;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGetAlbumId() {
        return getAlbumId;
    }

    public void setGetAlbumId(String getAlbumId) {
        this.getAlbumId = getAlbumId;
    }
}
