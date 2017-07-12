package com.philong.congthucnauan.adapter.hinhanhlienquan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.philong.congthucnauan.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Long on 6/18/2017.
 */

public class HinhLienQuanAdaper extends RecyclerView.Adapter<HinhLienQuanAdaper.HinhLienQuanViewHolder>{

    private Context mContext;
    private String[] mHinhLienQuan;

    public HinhLienQuanAdaper(Context context, String[] hinhLienQuan) {
        mContext = context;
        mHinhLienQuan = hinhLienQuan;
    }

    @Override
    public HinhLienQuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hinh_lien_quan_cong_thuc, parent, false);
        return new HinhLienQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HinhLienQuanViewHolder holder, int position) {
        String linkHinhLienQuan = mHinhLienQuan[position];
        Picasso.with(mContext).load(linkHinhLienQuan).error(R.drawable.placeholder).into(holder.imgHinhLienQuan);
    }

    @Override
    public int getItemCount() {
        return mHinhLienQuan.length;
    }

    public static class HinhLienQuanViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgHinhLienQuan;

        public HinhLienQuanViewHolder(View itemView) {
            super(itemView);
            imgHinhLienQuan = (ImageView) itemView.findViewById(R.id.imgHinhLienQuan);
        }
    }
}
