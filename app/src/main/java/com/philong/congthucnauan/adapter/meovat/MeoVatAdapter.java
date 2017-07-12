package com.philong.congthucnauan.adapter.meovat;

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
import com.philong.congthucnauan.activity.ChiTietMeoVat;
import com.philong.congthucnauan.model.congthuc.CongThuc;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/19/2017.
 */

public class MeoVatAdapter extends RecyclerView.Adapter<MeoVatAdapter.MeoVatViewHolder>{

    private Context mContext;
    private List<CongThuc> mCongThucs;

    public MeoVatAdapter(Context context, List<CongThuc> congThucs) {
        mContext = context;
        mCongThucs = congThucs;
    }

    @Override
    public MeoVatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_meo_vat, parent, false);
        return new MeoVatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeoVatViewHolder holder, int position) {
        final CongThuc congThuc = mCongThucs.get(position);
        holder.txtTenMeoVat.setText(congThuc.getTen());
        Picasso.with(mContext).load(congThuc.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgMeoVat);
        holder.cardViewMeoVat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChiTietMeoVat.newIntent(mContext, congThuc.getLink(), congThuc.getTen());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCongThucs.size();
    }

    public static class MeoVatViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMeoVat;
        private TextView txtTenMeoVat;
        private CardView cardViewMeoVat;

        public MeoVatViewHolder(View itemView) {
            super(itemView);
            imgMeoVat = (ImageView) itemView.findViewById(R.id.imgHinhMeoVat);
            txtTenMeoVat = (TextView) itemView.findViewById(R.id.txtTenMeoVat);
            cardViewMeoVat = (CardView) itemView.findViewById(R.id.cardViewMeoVat);
        }
    }

}
