package com.player.media.mediaplayer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.adapters.RCAdapterAlbums;
import com.player.media.mediaplayer.interfaces.MyOnClick;
import com.player.media.mediaplayer.models.MyAlbums;
import com.player.media.mediaplayer.models.MyArtists;
import com.player.media.mediaplayer.utils.DungLib;

import java.util.ArrayList;

public class FragmentAlbum extends Fragment {
    private Context context;

    private View mrootView;
    private RecyclerView rcAlbums;
    private RCAdapterAlbums adapter;
    private ArrayList<MyAlbums> arrAlbums;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mrootView = inflater.inflate(R.layout.fragment_albums, container, false);
        InitView(mrootView);
        InitArr();

        rcAlbums.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rcAlbums.setLayoutManager(layoutManager);

        adapter = new RCAdapterAlbums(context, arrAlbums, new MyOnClick() {
            @Override
            public void OnClick(int position) {

            }
        });

        rcAlbums.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return mrootView;
    }

    private void InitArr() {
        arrAlbums = new ArrayList<>();
        arrAlbums.addAll(DungLib.getAlbums(context));
    }

    private void InitView(View mrootView) {
        rcAlbums = mrootView.findViewById(R.id.rc_Albums);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
