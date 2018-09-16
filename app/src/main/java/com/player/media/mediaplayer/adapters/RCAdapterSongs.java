package com.player.media.mediaplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.interfaces.OnClickSong;
import com.player.media.mediaplayer.models.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RCAdapterSongs extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Song> arrSong;
    private Context context;
    private OnClickSong mSongOnClickListener;

    private View songView;
    private View sectionView;
    private View artView;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public static final int SECTION_VIEW = 0;
    public static final int CONTENT_VIEW = 1;

    public RCAdapterSongs(ArrayList<Song> arrSong, Context context, OnClickSong mSongOnClickListener) {
        this.arrSong = arrSong;
        this.context = context;
        this.mSongOnClickListener = mSongOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType){
            case SECTION_VIEW:{
                sectionView = inflater.inflate(R.layout.item_header_title, parent, false);
                return new ViewHolderHearder(sectionView);
            }
            case CONTENT_VIEW:{
                songView = inflater.inflate(R.layout.item_song, parent, false);
                return new ViewHolder(songView);
            }
            default :{
                artView = inflater.inflate(R.layout.item_song, parent, false);
                return new ViewHolder(artView);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (SECTION_VIEW == getItemViewType(position)) {

            ViewHolderHearder sectionHeaderViewHolder = (ViewHolderHearder) holder;
            sectionHeaderViewHolder.txtHearder.setText(arrSong.get(position).getTitle());
            return;
        }

        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.txtNameSong.setText(arrSong.get(position).getNameSong());
        String artistTime = arrSong.get(position).getNameArtist() + " - " + format.format(arrSong.get(position).getTimeMusic());
        viewHolder.txtArtistTime.setText(artistTime);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSongOnClickListener.OnClickSong(position, arrSong.get(position));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(arrSong.get(position).isSelection()){
            return SECTION_VIEW;
        } else {
            return  CONTENT_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameSong;
        TextView txtArtistTime;
        ImageView btnMore;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNameSong = itemView.findViewById(R.id.txtTenBaiHat_itemSong);
            txtArtistTime = itemView.findViewById(R.id.txtArtist_itemSong);
            btnMore = itemView.findViewById(R.id.btnMore_itemSong);
        }
    }

    private class ViewHolderHearder extends RecyclerView.ViewHolder{

        TextView txtHearder;

        public ViewHolderHearder(View itemView) {
            super(itemView);

            txtHearder = itemView.findViewById(R.id.headerTitleTextview);
        }
    }
}
