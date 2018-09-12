package com.player.media.mediaplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.interfaces.OnClickSong;
import com.player.media.mediaplayer.models.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RCAdapterSongs extends RecyclerView.Adapter<RCAdapterSongs.ViewHolder> {

    private ArrayList<Song> arrSong;
    private Context context;
    private OnClickSong mSongOnClickListener;

    private View itemView;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public RCAdapterSongs(ArrayList<Song> arrSong, Context context, OnClickSong mSongOnClickListener) {
        this.arrSong = arrSong;
        this.context = context;
        this.mSongOnClickListener = mSongOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        itemView = inflater.inflate(R.layout.item_song, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.txtNameSong.setText(arrSong.get(position).getNameSong());
        String artistTime = arrSong.get(position).getNameArtist() + " - " + format.format(arrSong.get(position).getTimeMusic());
        holder.txtArtistTime.setText(artistTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSongOnClickListener.OnClickSong(position, arrSong.get(position));
            }
        });
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
}
