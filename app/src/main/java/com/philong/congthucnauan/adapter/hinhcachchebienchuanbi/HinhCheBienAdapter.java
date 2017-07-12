package com.philong.congthucnauan.adapter.hinhcachchebienchuanbi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.util.StopAnimation;
import com.squareup.picasso.Picasso;

/**
 * Created by Long on 6/20/2017.
 */

public class HinhCheBienAdapter extends RecyclerView.Adapter<HinhCheBienAdapter.HinhCheBienViewHolder>{

    private Context mContext;
    private String[] linkHinh;
    public StopAnimation delegate = null;

    public HinhCheBienAdapter(Context context, String[] linkHinh) {
        mContext = context;
        this.linkHinh = linkHinh;
    }

    public void stopAnimation(StopAnimation stopAnimation){
        this.delegate = stopAnimation;
    }

    @Override
    public HinhCheBienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.item_chi_tiet_hinh_cach_che_bien, parent, false);
        return new HinhCheBienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HinhCheBienViewHolder holder, int position) {
        Picasso.with(mContext).load(linkHinh[position]).error(R.drawable.placeholder).into(holder.imgHinhCheBien);
        if(position == linkHinh.length - 1 && linkHinh.length > 1) {
            delegate.stopBlinkAnimation();
        }
    }

    @Override
    public int getItemCount() {
        return linkHinh.length;
    }

    public static class HinhCheBienViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgHinhCheBien;

        public HinhCheBienViewHolder(View itemView) {
            super(itemView);
            imgHinhCheBien = (ImageView) itemView.findViewById(R.id.imgHinhCheBien);
        }
    }

}
