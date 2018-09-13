package com.player.media.mediaplayer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.adapters.RCAdapterSongs;
import com.player.media.mediaplayer.interfaces.OnClickSong;
import com.player.media.mediaplayer.models.Song;
import com.player.media.mediaplayer.utils.DungLib;

import java.util.ArrayList;

public class FragmentSong extends Fragment {

    Context context;
    private View mrootView;
    private ArrayList<Song> arrSong;
    private RecyclerView rcSong;
    private RCAdapterSongs adapterSongs;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mrootView = inflater.inflate(R.layout.fragment_songs, container, false);

        InitView(mrootView);
        InitArrSong();

        DungLib.SetDataRCShow(context, rcSong);

        adapterSongs = new RCAdapterSongs(arrSong, context, new OnClickSong() {
            @Override
            public void OnClickSong(int positon, Song song) {

            }
        });
        rcSong.setAdapter(adapterSongs);
        adapterSongs.notifyDataSetChanged();


        return mrootView;
    }

    private void InitArrSong() {
        arrSong = new ArrayList<>();

        ArrayList<Song> arr = new ArrayList<>();

        arr.addAll(DungLib.getSongList(context));

        String lastHeader = "";

        for (int i = 0; i < arr.size(); i++) {

            String h1 = String.valueOf(arr.get(i).getNameSong().charAt(0)).toUpperCase();
//            String h2 = String.valueOf(arr.get(i + 1).getNameSong().charAt(0)).toUpperCase();
            if (!h1.equals(lastHeader)) {
                lastHeader = h1;
                arrSong.add(new Song(h1, true));
            }
            arrSong.add(arr.get(i));
//            if (i < arr.size() - 1) {
//                String h1 = String.valueOf(arr.get(i).getNameSong().charAt(0)).toUpperCase();
//                String h2 = String.valueOf(arr.get(i + 1).getNameSong().charAt(0)).toUpperCase();
//                if (!h1.equals(h2)) {
//                    arrSong.add(new Song(h1, true));
//                }
//                arrSong.add(arr.get(i));
//            } else {
//                arrSong.add(arr.get(i));
//            }
        }
    }

    private void InitView(View mrootView) {
        rcSong = mrootView.findViewById(R.id.rc_Song);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
