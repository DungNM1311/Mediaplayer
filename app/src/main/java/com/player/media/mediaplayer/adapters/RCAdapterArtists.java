package com.player.media.mediaplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.player.media.mediaplayer.R;
import com.player.media.mediaplayer.interfaces.MyOnClick;
import com.player.media.mediaplayer.models.MyArtists;

import java.util.ArrayList;

public class RCAdapterArtists extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<MyArtists> arrArt;
    private MyOnClick mClickArtListtener;

    public static final int SECTION_VIEW = 0;
    public static final int CONTENT_VIEW = 1;

    private View mItemView;

    public RCAdapterArtists(Context context, ArrayList<MyArtists> arrArt, MyOnClick mClickArtListtener) {
        this.context = context;
        this.arrArt = arrArt;
        this.mClickArtListtener = mClickArtListtener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        if(viewType == SECTION_VIEW){
            mItemView = inflater.inflate(R.layout.item_header_title, parent, false);
            return new ViewHolderTitleArt(mItemView);
        } else {
            mItemView = inflater.inflate(R.layout.item_artists, parent, false);
            return new ViewHolerArt(mItemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if(getItemViewType(position) == SECTION_VIEW){
            ViewHolderTitleArt holderTitleArt = (ViewHolderTitleArt) holder;
            holderTitleArt.txtTitleArt.setText(arrArt.get(position).getTitleHeader());
            return;
        }

        ViewHolerArt holerArt = (ViewHolerArt) holder;

//        holerArt.txtNameArtTitle.setText(String.valueOf(arrArt.get(position).getNameArtist().charAt(0)).toUpperCase());

        holerArt.txtNameArt.setText(arrArt.get(position).getNameArtist());

        String totalSong = arrArt.get(position).getTotalSong() + " " + context.getResources().getString(R.string.song);
        holerArt.txtSumSongs.setText(totalSong);

        holerArt.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickArtListtener.OnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrArt.size();
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if(arrArt.get(position).isSection()){
            return SECTION_VIEW;
        } else {
            return CONTENT_VIEW;
        }
    }

    private class ViewHolerArt extends RecyclerView.ViewHolder{
//        TextView txtNameArtTitle;
        TextView txtNameArt;
        TextView txtSumSongs;

        public ViewHolerArt(View itemView) {
            super(itemView);
//            txtNameArtTitle = itemView.findViewById(R.id.txtTittleNameArt_itemArt);
            txtNameArt = itemView.findViewById(R.id.txtNameArt_itemArt);
            txtSumSongs = itemView.findViewById(R.id.txtSumSong_itemArt);
        }
    }

    private class ViewHolderTitleArt extends RecyclerView.ViewHolder{
        TextView txtTitleArt;
        public ViewHolderTitleArt(View itemView) {
            super(itemView);
            txtTitleArt = itemView.findViewById(R.id.headerTitleTextview);
        }
    }
}
