package com.player.media.mediaplayer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.interfaces.MyOnClick;
import com.player.media.mediaplayer.models.MyAlbums;

import java.util.ArrayList;

public class RCAdapterAlbums extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<MyAlbums> arrAlbum;
    private MyOnClick mClickAlbumListener;

    private View mrootView;

    public RCAdapterAlbums(Context context, ArrayList<MyAlbums> arrAlbum, MyOnClick mClickAlbumListener) {
        this.context = context;
        this.arrAlbum = arrAlbum;
        this.mClickAlbumListener = mClickAlbumListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        mrootView = inflater.inflate(R.layout.item_album, parent, false);

        return new AlbumViewHolder(mrootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AlbumViewHolder viewHolder = (AlbumViewHolder) holder;

        if (arrAlbum.get(position).getImgAlbum() == null) {
            viewHolder.imgAlbum.setImageResource(R.drawable.icon_user);
        } else {
            Bitmap bm = BitmapFactory.decodeFile(arrAlbum.get(position).getImgAlbum());
            viewHolder.imgAlbum.setImageBitmap(bm);
        }
        viewHolder.txtNameAlbum.setText(arrAlbum.get(position).getNameAlbum());
        viewHolder.txtNameArt.setText(arrAlbum.get(position).getNameArt());
        String totalSongs = arrAlbum.get(position).getTotalSongs() + " " + context.getResources().getString(R.string.song);
        viewHolder.txtTotalSongs.setText(totalSongs);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickAlbumListener.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    private class AlbumViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameAlbum;
        TextView txtNameArt;
        TextView txtTotalSongs;
        ImageView imgAlbum;
        ImageView imgMore;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            txtNameAlbum = itemView.findViewById(R.id.txtNameAlbum_itemAlbum);
            txtNameArt = itemView.findViewById(R.id.txtNameArt_itemAlbum);
            txtTotalSongs = itemView.findViewById(R.id.txtTotalSong_itemAlbum);

            imgAlbum = itemView.findViewById(R.id.imgAlbum_itemAlbum);
            imgMore = itemView.findViewById(R.id.imgMore_itemAlbum);
        }
    }
}
