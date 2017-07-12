package com.philong.congthucnauan.adapter.bosuutapkhac;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.philong.congthucnauan.R;
import com.philong.congthucnauan.activity.DanhSachChiTietBoSuuTap;
import com.philong.congthucnauan.model.bosuutapkhac.BoSuuTapKhac;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/20/2017.
 */

public class BoSuuTapKhacAdapter  extends RecyclerView.Adapter<BoSuuTapKhacAdapter.BoSuuTapKhacViewHolder>{

    private Context mContext;
    private List<BoSuuTapKhac> mBoSuuTapKhacs;

    public BoSuuTapKhacAdapter(Context context, List<BoSuuTapKhac> boSuuTapKhacs) {
        mContext = context;
        mBoSuuTapKhacs = boSuuTapKhacs;
    }

    @Override
    public BoSuuTapKhacViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bo_suu_tap_khac, parent, false);
        return new BoSuuTapKhacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoSuuTapKhacViewHolder holder, int position) {
        final BoSuuTapKhac boSuuTapKhac = mBoSuuTapKhacs.get(position);
        holder.txtTenBSTKhac.setText(boSuuTapKhac.getTen());
        Picasso.with(mContext).load(boSuuTapKhac.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgBSTKhac);
        holder.txtBST1.setText(boSuuTapKhac.getSize());
        holder.cardViewBSTKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DanhSachChiTietBoSuuTap.newIntent(mContext, boSuuTapKhac.getLink(), boSuuTapKhac.getTen());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBoSuuTapKhacs.size();
    }

    public static class BoSuuTapKhacViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTenBSTKhac;
        private ImageView imgBSTKhac;
        private ImageView imgHinh1;
        private ImageView imgHinh2;
        private TextView txtBST1;
        private CardView cardViewBSTKhac;

        public BoSuuTapKhacViewHolder(View itemView) {
            super(itemView);
            txtTenBSTKhac = (TextView) itemView.findViewById(R.id.txtTenBoSuuTapKhac);
            imgBSTKhac = (ImageView) itemView.findViewById(R.id.imgBoSuuTapKhac);
            imgHinh1 = (ImageView) itemView.findViewById(R.id.imgHinhBST1);
            imgHinh2 = (ImageView) itemView.findViewById(R.id.imgHinhBST2);
            txtBST1 = (TextView) itemView.findViewById(R.id.txtBST1);
            cardViewBSTKhac = (CardView) itemView.findViewById(R.id.cardViewBSTKhac);
        }

    }

}
