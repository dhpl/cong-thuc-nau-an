package com.philong.congthucnauan.adapter.chitietchebien;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.adapter.hinhcachchebienchuanbi.HinhCheBienAdapter;
import com.philong.congthucnauan.model.chitiet.ChiTiet;
import com.philong.congthucnauan.util.StopAnimation;

import java.util.List;

/**
 * Created by Long on 6/20/2017.
 */

public class ChiTietCheBienAdapter extends RecyclerView.Adapter<ChiTietCheBienAdapter.ChiTietCheBienViewHolder>{

    private Context mContext;
    private List<ChiTiet> mChiTiets;

    public ChiTietCheBienAdapter(Context context, List<ChiTiet> chiTiets) {
        mContext = context;
        mChiTiets = chiTiets;
    }

    @Override
    public ChiTietCheBienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chi_tiet_cach_che_bien, parent, false);
        return new ChiTietCheBienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChiTietCheBienViewHolder holder, int position) {
        ChiTiet chiTiet = mChiTiets.get(position);
        holder.txtChiTiet.setText(String.valueOf(position + 1));
        holder.txtCachThuc.setText(chiTiet.getCachThucHien());
        holder.recyclerViewHinhCheBien.setHasFixedSize(true);
        holder.recyclerViewHinhCheBien.setNestedScrollingEnabled(false);
        holder.recyclerViewHinhCheBien.setItemAnimator(new DefaultItemAnimator());
        final Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.blink_animation);
        if(chiTiet.getLinkHinhs().length > 1){
            holder.imgBlink.setVisibility(View.VISIBLE);
            holder.imgBlink.startAnimation(animation);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerViewHinhCheBien.setLayoutManager(linearLayoutManager);
        HinhCheBienAdapter hinhCheBienAdapter = new HinhCheBienAdapter(mContext, chiTiet.getLinkHinhs());
        hinhCheBienAdapter.stopAnimation(new StopAnimation() {
            @Override
            public void stopBlinkAnimation() {
                    holder.imgBlink.clearAnimation();
                    holder.imgBlink.setVisibility(View.INVISIBLE);
            }
        });
        holder.recyclerViewHinhCheBien.setAdapter(hinhCheBienAdapter);
    }

    @Override
    public int getItemCount() {
        return mChiTiets.size();
    }

    public static class ChiTietCheBienViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView recyclerViewHinhCheBien;
        private TextView txtChiTiet;
        private TextView txtCachThuc;
        private ImageView imgBlink;

        public ChiTietCheBienViewHolder(View itemView) {
            super(itemView);
            recyclerViewHinhCheBien = (RecyclerView) itemView.findViewById(R.id.recyclerViewChiTieHinh);
            txtChiTiet = (TextView) itemView.findViewById(R.id.txtChiTiet);
            txtCachThuc = (TextView) itemView.findViewById(R.id.txtCachThuc);
            imgBlink = (ImageView) itemView.findViewById(R.id.imgBlink);
        }
    }

}
