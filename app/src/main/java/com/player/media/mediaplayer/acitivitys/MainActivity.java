package com.player.media.mediaplayer.acitivitys;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.adapters.TabAdapter;
import com.player.media.mediaplayer.adapters.TabLayoutAdapter;
import com.player.media.mediaplayer.fragments.FragmentAlbum;
import com.player.media.mediaplayer.fragments.FragmentArtist;
import com.player.media.mediaplayer.fragments.FragmentGenres;
import com.player.media.mediaplayer.fragments.FragmentPlayList;
import com.player.media.mediaplayer.fragments.FragmentSong;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_main;
    private TabLayout tb_main;

    private TabLayoutAdapter tabAdapter;

    final int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_FOLDER);
        } else {
            initView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_FOLDER:{
                initView();
                break;
            }
        }
    }

    private void initView() {
        vp_main = findViewById(R.id.vp_main);
        tb_main = findViewById(R.id.tb_main);

        tabAdapter = new TabLayoutAdapter(getSupportFragmentManager(), this);
        vp_main.setAdapter(tabAdapter);

        tb_main.setupWithViewPager(vp_main);

        tabAdapter.addViewFragment(new FragmentSong(),      this.getResources().getString(R.string.title_fragSong));
        tabAdapter.addViewFragment(new FragmentArtist(),    this.getResources().getString(R.string.title_fragArtist));
        tabAdapter.addViewFragment(new FragmentAlbum(),     this.getResources().getString(R.string.title_fragAlbums));
        tabAdapter.addViewFragment(new FragmentGenres(),    this.getResources().getString(R.string.title_fragGenres));
        tabAdapter.addViewFragment(new FragmentPlayList(),  this.getResources().getString(R.string.title_fragPlaylists));
    }
}
