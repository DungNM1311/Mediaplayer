package com.player.media.mediaplayer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.adapters.RCAdapterArtists;
import com.player.media.mediaplayer.interfaces.MyOnClick;
import com.player.media.mediaplayer.models.MyArtists;
import com.player.media.mediaplayer.models.Song;
import com.player.media.mediaplayer.utils.DungLib;

import java.util.ArrayList;

public class FragmentArtist  extends Fragment{

    private View mrootView;

    private Context context;
    private RecyclerView rcArt;
    private RCAdapterArtists adapter;
    private ArrayList<MyArtists> arrArt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mrootView = inflater.inflate(R.layout.fragment_artists, container, false);
        InitView(mrootView);
        InitArrArt();

        DungLib.SetDataRCShow(context, rcArt);

        adapter = new RCAdapterArtists(context, arrArt, new MyOnClick() {
            @Override
            public void OnClick(int position) {

            }
        });

        rcArt.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return mrootView;
    }

    /*TODO: Khời tạo mảng dsach nghệ sĩ*/
    private void InitArrArt() {
        arrArt = new ArrayList<>();

        ArrayList<MyArtists> arr = new ArrayList<>();

        arr.addAll(DungLib.getArtistsList(context));

        String lastHeader = "";

        for (int i = 0; i < arr.size(); i++) {

            String h1 = String.valueOf(arr.get(i).getNameArtist().charAt(0)).toUpperCase();
//            String h2 = String.valueOf(arr.get(i + 1).getNameSong().charAt(0)).toUpperCase();
            if (!h1.equals(lastHeader)) {
                lastHeader = h1;
                arrArt.add(new MyArtists(h1, true));
            }

            Log.e("1213241", "InitArrArt: " + arr.get(i).getArrSongArt().size() + arr.get(i).getNameArtist());
            Log.e("213214234", "InitArrArt: " + arr.get(i).getTotalSong()+ arr.get(i).getNameArtist());
            arrArt.add(arr.get(i));
        }

    }

    /*TODO: Khởi tạo view Fragment Artist*/
    private void InitView(View mrootView) {
        rcArt = mrootView.findViewById(R.id.rc_Artists);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
